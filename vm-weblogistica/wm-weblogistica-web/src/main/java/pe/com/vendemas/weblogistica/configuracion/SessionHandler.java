package pe.com.vendemas.weblogistica.configuracion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SessionHandler extends HandlerInterceptorAdapter {
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       request.getSession().setMaxInactiveInterval(30*60);
       return true;
   }
}