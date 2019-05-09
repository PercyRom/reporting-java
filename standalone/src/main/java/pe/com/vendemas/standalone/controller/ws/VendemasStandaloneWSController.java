package pe.com.vendemas.standalone.controller.ws;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import pe.com.vendemas.standalone.controller.bean.Filtro;
import pe.com.vendemas.standalone.util.ConfigPropiedad.URI;
import pe.com.vendemas.standalone.util.Propiedad;

@RestController
@RequestMapping("/api/reader")
public class VendemasStandaloneWSController {

	@Autowired
	private Propiedad propiedad;

	static final Logger logger = (Logger) LoggerFactory.getLogger(VendemasStandaloneWSController.class);

	@RequestMapping(value = "/activate", method = { RequestMethod.POST }, produces = {"application/json; charset=UTF-8" })
	public String activate(@RequestBody Filtro filtro) {
		String strRpta = "";
		try {

			String strUrl = propiedad.getProperty(URI.SERVICE_PAX_ACTIVATE.getUri());

			String body = new Gson().toJson(filtro);

			URL url = new URL(strUrl);
			HttpURLConnection connection = null;
			OutputStream os = null;
			InputStream entrada = null;
			ByteArrayOutputStream baos = null;

			int codigoRespuesta = -1;

			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", null);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(50000);
			connection.setReadTimeout(50000);

			os = connection.getOutputStream();
			os.write(body.toString().getBytes("UTF-8"));
			os.close();

			connection.connect();
			codigoRespuesta = connection.getResponseCode();

			if (codigoRespuesta == HttpURLConnection.HTTP_OK || codigoRespuesta == HttpURLConnection.HTTP_CREATED) {
				entrada = connection.getInputStream();
			} else if (codigoRespuesta == HttpURLConnection.HTTP_BAD_REQUEST) {
				entrada = connection.getErrorStream();
			} else if (codigoRespuesta == HttpURLConnection.HTTP_UNAUTHORIZED) {
				entrada = connection.getErrorStream();
			} else if (codigoRespuesta == HttpURLConnection.HTTP_INTERNAL_ERROR) {
				entrada = connection.getErrorStream();
			} else {
				strRpta = "ERROR DESCONOCIDO";
			}

			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			if (entrada != null) {
				int length = 0;
				while ((length = entrada.read(buffer)) != -1) {
					baos.write(buffer, 0, length);
					strRpta = baos.toString();
				}
				entrada.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRpta;
	}
	
	@RequestMapping(value = "/deactivate", method = { RequestMethod.POST }, produces = {"application/json; charset=UTF-8" })
	public String deactivate(@RequestBody Filtro filtro,HttpServletRequest request,HttpServletResponse response) {
		String strRpta = "";
		try {

			String strUrl = propiedad.getProperty(URI.SERVICE_PAX_DEACTIVATE.getUri());
			
			String apiKeyAutorizacion = request.getHeader("X-Authentication-ApiKey");
			String tokenAutorizacion = request.getHeader("X-Authentication-Token");

			String body = new Gson().toJson(filtro);

			URL url = new URL(strUrl);
			HttpURLConnection connection = null;
			OutputStream os = null;
			InputStream entrada = null;
			ByteArrayOutputStream baos = null;

			int codigoRespuesta = -1;

			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("X-Authentication-ApiKey", apiKeyAutorizacion);
			connection.setRequestProperty("X-Authentication-Token", tokenAutorizacion);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(50000);
			connection.setReadTimeout(50000);

			os = connection.getOutputStream();
			os.write(body.toString().getBytes("UTF-8"));
			os.close();

			connection.connect();
			codigoRespuesta = connection.getResponseCode();

			if (codigoRespuesta == HttpURLConnection.HTTP_OK || codigoRespuesta == HttpURLConnection.HTTP_CREATED) {
				entrada = connection.getInputStream();
			} else if (codigoRespuesta == HttpURLConnection.HTTP_BAD_REQUEST) {
				entrada = connection.getErrorStream();
			} else if (codigoRespuesta == HttpURLConnection.HTTP_UNAUTHORIZED) {
				entrada = connection.getErrorStream();
			} else if (codigoRespuesta == HttpURLConnection.HTTP_INTERNAL_ERROR) {
				entrada = connection.getErrorStream();
			} else {
				strRpta = "ERROR DESCONOCIDO";
			}
			
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			if (entrada != null) {
				int length = 0;
				while ((length = entrada.read(buffer)) != -1) {
					baos.write(buffer, 0, length);
					strRpta = baos.toString();
				}
				entrada.close();
			}
			
			if(strRpta.equals("") && HttpURLConnection.HTTP_OK == codigoRespuesta){
				strRpta.concat("{\"data\" : { \"code\" : \"200\", \"responseText\" : \"OK\"}  }");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRpta;
	}

}
