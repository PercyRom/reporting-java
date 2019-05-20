package pe.com.vendemas.weblogistica.service;

import java.util.List;

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.domain.Rol;
import pe.com.vendemas.weblogistica.filterBean.RolFilter;

public interface RolService {
	
	//CRUD BASICO
	public Integer nuevoRol(Rol rol);
	public List<Rol> obtenerRoles();
	public void actualizarRol(Rol rol);
	public Rol obtenerRolById(Integer id);
	
	public List<Rol> obtenerRolesByUsuario(Integer idUsuario);
	public StprPaginado obtenerRolesPaginado(RolFilter filter);
	public Integer guardarRol(Rol rol);
	
}
	