package pe.com.vendemas.weblogistica.domain;

public class RolOpcion {
	
	private Integer idRol;
	private Integer idOpcion;
	private String vigente;
	private String UsuarioSession;
	
	private String opciones;
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public Integer getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public String getUsuarioSession() {
		return UsuarioSession;
	}
	public void setUsuarioSession(String usuarioSession) {
		UsuarioSession = usuarioSession;
	}
	public String getOpciones() {
		return opciones;
	}
	public void setOpciones(String opciones) {
		this.opciones = opciones;
	}

}
