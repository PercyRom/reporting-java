package pe.com.vendemas.weblogistica.dao;

import java.util.List;

import pe.com.vendemas.weblogistica.domain.Entrada;

public interface EntradaDao extends GenericDao<Entrada>{
	
	public void save(List<Entrada> entradas);
	
}
