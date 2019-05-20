package pe.com.vendemas.weblogistica.dao;

import java.util.List;

import pe.com.vendemas.weblogistica.domain.RolUsuario;

public interface RolUsuarioDao {
	
	//CRUD BASICO
	public Integer createRolUsuario(RolUsuario obj);
	public List<RolUsuario> getRolUsuarios();
	public void updateRolUsuario(RolUsuario obj);
	
	public void mantRolUsuario(RolUsuario obj);
	
}
