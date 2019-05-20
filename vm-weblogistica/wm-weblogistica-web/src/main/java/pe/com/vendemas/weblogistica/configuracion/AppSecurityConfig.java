package pe.com.vendemas.weblogistica.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.com.vendemas.weblogistica.seguridad.LoginFailureHandler;
import pe.com.vendemas.weblogistica.seguridad.LoginSuccessHandler;
import pe.com.vendemas.weblogistica.seguridad.service.impl.AutenticacionServiceImpl;
import pe.com.vendemas.weblogistica.util.SpringUtil;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	@Qualifier("autenticacionServiceImpl")
	private AutenticacionServiceImpl userDetailsService;
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private LoginFailureHandler loginFailureHandler;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.sessionManagement().maximumSessions(1);
	    http.authorizeRequests()
	  	.antMatchers("/login/**").permitAll()
	  	.antMatchers("/recuperar/**").permitAll()
		.antMatchers("/**").authenticated()
		.and()
		.formLogin()
		.loginProcessingUrl(SpringUtil.LOGIN_PROCESSING_URL)
		.loginPage(SpringUtil.LOGIN_PAGE)
		.successHandler(loginSuccessHandler)
		.failureHandler(loginFailureHandler)
		.usernameParameter(SpringUtil.USERNAME_PARAMETER)
		.passwordParameter(SpringUtil.PASSWORD_PARAMETER)
		.and()
		.logout()
		.logoutUrl(SpringUtil.LOGOUT_URL)
		.logoutSuccessUrl(SpringUtil.LOGOUT_SUCCESS_URL)
		.invalidateHttpSession(true)
		.deleteCookies(SpringUtil.JSESSIONID)
		.and()
		.exceptionHandling()
		.accessDeniedPage(SpringUtil.ACCES_DENIED_PAGE)
		.and()
		.csrf()
		.disable();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}