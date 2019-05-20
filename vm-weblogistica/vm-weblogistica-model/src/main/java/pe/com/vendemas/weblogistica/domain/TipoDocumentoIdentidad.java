package pe.com.vendemas.weblogistica.domain;

import java.io.Serializable;

public class TipoDocumentoIdentidad implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idtipodocident;
	private String nombretipodocident;
	private String orden;
	private String vigente;
	private String idusuariocreacion;
	private String fechacreacion;
	private String idusuariomodifica;
	private String fechamodifica;
	
	public Integer getIdtipodocident() {
		return idtipodocident;
	}
	public void setIdtipodocident(Integer idtipodocident) {
		this.idtipodocident = idtipodocident;
	}
	public String getNombretipodocident() {
		return nombretipodocident;
	}
	public void setNombretipodocident(String nombretipodocident) {
		this.nombretipodocident = nombretipodocident;
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
	public String getIdusuariocreacion() {
		return idusuariocreacion;
	}
	public void setIdusuariocreacion(String idusuariocreacion) {
		this.idusuariocreacion = idusuariocreacion;
	}
	public String getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(String fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public String getIdusuariomodifica() {
		return idusuariomodifica;
	}
	public void setIdusuariomodifica(String idusuariomodifica) {
		this.idusuariomodifica = idusuariomodifica;
	}
	public String getFechamodifica() {
		return fechamodifica;
	}
	public void setFechamodifica(String fechamodifica) {
		this.fechamodifica = fechamodifica;
	}
}
