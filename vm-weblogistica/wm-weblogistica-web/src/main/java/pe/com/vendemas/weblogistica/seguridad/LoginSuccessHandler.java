package pe.com.vendemas.weblogistica.seguridad;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pe.com.vendemas.weblogistica.domain.Opcion;
import pe.com.vendemas.weblogistica.seguridad.bean.UsuarioWebBean;
import pe.com.vendemas.weblogistica.seguridad.bean.UsuarioWsSpring;
import pe.com.vendemas.weblogistica.util.ConstantesJSP;

@Component("LoginSuccessHandler")
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	//private static Logger LOGGER = LoggerFactory.getLogger(LoginSuccessHandler.class);

	/*
	@Autowired
	private Propiedad propiedad;

	@Autowired
	private RestTemplate restTemplate;
	*/
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication)throws ServletException, IOException {

		super.onAuthenticationSuccess(request, response, authentication);
		UsuarioWsSpring user = (UsuarioWsSpring) authentication.getPrincipal();
		
		UsuarioWebBean usuario = user.getUsuario();
		
		//Colocar los datos en sesion
		HttpSession sesion = request.getSession();

		//Pasar las opciones a gson
		Gson gson = new Gson();
		Type typeLst = new TypeToken<List<Opcion>>(){}.getType();
		
		List<Opcion> lstOpciones = usuario.getLstOpciones();
		if(lstOpciones == null) {
			lstOpciones = new ArrayList<Opcion>();
		}
		
		String jsonOpciones = gson.toJson(lstOpciones,typeLst);
		sesion.setAttribute(ConstantesJSP.SESSION_JSON_MENU,jsonOpciones);
		sesion.setAttribute(ConstantesJSP.SESSION_USUARIO_ADMIN,usuario);
		//putParticipanteInSession(user.getUsuario(), request);
	}

	/*
	public void putParticipanteInSession(Usuario usuario,HttpServletRequest request) {
		HttpSession sesion = request.getSession();

		//Pasar las opciones a gson
		Gson gson = new Gson();
		Type typeLst = new TypeToken<List<Opcion>>(){}.getType();
		
		List<Opcion> lstOpciones = usuario.getLstOpciones();
		if(lstOpciones == null) {
			lstOpciones = new ArrayList<Opcion>();
		}
		
		String jsonOpciones = gson.toJson(lstOpciones,typeLst);
		sesion.setAttribute(Constantes.SESSION_JSON_MENU,jsonOpciones);
		sesion.setAttribute(Constantes.SESSION_USUARIO_ADMIN,usuario);
	}*/

	/*
	public void putSessionListaAccesosInSession(List<RolAcceso> listaAccesos,HttpServletRequest request) {
		LOGGER.info("## Colocando lista de accesos en session");
		request.getSession().setAttribute(Constantes.SESSION_LISTA_ACCESOS,listaAccesos);
	}*/

}