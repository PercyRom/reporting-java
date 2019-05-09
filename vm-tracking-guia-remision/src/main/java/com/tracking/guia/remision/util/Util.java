package com.tracking.guia.remision.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
	
	public static String getPropiedad(String prop) {
		Properties p = new Properties();
		String propiedad = null;
		try (InputStream input = Util.class.getClassLoader().getResourceAsStream("application.properties")) {
			
			p.load(input);
			propiedad = p.getProperty(prop);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return propiedad;
	}
	
}
