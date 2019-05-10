package com.tracking.guia.remision.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.tracking.guia.remision.AppRun;
import com.tracking.guia.remision.bean.BeanCabGuiaRemision;
import com.tracking.guia.remision.bean.BeanDetGuiaRemision;
import com.tracking.guia.remision.bean.BeanMessage;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Jasper {

	public static BeanMessage create(String jsonData) {
		BeanMessage message = null;

		Gson g = new Gson();
		BeanCabGuiaRemision cabGuiaRemision = g.fromJson(jsonData, BeanCabGuiaRemision.class);

		List<BeanDetGuiaRemision> lsBean = cabGuiaRemision.getItems();

		InputStream inputStream = Jasper.class.getResourceAsStream("/guia-remision.jasper");

		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Map<String, Object> parameters;
		JRBeanCollectionDataSource dataSource;
		final ByteArrayOutputStream outputStream;

		try {

			message = new BeanMessage();
			jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
			
			int fill = lsBean.size();
			for (int i = fill; i <= 8; i++) {
				lsBean.add(new BeanDetGuiaRemision());
			}

			dataSource = new JRBeanCollectionDataSource(lsBean);
			parameters = new HashMap<String, Object>();

			parameters.put("RAZON_SOCIAL", cabGuiaRemision.getRazonSocial());
			parameters.put("DATA_SOURCE", dataSource.cloneDataSource());
			parameters.put("LOGO", base64("/logo-vendemas.jpg"));

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			outputStream = new ByteArrayOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

			PrintService service = AppRun.getPanelControl().fnImpresora();
			
			InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			pras.add(new Copies(1));

			Doc doc = new SimpleDoc(is, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
			DocPrintJob job = service.createPrintJob();

			PrintJobWatcher pjw = new PrintJobWatcher(job);
			job.print(doc, pras);
			pjw.waitForDone();

			is.close();

			message.setSucess(true);
			message.setCode(1);
			

		} catch (JRException e) {

			message.setSucess(false);
			message.setMsg(e.getMessage());
			message.setCode(0);
			e.printStackTrace();

		} catch (IOException e) {

			message.setSucess(false);
			message.setMsg(e.getMessage());
			message.setCode(0);
			e.printStackTrace();

		} catch (PrintException e) {

			message.setSucess(false);
			message.setMsg(e.getMessage());
			message.setCode(0);
			e.printStackTrace();

		}

		return message;
	}

	private static String base64(String resource) throws IOException {
		InputStream isLogo = Jasper.class.getResourceAsStream(resource);
		return Base64.getEncoder().encodeToString(IOUtils.toByteArray(isLogo));
	}
}