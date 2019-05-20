package pe.com.vendemas.weblogistica.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.vendemas.weblogistica.consult.StprMaestroEntidad;
import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.domain.Entidad;
import pe.com.vendemas.weblogistica.filterBean.EntidadFilter;
import pe.com.vendemas.weblogistica.seguridad.bean.UsuarioWebBean;
import pe.com.vendemas.weblogistica.service.impl.MaestroServiceImpl;
import pe.com.vendemas.weblogistica.util.ConstantesJSP;

@Controller()
@RequestMapping("/maestras")
public class MaestroController {
	
	@Autowired
	@Qualifier("maestroServiceImpl")
	private MaestroServiceImpl maestroServiceImpl;
	
	
	@RequestMapping(value = "entidad", method = RequestMethod.GET)
	public String showPageRoles(ModelMap model, HttpServletRequest request) {
		return "entidad";
	}
	
	@RequestMapping(value = "entidad/nuevo", method = RequestMethod.GET)
	public String showEntidadNuevo(ModelMap model, HttpServletRequest request) {
		request.setAttribute("lstTipoEntidad", maestroServiceImpl.getTiposEntidad());
		request.setAttribute("lstTipoDocumentoIdentidad", maestroServiceImpl.getTipoDocumentoIdentidad());
		return "mntoEntidad";
	}
	
	@ResponseBody
	@RequestMapping(value = "listarEntidades", method = RequestMethod.POST)
	public StprPaginado listarEntidades(@RequestBody EntidadFilter filter) {
		StprPaginado result = new StprPaginado();
		result = maestroServiceImpl.listarEntidades(filter);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "entidad/save", method = RequestMethod.POST)
	public StprMaestroEntidad entidadSave(@RequestBody Entidad entidad,HttpServletRequest request) {
		StprMaestroEntidad maestro = new  StprMaestroEntidad();
		HttpSession session = request.getSession();
		UsuarioWebBean usuarioSession = (UsuarioWebBean)session.getAttribute(ConstantesJSP.SESSION_USUARIO_ADMIN);
		entidad.setUsuarioSession(usuarioSession.getUsuario());
		maestro.setEntidad(maestroServiceImpl.entidadSave(entidad));
		return maestro;
	}
	
	@RequestMapping(value = "entidad/{id}", method = RequestMethod.GET)
	public String entidadById(@PathVariable("id") Integer id,ModelMap model,HttpServletRequest request) {
		request.setAttribute("lstTipoEntidad", maestroServiceImpl.getTiposEntidad());
		request.setAttribute("lstTipoDocumentoIdentidad", maestroServiceImpl.getTipoDocumentoIdentidad());
		request.setAttribute("entidad", maestroServiceImpl.getEntidadById(id));
		return "mntoEntidad";
	}
}
