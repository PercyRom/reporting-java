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

import pe.com.vendemas.weblogistica.domain.Compra;
import pe.com.vendemas.weblogistica.domain.Entidad;
import pe.com.vendemas.weblogistica.domain.TipoEntidad;
import pe.com.vendemas.weblogistica.domain.TipoProducto;
import pe.com.vendemas.weblogistica.seguridad.bean.UsuarioWebBean;
import pe.com.vendemas.weblogistica.service.impl.CompraServiceImpl;
import pe.com.vendemas.weblogistica.service.impl.EntidadContactosServiceImpl;
import pe.com.vendemas.weblogistica.service.impl.MaestroServiceImpl;
import pe.com.vendemas.weblogistica.service.impl.TipoProductoServiceImpl;
import pe.com.vendemas.weblogistica.util.ConstantesJSP;

@Controller()
@RequestMapping("/almacen")
public class AlmacenController {
	
	@Autowired
	@Qualifier("maestroServiceImpl")
	private MaestroServiceImpl maestroServiceImpl;
	
	@Autowired
	@Qualifier("tipoProductoServiceImpl")
	private TipoProductoServiceImpl tipoProductoServiceImpl;
	
	@Autowired
	@Qualifier("compraServiceImpl")
	private CompraServiceImpl compraServiceImpl;
	
	@Autowired
	@Qualifier("entidadContactosServiceImpl")
	private EntidadContactosServiceImpl entidadContactosServiceImpl;
	
	@RequestMapping(value = "compras", method = RequestMethod.GET)
	public String showPageComprasListado(ModelMap model, HttpServletRequest request) {
		return "compras";
	}
	
	@RequestMapping(value = "compra-registro", method = RequestMethod.GET)
	public String showPageComprasRegistro(ModelMap model, HttpServletRequest request) {
		List<TipoEntidad> listTiposEntidad = maestroServiceImpl.getTiposEntidad();
		List<TipoProducto> listTipoProducto = tipoProductoServiceImpl.obtenerTipoProductos();
		
		request.setAttribute("listTiposEntidad",listTiposEntidad);
		request.setAttribute("listTipoProducto",listTipoProducto);
		return "compra-registro";
	}
	
	@ResponseBody
	@RequestMapping(value = "listarEntidadByTipo", method = RequestMethod.POST)
	public List<Entidad> listarEntidadByTipo(@RequestBody Integer idTipoEntidad) {
		List<Entidad> listEntidad = maestroServiceImpl.listarEntidadesPorTipo(idTipoEntidad);
		return listEntidad;
	}
	
	@ResponseBody
	@RequestMapping(value = "obtenerDatosEntidad", method = RequestMethod.POST)
	public Entidad obtenerDatosEntidad(@RequestBody Integer idEntidad) {
		return maestroServiceImpl.getEntidadById(idEntidad);
	}
	
	@ResponseBody
	@RequestMapping(value = "saveCompra", method = RequestMethod.PUT)
	public String saveCompra(@RequestBody Compra compra,HttpServletRequest request) {
		
		HttpSession sesion = request.getSession();
		UsuarioWebBean usuarioSession = (UsuarioWebBean)sesion.getAttribute(ConstantesJSP.SESSION_USUARIO_ADMIN);
		compra.setUsuarioSession(usuarioSession.getUsuario());
		compraServiceImpl.guardarCompra(compra);
		
		return "compras";
	}
	
}
