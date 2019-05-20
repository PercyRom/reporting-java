package pe.com.vendemas.weblogistica.domain;

public class EntidadContactos {
	
	private Integer idEntidadContacto;
	private Integer idEntidad;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	private Integer idTipoDocumentoIdentidad;
	private String documentoIdentidad;
	private String telefonoContacto;
	private String vigente;
	private String usuarioSession;
	
	private boolean editado;
		
	public boolean isEditado() {
		return editado;
	}
	public void setEditado(boolean editado) {
		this.editado = editado;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public Integer getIdEntidadContacto() {
		return idEntidadContacto;
	}
	public void setIdEntidadContacto(Integer idEntidadContacto) {
		this.idEntidadContacto = idEntidadContacto;
	}
	public Integer getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(Integer idEntidad) {
		this.idEntidad = idEntidad;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public Integer getIdTipoDocumentoIdentidad() {
		return idTipoDocumentoIdentidad;
	}
	public void setIdTipoDocumentoIdentidad(Integer idTipoDocumentoIdentidad) {
		this.idTipoDocumentoIdentidad = idTipoDocumentoIdentidad;
	}
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public String getUsuarioSession() {
		return usuarioSession;
	}
	public void setUsuarioSession(String usuarioSession) {
		this.usuarioSession = usuarioSession;
	}

}
