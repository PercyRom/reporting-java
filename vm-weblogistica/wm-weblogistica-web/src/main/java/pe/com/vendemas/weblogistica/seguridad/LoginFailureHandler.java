package pe.com.vendemas.weblogistica.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import pe.com.vendemas.weblogistica.util.SpringUtil;

@Component("LoginFailureHandler")
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,HttpServletResponse response, AuthenticationException exception)throws IOException, ServletException {
		String urlToRedirect = request.getContextPath()+ SpringUtil.AUTHENTICATION_FAILURE_URL;
		response.sendRedirect(urlToRedirect);
	}
}