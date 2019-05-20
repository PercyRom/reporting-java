package pe.com.vendemas.weblogistica.consult;

import java.util.List;

public class StprPaginado {
	
	private Integer totalObjetosBD;
	private Integer totalPaginas;
	private List<?> listObjetos;
	
	
	public Integer getTotalObjetosBD() {
		return totalObjetosBD;
	}
	public void setTotalObjetosBD(Integer totalObjetosBD) {
		this.totalObjetosBD = totalObjetosBD;
	}
	public Integer getTotalPaginas() {
		return totalPaginas;
	}
	public void setTotalPaginas(Integer totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	public List<?> getListObjetos() {
		return listObjetos;
	}
	public void setListObjetos(List<?> listObjetos) {
		this.listObjetos = listObjetos;
	}
	
}
