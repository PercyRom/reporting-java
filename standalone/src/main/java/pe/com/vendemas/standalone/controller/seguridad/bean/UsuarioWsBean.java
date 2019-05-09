package pe.com.vendemas.standalone.controller.seguridad.bean;

import java.io.Serializable;

public class UsuarioWsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String clave;
	
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

}