package pe.com.vendemas.weblogistica.domain;

import java.io.Serializable;

public class Opcion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idOpcion;
	private Integer idPadre;
	private String nombreOpcion;
	private String linkOpcion;
	private String vigente;
	private String esMenu;
	private String orden;
	private String icono;
	
	public Integer getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	public String getNombreOpcion() {
		return nombreOpcion;
	}
	public void setNombreOpcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
	}
	public String getLinkOpcion() {
		return linkOpcion;
	}
	public void setLinkOpcion(String linkOpcion) {
		this.linkOpcion = linkOpcion;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public String getEsMenu() {
		return esMenu;
	}
	public void setEsMenu(String esMenu) {
		this.esMenu = esMenu;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}	
}