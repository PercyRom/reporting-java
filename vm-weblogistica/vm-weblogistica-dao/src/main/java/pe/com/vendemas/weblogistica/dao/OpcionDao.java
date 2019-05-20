package pe.com.vendemas.weblogistica.dao;

import java.util.List;

import pe.com.vendemas.weblogistica.domain.Opcion;

public interface OpcionDao extends GenericDao<Opcion>{
	
	//CRUD BASICO
//	Se hereda del GenericDao
	
	public List<Opcion> getOpcionesByUsuario(Integer cod_usuario);
	public List<Opcion> getOpcionesByRol(Integer idRol);
	
}
