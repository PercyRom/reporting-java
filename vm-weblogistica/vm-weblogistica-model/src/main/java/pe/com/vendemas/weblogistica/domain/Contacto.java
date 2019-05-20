package pe.com.vendemas.weblogistica.domain;

import java.io.Serializable;

public class Contacto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String idEntidadContacto;
	private String idEntidad;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	private String idTipoDocumentoIdentidad;
	private String documentoIdentidad;
	private String telefonoContacto;
	private String idUsuarioCreacion;
	private String fechaCreacion;
	private String idUsuarioModifica;
	private String fechaModifica;
	
	public String getIdEntidadContacto() {
		return idEntidadContacto;
	}
	public void setIdEntidadContacto(String idEntidadContacto) {
		this.idEntidadContacto = idEntidadContacto;
	}
	public String getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(String idEntidad) {
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
	public String getIdTipoDocumentoIdentidad() {
		return idTipoDocumentoIdentidad;
	}
	public void setIdTipoDocumentoIdentidad(String idTipoDocumentoIdentidad) {
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
	public String getIdUsuarioCreacion() {
		return idUsuarioCreacion;
	}
	public void setIdUsuarioCreacion(String idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getIdUsuarioModifica() {
		return idUsuarioModifica;
	}
	public void setIdUsuarioModifica(String idUsuarioModifica) {
		this.idUsuarioModifica = idUsuarioModifica;
	}
	public String getFechaModifica() {
		return fechaModifica;
	}
	public void setFechaModifica(String fechaModifica) {
		this.fechaModifica = fechaModifica;
	}
	
	
}
