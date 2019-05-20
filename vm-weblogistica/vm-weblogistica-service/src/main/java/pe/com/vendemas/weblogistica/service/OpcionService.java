package pe.com.vendemas.weblogistica.service;

import java.util.List;

import pe.com.vendemas.weblogistica.domain.Opcion;

public interface OpcionService {
	
	//CRUD BASICO
	public Integer registrarOpcion(Opcion opcion);
	public List<Opcion> obtenerOpciones();
	public void actualizarOpcion(Opcion opcion);
	
	public List<Opcion> obtenerOpcionesByRol(Integer idRol);
	
}
