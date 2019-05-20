package pe.com.vendemas.weblogistica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.consult.StprRolOpcion;
import pe.com.vendemas.weblogistica.consult.StprUsuarioRol;
import pe.com.vendemas.weblogistica.domain.Opcion;
import pe.com.vendemas.weblogistica.domain.Rol;
import pe.com.vendemas.weblogistica.domain.Usuario;
import pe.com.vendemas.weblogistica.filterBean.RolFilter;
import pe.com.vendemas.weblogistica.filterBean.UsuarioFilter;
import pe.com.vendemas.weblogistica.seguridad.bean.UsuarioWebBean;
import pe.com.vendemas.weblogistica.service.OpcionService;
import pe.com.vendemas.weblogistica.service.RolService;
import pe.com.vendemas.weblogistica.service.UsuarioService;
import pe.com.vendemas.weblogistica.util.ConstantesJSP;

@Controller()
@RequestMapping("/seguridad")
public class SeguridadController {
	
	@Autowired
	@Qualifier("opcionServiceImpl")
	private OpcionService opcionServiceImpl;
	
	@Autowired
	@Qualifier("rolServiceImpl")
	private RolService rolServiceImpl;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioServiceImpl;
	
	@RequestMapping(value = "roles", method = RequestMethod.GET)
	public String showPageRoles(ModelMap model, HttpServletRequest request) {
		return "roles";
	}
	
	@ResponseBody
	@RequestMapping(value = "listarOpciones", method = RequestMethod.GET)
	public List<Opcion> listarOpciones() {
		return opcionServiceImpl.obtenerOpciones();
	}
	
	@ResponseBody
	@RequestMapping(value = "listarRoles", method = RequestMethod.POST)
	public StprPaginado listarRoles(@RequestBody RolFilter filter) {
		StprPaginado result = new StprPaginado();
		result = rolServiceImpl.obtenerRolesPaginado(filter);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "loadRol", method = RequestMethod.POST)
	public StprRolOpcion loadRol(@RequestBody Integer idRol) {
		StprRolOpcion result = new StprRolOpcion();
		result.setRol(rolServiceImpl.obtenerRolById(idRol));
		result.setListOpciones(opcionServiceImpl.obtenerOpcionesByRol(idRol));
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "saveRol", method = RequestMethod.PUT)
	public String saveRol(@RequestBody Rol rol,HttpServletRequest request) {
		
		HttpSession sesion = request.getSession();
		UsuarioWebBean usuarioSession = (UsuarioWebBean)sesion.getAttribute(ConstantesJSP.SESSION_USUARIO_ADMIN);
//		System.out.println("[roles]["+usuario.getRoles()+"]");
		rol.setUsuarioSession(usuarioSession.getUsuario());
		rolServiceImpl.guardarRol(rol);
		return "SUCCESS";
	}
	
	@RequestMapping(value = "usuarios", method = RequestMethod.GET)
	public String showPageUsuarios(ModelMap model, HttpServletRequest request) {
		return "usuarios";
	}
	
	@ResponseBody
	@RequestMapping(value = "listarUsuarios", method = RequestMethod.POST)
	public StprPaginado listarUsuarios(@RequestBody UsuarioFilter usuarioFilter) {
		return usuarioServiceImpl.obtenerUsuariosPaginado(usuarioFilter);
	}
	
	@ResponseBody
	@RequestMapping(value = "listarRoles", method = RequestMethod.GET)
	public List<Rol> listarRoles() {
		return rolServiceImpl.obtenerRoles();
	}
	
	@ResponseBody
	@RequestMapping(value = "saveUsuario", method = RequestMethod.PUT)
	public String saveUsuario(@RequestBody Usuario usuario,HttpServletRequest request) {
		
		HttpSession sesion = request.getSession();
		UsuarioWebBean usuarioSession = (UsuarioWebBean)sesion.getAttribute(ConstantesJSP.SESSION_USUARIO_ADMIN);
//		System.out.println("[roles]["+usuario.getRoles()+"]");
		usuario.setUsuarioSession(usuarioSession.getUsuario());
		if(usuarioServiceImpl.guardarUsuario(usuario)!=0) {
			System.out.println("usuario guardado");
		}
		return "SUCCESS";
	}
	
	@ResponseBody
	@RequestMapping(value = "loadUsuario", method = RequestMethod.POST)
	public StprUsuarioRol loadUsuario(@RequestBody Integer idUsuario) {
		StprUsuarioRol result = new StprUsuarioRol();
		result.setUsuario(usuarioServiceImpl.obtenerUsuarioById(idUsuario));
		result.setListRoles(rolServiceImpl.obtenerRolesByUsuario(idUsuario));
		return result;
	}
	
}
