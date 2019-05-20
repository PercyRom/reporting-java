package pe.com.vendemas.weblogistica.consult;

import java.io.Serializable;
import java.util.List;

import pe.com.vendemas.weblogistica.domain.Entidad;
import pe.com.vendemas.weblogistica.domain.TipoEntidad;

public class StprMaestroEntidad implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Entidad entidad;
	
	private List<TipoEntidad> tipoEntidad;

	public List<TipoEntidad> getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(List<TipoEntidad> tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	
	

}