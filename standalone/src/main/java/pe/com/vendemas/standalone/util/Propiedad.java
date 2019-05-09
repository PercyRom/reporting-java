package pe.com.vendemas.standalone.util;

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

import pe.com.vendemas.standalone.util.ConfigPropiedad.URI;
import pe.com.vendemas.standalone.util.ConfigPropiedad.URL_BASE;

@Service("propiedad")
@PropertySources({
		@PropertySource(value = "file:/${propertiesHomeVendemasStandalone}/configUriStandalone.properties", ignoreResourceNotFound = false),
		@PropertySource(value = "file:/${propertiesHomeVendemasStandalone}/configIPStandalone.properties", ignoreResourceNotFound = false), })
public class Propiedad {

	private static Logger LOGGER = LoggerFactory.getLogger(Propiedad.class);

	@Autowired
	private Environment env;

	@Autowired
	private ServletContext context;

	@Autowired
	private Properties properties;

	@PostConstruct
	public void init() {
		LOGGER.info("### init");
		Map<String, String> map = new HashMap<String, String>();
		for (final String key : properties.stringPropertyNames()) {
			LOGGER.info("### " + key + " : " + getProperty(key));
			map.put(key, getProperty(key));
		}

		context.setAttribute("prop", map);
		LOGGER.info("##----------------------");
		LOGGER.info(getProperty("config.url.resources.base.web"));

	}

	public String getProperty(String propName) {
		return env.getProperty(propName);
	}

	public String getURIServiceWeb(URI uri) {
		return getProperty(URL_BASE.SERVICES_WEB.getUrl())+ getProperty(uri.getUri());
	}

}
