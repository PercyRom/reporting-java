package pe.com.vendemas.weblogistica.seguridad.bean;

import java.io.Serializable;
import java.util.List;

import pe.com.vendemas.weblogistica.domain.Opcion;

public class UsuarioWebBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String clave;
	
	private List<Opcion> lstOpciones;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public List<Opcion> getLstOpciones() {
		return lstOpciones;
	}
	public void setLstOpciones(List<Opcion> lstOpciones) {
		this.lstOpciones = lstOpciones;
	}
}