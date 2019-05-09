package pe.com.vendemas.standalone.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

import pe.com.vendemas.standalone.controller.seguridad.service.impl.AutenticacionServiceImpl;


@Configuration
@EnableWebSecurity
@PropertySources({
	@PropertySource(value = "file:/${propertiesHomeVendemasStandalone}/vm-standalone-aplicacion.properties", ignoreResourceNotFound = false) 
})
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    
	@Autowired
	@Qualifier("autenticacionServiceImpl")
	private AutenticacionServiceImpl userDetailsService;
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
          .antMatchers("/venta/enviar").permitAll()
          .antMatchers("/v2/api-docs").permitAll()
          .anyRequest().permitAll()
          .and()
          .httpBasic()
          .authenticationEntryPoint(authenticationEntryPoint);
    }
}