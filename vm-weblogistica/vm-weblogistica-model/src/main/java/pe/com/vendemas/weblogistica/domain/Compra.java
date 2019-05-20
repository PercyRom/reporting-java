package pe.com.vendemas.weblogistica.domain;

import java.util.List;

public class Compra {
	
	private Integer idCompra;
	private Integer idTipoEntidadOrigen;
	private Integer idEntidadOrigen;
	private Integer idTipoProducto;
	private String nroOrdenCompra;
	
	private Integer cantidad;
	private Integer seedStock;
	private Integer idEstadoCatalogo;
	private Integer idEstadoItem;
	private String usuarioSession;
	
	private List<Entrada> entradas;
	
	public Integer getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}
	public Integer getIdTipoEntidadOrigen() {
		return idTipoEntidadOrigen;
	}
	public void setIdTipoEntidadOrigen(Integer idTipoEntidadOrigen) {
		this.idTipoEntidadOrigen = idTipoEntidadOrigen;
	}
	public Integer getIdEntidadOrigen() {
		return idEntidadOrigen;
	}
	public void setIdEntidadOrigen(Integer idEntidadOrigen) {
		this.idEntidadOrigen = idEntidadOrigen;
	}
	public Integer getIdTipoProducto() {
		return idTipoProducto;
	}
	public void setIdTipoProducto(Integer idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}
	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}
	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getSeedStock() {
		return seedStock;
	}
	public void setSeedStock(Integer seedStock) {
		this.seedStock = seedStock;
	}
	public Integer getIdEstadoCatalogo() {
		return idEstadoCatalogo;
	}
	public void setIdEstadoCatalogo(Integer idEstadoCatalogo) {
		this.idEstadoCatalogo = idEstadoCatalogo;
	}
	public Integer getIdEstadoItem() {
		return idEstadoItem;
	}
	public void setIdEstadoItem(Integer idEstadoItem) {
		this.idEstadoItem = idEstadoItem;
	}
	public String getUsuarioSession() {
		return usuarioSession;
	}
	public void setUsuarioSession(String usuarioSession) {
		this.usuarioSession = usuarioSession;
	}
	public List<Entrada> getEntradas() {
		return entradas;
	}
	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}
	
}
