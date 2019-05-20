package pe.com.vendemas.weblogistica.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.vendemas.weblogistica.seguridad.bean.UsuarioWebBean;
import pe.com.vendemas.weblogistica.util.ConstantesJSP;

@Controller
public class InicioController {

	@RequestMapping(value="/inicio",method = RequestMethod.GET)
	public String inicio(ModelMap model,HttpServletRequest request) {

		HttpSession sesion = request.getSession();
		UsuarioWebBean usuario = (UsuarioWebBean) sesion.getAttribute(ConstantesJSP.SESSION_USUARIO_ADMIN);
		if(usuario!=null){
			return "redirect:/dashboard";
		}else{
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false)String error,
							@RequestParam(value = "logout", required = false)String logout,
							Model model,
							HttpServletRequest request){

		if (error != null) {
			model.addAttribute("error","Credenciales incorrectas");
		}
		if (logout != null) {
			model.addAttribute("msg", "Login correcto");
		}

		return ConstantesJSP.PAGINA_LOGIN;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {

		HttpSession sesion = request.getSession();
	    sesion.removeAttribute(ConstantesJSP.SESSION_USUARIO_ADMIN);
	    
		SecurityContext context = SecurityContextHolder.getContext();
	    Authentication auth = context.getAuthentication();
	    SecurityContextLogoutHandler logoutHandler = null;
	    if (auth != null){    
	    	logoutHandler = new SecurityContextLogoutHandler();
	    	logoutHandler.logout(request, response, auth);
	    }

	    return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public String dashboard(ModelMap model,HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		UsuarioWebBean usuario = (UsuarioWebBean)sesion.getAttribute(ConstantesJSP.SESSION_USUARIO_ADMIN);
		
		String retorno = "";
		if(usuario!=null) {
			request.setAttribute("usuario",usuario);
			retorno = ConstantesJSP.PAGINA_DASHBOARD;
		}else {
			retorno = "redirect:/login";
		}
		return retorno;
	}
	
}