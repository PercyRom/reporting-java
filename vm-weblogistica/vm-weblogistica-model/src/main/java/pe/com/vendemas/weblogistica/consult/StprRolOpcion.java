package pe.com.vendemas.weblogistica.consult;

import java.util.List;

import pe.com.vendemas.weblogistica.domain.Opcion;
import pe.com.vendemas.weblogistica.domain.Rol;

public class StprRolOpcion {
	
	private Rol rol;
	private List<Opcion> listOpciones;
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public List<Opcion> getListOpciones() {
		return listOpciones;
	}
	public void setListOpciones(List<Opcion> listOpciones) {
		this.listOpciones = listOpciones;
	}
	
	
	
}
