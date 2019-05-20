package pe.com.vendemas.weblogistica.dao;

import java.util.List;

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.domain.Usuario;
import pe.com.vendemas.weblogistica.filterBean.UsuarioFilter;

public interface UsuarioDao {

	//CRUD BASICO
	public Integer createUsuario(Usuario usuario);
	public List<Usuario> getUsuarios(Usuario usuario);
	public void updateUsuario(Usuario usuario);
	public Usuario getUsuarioById(Integer idUsuario);
	
	public Usuario getUsuarioByCodigo(String codigoUsuario);
	public StprPaginado getUsuariosPaginado(UsuarioFilter usuarioFilter);
	public Integer getUsuariosTotal(UsuarioFilter usuarioFilter);
	
}