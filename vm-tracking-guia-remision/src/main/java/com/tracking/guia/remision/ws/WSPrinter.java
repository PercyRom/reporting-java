package com.tracking.guia.remision.ws;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.tracking.guia.remision.AppRun;
import com.tracking.guia.remision.bean.BeanMessage;
import com.tracking.guia.remision.util.Constantes;
import com.tracking.guia.remision.util.Jasper;
import com.tracking.guia.remision.util.Util;

@Path("/printer")
public class WSPrinter {

	private static final String empty = "";
	private static final String msg_error = "Error al Crear Reporte";
	private static final String msg_succes = "Enviado a Imprimir";
	private static final String ln = "\n";

	@GET
	@Path("/{base64}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response print(@PathParam("base64") String base64) {

		String decode = empty;
		BeanMessage msg =  null;
		String response = empty;
		
		String wrapping = Util.getPropiedad(Constantes.SERVICE_PRINT_WRAPPING);

		try {
			if (base64 != null && !base64.equals(empty)) {
				byte[] credDecoded = Base64.getDecoder().decode(base64);
				decode = new String(credDecoded, StandardCharsets.UTF_8);
				
				msg = Jasper.create(decode);
				
				if(msg.isSucess()) {
					msg.setMsg(msg_succes);
				}else {
					msg.setMsg(msg_error);
				}
			}
			
			response = wrapping.concat("(" + new Gson().toJson(msg) + ")");
			
			AppRun.getPanelControl().fnCargarConsola(decode.concat(ln).concat(response));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(response).build();
	}
}