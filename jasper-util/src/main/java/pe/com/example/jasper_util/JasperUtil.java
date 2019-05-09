package pe.com.example.jasper_util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import printer.test.PrintJobWatcher;

public class JasperUtil {

	public static void main(String[] args) throws JRException, FileNotFoundException {

		String jsonCabData = "{  \r\n" + 
				"   \"departamento\":\"LIMA\",\r\n" + 
				"   \"provincia\":\"LIMA\",\r\n" + 
				"   \"distrito\":\"BREÑA\",\r\n" + 
				"   \"nombreVia\":\"ES UNA PRUEBA\",\r\n" + 
				"   \"numeroVia\":\"123\",\r\n" + 
				"   \"referencia\":\"PRUEBA\",\r\n" + 
				"   \"nombreContacto\":\"Antonio Mendozaaaa\",\r\n" + 
				"   \"telefonoContacto\":\"999999999\",\r\n" + 
				"   \"tipoPago\":\"EN LINEA\",\r\n" + 
				"   \"nombreUsuario\":\"PRUEBA VENDEMAS\",\r\n" + 
				"   \"telefonoUsuario\":\"999999999\",\r\n" + 
				"   \"rucComercio\":\"ABCdsfsfsGFFGD\",\r\n" + 
				"   \"razonSocial\":\"XYZZZZZZZ\",\r\n" + 
				"   \"IDVISANET\":\"998798765987659\",\r\n" + 
				"   \"cantidadItems\":\"1\",\r\n" + 
				"   \"Monto\":\"175.00\",\r\n" + 
				"   \"DNI\":\"abcdefc\",\r\n" + 
				"   \"tipoDocumentoPago\":\"1\",\r\n" + 
				"   \"emailRepresentante\":\"jca0907@gmail.com\",\r\n" + 
				"   \"idTipoVenta\":\"1\",\r\n" + 
				"   \"idCanal\":\"2\",\r\n" + 
				"   \"items\":[  \r\n" + 
				"      {  \r\n" + 
				"         \"item\":\"1\",\r\n" + 
				"         \"codigo\":\"MVC-0091\",\r\n" + 
				"         \"uniMedida\":\"unidad\",\r\n" + 
				"         \"cantidad\":\"1\",\r\n" + 
				"         \"descripcion\":\"POS 001-23565-DCFTS-001\"\r\n" + 
				"      },\r\n" + 
				"      {  \r\n" + 
				"         \"item\":\"2\",\r\n" + 
				"         \"codigo\":\"MVC-0092\",\r\n" + 
				"         \"uniMedida\":\"unidad\",\r\n" + 
				"         \"cantidad\":\"1\",\r\n" + 
				"         \"descripcion\":\"POS 001-23565-DCFTS-002\"\r\n" + 
				"      },\r\n" + 
				"      {  \r\n" + 
				"         \"item\":\"3\",\r\n" + 
				"         \"codigo\":\"MVC-0093\",\r\n" + 
				"         \"uniMedida\":\"unidad\",\r\n" + 
				"         \"cantidad\":\"1\",\r\n" + 
				"         \"descripcion\":\"POS 001-23565-DCFTS-003\"\r\n" + 
				"      },\r\n" + 
				"      {  \r\n" + 
				"         \"item\":\"4\",\r\n" + 
				"         \"codigo\":\"MVC-0094\",\r\n" + 
				"         \"uniMedida\":\"unidad\",\r\n" + 
				"         \"cantidad\":\"1\",\r\n" + 
				"         \"descripcion\":\"POS 001-23565-DCFTS-004\"\r\n" + 
				"      },\r\n" + 
				"      {  \r\n" + 
				"         \"item\":\"5\",\r\n" + 
				"         \"codigo\":\"MVC-0095\",\r\n" + 
				"         \"uniMedida\":\"unidad\",\r\n" + 
				"         \"cantidad\":\"1\",\r\n" + 
				"         \"descripcion\":\"POS 001-23565-DCFTS-005\"\r\n" + 
				"      }\r\n" + 
				"   ]\r\n" + 
				"}";

		Gson g = new Gson();
		BeanCabGuiaRemision cabGuiaRemision = g.fromJson(jsonCabData, BeanCabGuiaRemision.class);

		try {

			InputStream inputStream = JasperUtil.class.getResourceAsStream("/guia-remision.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cabGuiaRemision.getItems());

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("RAZON_SOCIAL", cabGuiaRemision.getRazonSocial());
			parameters.put("DATA_SOURCE", dataSource.cloneDataSource());
			parameters.put("LOGO", base64("/logo.png"));

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			
			final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

			PrintService service = PrintServiceLookup.lookupDefaultPrintService();

			InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			pras.add(new Copies(1));

			Doc doc = new SimpleDoc(is, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
			DocPrintJob job = service.createPrintJob();

			PrintJobWatcher pjw = new PrintJobWatcher(job);
			job.print(doc, pras);
			pjw.waitForDone();
			
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String base64(String resource) throws IOException {
		InputStream isLogo = JasperUtil.class.getResourceAsStream(resource);
		return Base64.getEncoder().encodeToString(IOUtils.toByteArray(isLogo));
	}

}
