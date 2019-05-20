package pe.com.vendemas.weblogistica.filterBean;

public abstract class PaginadoFilter {
	
	private Integer numPagina;
	private Integer cantidadFilasPagina;
	
	private Integer limit;
	private Integer offset;
	
	public Integer getNumPagina() {
		return numPagina;
	}
	public void setNumPagina(Integer numPagina) {
		this.numPagina = numPagina;
	}
	public Integer getCantidadFilasPagina() {
		return cantidadFilasPagina;
	}
	public void setCantidadFilasPagina(Integer cantidadFilasPagina) {
		this.cantidadFilasPagina = cantidadFilasPagina;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	
	
}
