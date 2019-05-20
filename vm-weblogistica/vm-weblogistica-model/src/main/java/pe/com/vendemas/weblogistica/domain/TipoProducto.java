package pe.com.vendemas.weblogistica.domain;

public class TipoProducto {
	
	private Integer idTipoProducto;
	private String nombreproducto;
	private Integer orden;
	private String vigente;
	private String usuarioSession;
	
	public Integer getIdTipoProducto() {
		return idTipoProducto;
	}
	public void setIdTipoProducto(Integer idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}
	public String getNombreproducto() {
		return nombreproducto;
	}
	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public String getUsuarioSession() {
		return usuarioSession;
	}
	public void setUsuarioSession(String usuarioSession) {
		this.usuarioSession = usuarioSession;
	}
	
}
