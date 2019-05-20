package pe.com.vendemas.weblogistica.dao;

import java.util.List;

import pe.com.vendemas.weblogistica.consult.StprEntidadContactos;
import pe.com.vendemas.weblogistica.domain.EntidadContactos;

public interface EntidadContactosDao extends GenericDao<EntidadContactos>{
	
	public List<StprEntidadContactos> getListByEntidad(Integer idEntidad);
	
	public Integer save(EntidadContactos contactos);
	
}
