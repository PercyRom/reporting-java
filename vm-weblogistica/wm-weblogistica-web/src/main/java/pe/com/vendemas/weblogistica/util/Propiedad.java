package pe.com.vendemas.weblogistica.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service("propiedad")
@PropertySources({
	@PropertySource(value = "file:/${propertiesHomeWebLogistica}/vm-weblogistica-aplicacion.properties", ignoreResourceNotFound = false)
})
public class Propiedad {
	
	private static Logger logger = LoggerFactory.getLogger(Propiedad.class);

	@Autowired
	private Environment env;

	@Autowired
	private ServletContext context;

	@Autowired
	private Properties propertiesHomeWebLogistica;

	@PostConstruct
	public void init() {
		Map<String, String> map = new HashMap<String, String>();
		for (final String key : propertiesHomeWebLogistica.stringPropertyNames()) {
			map.put(key, getProperty(key));
		}

		context.setAttribute("prop", map);
		logger.info("##----------------------");
		logger.info(getProperty("aplicacion.rutaRecursos"));
	}

	public String getProperty(String propName) {
		return env.getProperty(propName);
	}
}