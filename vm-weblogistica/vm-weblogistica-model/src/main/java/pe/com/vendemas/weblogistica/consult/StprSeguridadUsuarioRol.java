package pe.com.vendemas.weblogistica.consult;

public class StprSeguridadUsuarioRol {

	private Integer idUsuarioAsignadoInicial;
	private Integer idUsuarioAsignado;
	private Integer idRol;
	private String nombreRol;
	private String vigente;
	private String vigenteInicial;
	
	public Integer getIdUsuarioAsignadoInicial() {
		return idUsuarioAsignadoInicial;
	}
	public void setIdUsuarioAsignadoInicial(Integer idUsuarioAsignadoInicial) {
		this.idUsuarioAsignadoInicial = idUsuarioAsignadoInicial;
	}
	public Integer getIdUsuarioAsignado() {
		return idUsuarioAsignado;
	}
	public void setIdUsuarioAsignado(Integer idUsuarioAsignado) {
		this.idUsuarioAsignado = idUsuarioAsignado;
	}
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public String getVigenteInicial() {
		return vigenteInicial;
	}
	public void setVigenteInicial(String vigenteInicial) {
		this.vigenteInicial = vigenteInicial;
	}

}