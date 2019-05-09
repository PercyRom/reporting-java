package com.tracking.guia.remision.bean;

import java.io.Serializable;
import java.util.List;

public class BeanCabGuiaRemision implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String departamento;
	private String provincia;
	private String distrito;
	private String nombreVia;
	private String numeroVia;
	private String referencia;
	private String nombreContacto;
	private String telefonoContacto;
	private String tipoPago;
	private String nombreUsuario;
	private String telefonoUsuario;
	private String rucComercio;
	private String razonSocial;
	private String IDVISANET;
	private String cantidadItems;
	private String Monto;
	private String DNI;
	private String tipoDocumentoPago;
	private String emailRepresentante;
	private String idTipoVenta;
	private String idCanal;
	
	private List<BeanDetGuiaRemision> items;

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	public String getNumeroVia() {
		return numeroVia;
	}

	public void setNumeroVia(String numeroVia) {
		this.numeroVia = numeroVia;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}

	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	public String getRucComercio() {
		return rucComercio;
	}

	public void setRucComercio(String rucComercio) {
		this.rucComercio = rucComercio;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getIDVISANET() {
		return IDVISANET;
	}

	public void setIDVISANET(String iDVISANET) {
		IDVISANET = iDVISANET;
	}

	public String getCantidadItems() {
		return cantidadItems;
	}

	public void setCantidadItems(String cantidadItems) {
		this.cantidadItems = cantidadItems;
	}

	public String getMonto() {
		return Monto;
	}

	public void setMonto(String monto) {
		Monto = monto;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getTipoDocumentoPago() {
		return tipoDocumentoPago;
	}

	public void setTipoDocumentoPago(String tipoDocumentoPago) {
		this.tipoDocumentoPago = tipoDocumentoPago;
	}

	public String getEmailRepresentante() {
		return emailRepresentante;
	}

	public void setEmailRepresentante(String emailRepresentante) {
		this.emailRepresentante = emailRepresentante;
	}

	public String getIdTipoVenta() {
		return idTipoVenta;
	}

	public void setIdTipoVenta(String idTipoVenta) {
		this.idTipoVenta = idTipoVenta;
	}

	public String getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}

	public List<BeanDetGuiaRemision> getItems() {
		return items;
	}

	public void setItems(List<BeanDetGuiaRemision> items) {
		this.items = items;
	}

}
