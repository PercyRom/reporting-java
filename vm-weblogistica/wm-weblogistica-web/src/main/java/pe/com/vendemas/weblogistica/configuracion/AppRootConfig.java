package pe.com.vendemas.weblogistica.configuracion;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.util.Propiedad;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@DependsOn("transactionManager")
@ImportResource("classpath:applicationContext.xml")
@ComponentScan(basePackages = "pe.com.vendemas.weblogistica")
@PropertySources({
	@PropertySource(value = "file:/${propertiesHomeWebLogistica}/vm-weblogistica-jdbc.properties", ignoreResourceNotFound = false),
	@PropertySource(value = "file:/${propertiesHomeWebLogistica}/vm-weblogistica-aplicacion.properties", ignoreResourceNotFound = false)
})
public class AppRootConfig implements TransactionManagementConfigurer {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Environment environment;
	
	@Value(value = "file:/${propertiesHomeWebLogistica}/vm-weblogistica-aplicacion.properties")
	private Resource configAplicacion;
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager transactionManager;
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}

	@Bean(name="dataSource",destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		String driverClassName = environment.getRequiredProperty("jdbc.driverClassName");
		String url = environment.getRequiredProperty("jdbc.db.url");
		String usuario = environment.getRequiredProperty("jdbc.db.usuario");
		String clave = environment.getRequiredProperty("jdbc.db.clave");
		
		String esquema = environment.getRequiredProperty("jdbc.db.esquema");
		ConstantesDAO.iniciarlizarEsquema(esquema);
		
		String minThread = environment.getRequiredProperty("jdbc.db.minThread");
		String maxThread = environment.getRequiredProperty("jdbc.db.maxThread");
		
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(usuario);
		dataSource.setPassword(clave);

		dataSource.setInitialSize(Integer.parseInt(minThread));
		dataSource.setMaxTotal(Integer.parseInt(maxThread));
		dataSource.setDefaultAutoCommit(false);

		return dataSource;
	}

	@Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager;
    }
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters());
		return restTemplate;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter jsonHttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		jsonHttpMessageConverter.setPrefixJson(false);
		jsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		jsonHttpMessageConverter.setObjectMapper(objectMapper);
		return jsonHttpMessageConverter;
	}

	public List<HttpMessageConverter<?>> messageConverters() {
		List<MediaType> mediatypes = new ArrayList<MediaType>();
		mediatypes.add(new MediaType("text", "plain", StandardCharsets.UTF_8));
		mediatypes.add(new MediaType("*", "*", StandardCharsets.UTF_8));

		StringHttpMessageConverter stringHttpConverter = new StringHttpMessageConverter();
		stringHttpConverter.setSupportedMediaTypes(mediatypes);

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(stringHttpConverter);
		messageConverters.add(jsonHttpMessageConverter());

		return messageConverters;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("WEB-INF/messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean
	public PropertiesFactoryBean propertiesHomeWebLogistica() {
		PropertiesFactoryBean propertiesHomeWebLogistica = new PropertiesFactoryBean();
		propertiesHomeWebLogistica.setLocations(configAplicacion);
		return propertiesHomeWebLogistica;
	}

	@Scope(value = "singleton")
	@Bean(name = "propiedad", initMethod = "init")
	Propiedad propiedad() {
		return new Propiedad();
	}

}