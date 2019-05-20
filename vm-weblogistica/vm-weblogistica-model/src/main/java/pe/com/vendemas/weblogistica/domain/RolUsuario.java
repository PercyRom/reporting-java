package pe.com.vendemas.weblogistica.domain;

public class RolUsuario {
	
	private Integer idRol;
	private Integer idUsuario;
	private String vigente;
	private String UsuarioSession;
	
	private String roles;
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getUsuarioSession() {
		return UsuarioSession;
	}
	public void setUsuarioSession(String usuarioSession) {
		UsuarioSession = usuarioSession;
	}
	
}
