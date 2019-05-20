package pe.com.vendemas.weblogistica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.dao.RolUsuarioDao;
import pe.com.vendemas.weblogistica.dao.UsuarioDao;
import pe.com.vendemas.weblogistica.domain.RolUsuario;
import pe.com.vendemas.weblogistica.domain.Usuario;
import pe.com.vendemas.weblogistica.filterBean.UsuarioFilter;
import pe.com.vendemas.weblogistica.service.UsuarioService;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("rolUsuarioDao")
	private RolUsuarioDao rolUsuarioDao;

	@Override
	public Integer nuevoUsuario(Usuario usuario) {
		return usuarioDao.createUsuario(usuario);
	}

	@Override
	public List<Usuario> obtenerUsuarios(Usuario usuario) {
		return usuarioDao.getUsuarios(usuario);
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		usuarioDao.updateUsuario(usuario);
	}

	@Override
	public StprPaginado obtenerUsuariosPaginado(UsuarioFilter usuarioFilter) {
//		StprUsuarioPaginado result = new StprUsuarioPaginado();
//		
//		Integer totalUsuarios = usuarioDao.getUsuariosTotal(usuarioFilter);
//		if(usuarioFilter.getNumPagina() <= 0 && usuarioFilter.getCantidadFilasPagina() <= 0) {
//			usuarioFilter.setOffset(0);
//			usuarioFilter.setLimit(totalUsuarios);
//			result.setTotalPaginas(1);
//		}else {
//			usuarioFilter.setOffset( (usuarioFilter.getNumPagina()-1) * usuarioFilter.getCantidadFilasPagina());
//			usuarioFilter.setLimit(usuarioFilter.getCantidadFilasPagina());
//			result.setTotalPaginas( (totalUsuarios/usuarioFilter.getCantidadFilasPagina())+(totalUsuarios%usuarioFilter.getCantidadFilasPagina()==0?0:1) );
//		}
//		List<Usuario> listUsuarios = usuarioDao.getUsuariosPaginado(usuarioFilter);
//		
//		result.setTotalUsuarios(totalUsuarios);
//		result.setListUsuarios(listUsuarios);
//		return result;
		return usuarioDao.getUsuariosPaginado(usuarioFilter);
	}

	@Override
	public Usuario obtenerUsuarioById(Integer idUsuario) {
		return usuarioDao.getUsuarioById(idUsuario);
	}

	@Override
	public Integer guardarUsuario(Usuario usuario) {
		Integer result=0;
		
		if(usuario.getIdUsuario()>0) {
			usuarioDao.updateUsuario(usuario);
			result = usuario.getIdUsuario();
		}else {
			result = usuarioDao.createUsuario(usuario);
			usuario.setIdUsuario(result);
		}
		
		if(result!= null && result>0) {
			RolUsuario rolUsuario = new RolUsuario();
			rolUsuario.setIdUsuario(usuario.getIdUsuario());
			rolUsuario.setUsuarioSession(usuario.getUsuarioSession());
			rolUsuario.setRoles(usuario.getRoles());
			rolUsuarioDao.mantRolUsuario(rolUsuario);
		}
		
		return result;
	}
	
}
