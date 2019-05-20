package pe.com.vendemas.weblogistica.domain;

import java.util.List;

public class Entidad {
	
	private Integer idEntidad;
	private String idTipoEntidad;
	private String nombreEntidad;
	private String identificadorFacturacion;
	private String nombreComercial;
	private String codigoPais;
	private String codigoNbo;
	private String idUsuarioCreacion;
	private String fechaCreacion;
	private String idUsuarioModifica;
	private String fechaModifica;
	private String vigente;

	private String usuarioSession;
	private String nombreTipoEntidad;
	
	private List<EntidadContactos> contactos;

	public String getNombreTipoEntidad() {
		return nombreTipoEntidad;
	}

	public void setNombreTipoEntidad(String nombreTipoEntidad) {
		this.nombreTipoEntidad = nombreTipoEntidad;
	}

	public Integer getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Integer idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getIdTipoEntidad() {
		return idTipoEntidad;
	}

	public void setIdTipoEntidad(String idTipoEntidad) {
		this.idTipoEntidad = idTipoEntidad;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String getIdentificadorFacturacion() {
		return identificadorFacturacion;
	}

	public void setIdentificadorFacturacion(String identificadorFacturacion) {
		this.identificadorFacturacion = identificadorFacturacion;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoNbo() {
		return codigoNbo;
	}

	public void setCodigoNbo(String codigoNbo) {
		this.codigoNbo = codigoNbo;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdUsuarioCreacion() {
		return idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(String idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	public String getUsuarioSession() {
		return usuarioSession;
	}

	public void setUsuarioSession(String usuarioSession) {
		this.usuarioSession = usuarioSession;
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

	public String getVigente() {
		return vigente;
	}

	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	
	public List<EntidadContactos> getContactos() {
		return contactos;
	}

	public void setContactos(List<EntidadContactos> contactos) {
		this.contactos = contactos;
	}
}
