package pe.com.vendemas.weblogistica.service;

import java.util.List;

import pe.com.vendemas.weblogistica.consult.StprEntidadContactos;
import pe.com.vendemas.weblogistica.domain.EntidadContactos;

public interface EntidadContactosService {
	
	public List<StprEntidadContactos> listContactosByEntidad(Integer idEntidad);
	
	public Integer entidadContactoSave(EntidadContactos contactos);
	
}
