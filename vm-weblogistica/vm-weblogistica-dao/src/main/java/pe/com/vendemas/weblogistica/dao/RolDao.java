package pe.com.vendemas.weblogistica.dao;

import java.util.List;

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.domain.Rol;
import pe.com.vendemas.weblogistica.filterBean.RolFilter;

public interface RolDao {
	
	//CRUD BASICO
	public Integer createRol(Rol rol);
	public List<Rol> getRoles();
	public void updateRol(Rol rol);
	
	public Rol getRolById(Integer id);
	public List<Rol> getRolesByUser(Integer idUsuario);
	public StprPaginado getRolesPaginado(RolFilter filter);
}