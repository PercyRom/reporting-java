package pe.com.vendemas.weblogistica.consult;

public class StprEstadoCatologo {
	
	private Integer idCatalogo;
	private String descripcionCatalogo;
	private Integer idItem;
	private String descripcionItem;
	
	public StprEstadoCatologo(String descripcionCatalogo, String descripcionItem) {
		this.descripcionCatalogo = descripcionCatalogo;
		this.descripcionItem = descripcionItem;
	}
	
	public StprEstadoCatologo(Integer idCatalogo, Integer idItem) {
		this.idCatalogo = idCatalogo;
		this.idItem = idItem;
	}
	
	public Integer getIdCatalogo() {
		return idCatalogo;
	}
	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}
	public String getDescripcionCatalogo() {
		return descripcionCatalogo;
	}
	public void setDescripcionCatalogo(String descripcionCatalogo) {
		this.descripcionCatalogo = descripcionCatalogo;
	}
	public Integer getIdItem() {
		return idItem;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	public String getDescripcionItem() {
		return descripcionItem;
	}
	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}
	
}
