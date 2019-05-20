package pe.com.vendemas.weblogistica.service;

import java.util.List;

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.domain.Usuario;
import pe.com.vendemas.weblogistica.filterBean.UsuarioFilter;

public interface UsuarioService {
	
	//CRUD BASICO
	public Integer nuevoUsuario(Usuario usuario);
	public List<Usuario> obtenerUsuarios(Usuario usuario);
	public void actualizarUsuario(Usuario usuario);
	public Usuario obtenerUsuarioById(Integer idUsuario);
	
	public Integer guardarUsuario(Usuario usuario);
	public StprPaginado obtenerUsuariosPaginado(UsuarioFilter usuarioFilter);
	
}
