package pe.com.vendemas.weblogistica.domain;

import java.io.Serializable;

public class TipoEntidad implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idTipoEntidad;
	private String nombreTipoEntidad;
	private String orden;
	private String vigente;
	private String idUsuarioCreacion;
	private String fechaCreacion;
	private String idUsuarioModifica;
	private String fechaModifica;
	public Integer getIdTipoEntidad() {
		return idTipoEntidad;
	}
	public void setIdTipoEntidad(Integer idTipoEntidad) {
		this.idTipoEntidad = idTipoEntidad;
	}
	public String getNombreTipoEntidad() {
		return nombreTipoEntidad;
	}
	public void setNombreTipoEntidad(String nombreTipoEntidad) {
		this.nombreTipoEntidad = nombreTipoEntidad;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
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
