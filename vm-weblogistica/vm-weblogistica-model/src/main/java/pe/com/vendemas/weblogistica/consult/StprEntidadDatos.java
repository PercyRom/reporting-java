package pe.com.vendemas.weblogistica.consult;

import java.util.List;

import pe.com.vendemas.weblogistica.domain.Entidad;

public class StprEntidadDatos {
	
	private Entidad entidad;
	private List<StprEntidadContactos> listEntidadContactos;
	
	public Entidad getEntidad() {
		return entidad;
	}
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	public List<StprEntidadContactos> getListEntidadContactos() {
		return listEntidadContactos;
	}
	public void setListEntidadContactos(List<StprEntidadContactos> listEntidadContactos) {
		this.listEntidadContactos = listEntidadContactos;
	}
	
}
