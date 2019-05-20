package pe.com.vendemas.weblogistica.domain;

public class Entrada {
	
	private Integer idEntrada;
	private Integer idTipoMovimiento;
	private Integer idAlmacenDestino;
	private Integer idEstadoCatalogo;
	private Integer idEstadoItem;
	private String nroGuiaRemision;
	private String mposUnico;
	private String serieInicial;
	private String serieFinal;
	private String fechaPlanificado;
	private String fechaReal;
	private String flagSeedStock;
	private Integer cantidad;
	private Integer cantidadSeedStock;
	private Integer idCourier;
	private Integer idCompra;
	private Integer idAveria;
	private Integer idDevolucion;
	private String eliminado;
	private String usuarioSession;
	public Integer getIdEntrada() {
		return idEntrada;
	}
	public void setIdEntrada(Integer idEntrada) {
		this.idEntrada = idEntrada;
	}
	public Integer getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(Integer idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}
	public Integer getIdAlmacenDestino() {
		return idAlmacenDestino;
	}
	public void setIdAlmacenDestino(Integer idAlmacenDestino) {
		this.idAlmacenDestino = idAlmacenDestino;
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
	public String getNroGuiaRemision() {
		return nroGuiaRemision;
	}
	public void setNroGuiaRemision(String nroGuiaRemision) {
		this.nroGuiaRemision = nroGuiaRemision;
	}
	public String getMposUnico() {
		return mposUnico;
	}
	public void setMposUnico(String mposUnico) {
		this.mposUnico = mposUnico;
	}
	public String getSerieInicial() {
		return serieInicial;
	}
	public void setSerieInicial(String serieInicial) {
		this.serieInicial = serieInicial;
	}
	public String getSerieFinal() {
		return serieFinal;
	}
	public void setSerieFinal(String serieFinal) {
		this.serieFinal = serieFinal;
	}
	public String getFechaPlanificado() {
		return fechaPlanificado;
	}
	public void setFechaPlanificado(String fechaPlanificado) {
		this.fechaPlanificado = fechaPlanificado;
	}
	public String getFechaReal() {
		return fechaReal;
	}
	public void setFechaReal(String fechaReal) {
		this.fechaReal = fechaReal;
	}
	public String getFlagSeedStock() {
		return flagSeedStock;
	}
	public void setFlagSeedStock(String flagSeedStock) {
		this.flagSeedStock = flagSeedStock;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getCantidadSeedStock() {
		return cantidadSeedStock;
	}
	public void setCantidadSeedStock(Integer cantidadSeedStock) {
		this.cantidadSeedStock = cantidadSeedStock;
	}
	public Integer getIdCourier() {
		return idCourier;
	}
	public void setIdCourier(Integer idCourier) {
		this.idCourier = idCourier;
	}
	public Integer getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}
	public Integer getIdAveria() {
		return idAveria;
	}
	public void setIdAveria(Integer idAveria) {
		this.idAveria = idAveria;
	}
	public Integer getIdDevolucion() {
		return idDevolucion;
	}
	public void setIdDevolucion(Integer idDevolucion) {
		this.idDevolucion = idDevolucion;
	}
	public String getUsuarioSession() {
		return usuarioSession;
	}
	public void setUsuarioSession(String usuarioSession) {
		this.usuarioSession = usuarioSession;
	}
	public String getEliminado() {
		return eliminado;
	}
	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}
	
}
