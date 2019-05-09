package com.tracking.guia.remision.ws;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		response.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
		response.getHttpHeaders().putSingle("Access-Control-Max-Age", "-1");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

       return response;
	}
	
}
