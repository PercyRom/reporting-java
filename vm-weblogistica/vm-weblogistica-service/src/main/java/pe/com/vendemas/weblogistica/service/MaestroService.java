package pe.com.vendemas.weblogistica.service;

import java.util.List;

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.domain.Entidad;
import pe.com.vendemas.weblogistica.domain.TipoDocumentoIdentidad;
import pe.com.vendemas.weblogistica.domain.TipoEntidad;
import pe.com.vendemas.weblogistica.filterBean.EntidadFilter;

public interface MaestroService {
	
	public StprPaginado listarEntidades(EntidadFilter filter);
	
	public Entidad entidadSave(Entidad entidad);
	
	public List<Entidad> listarEntidadesPorTipo(Integer idTipoEntidad);
	
	public Entidad getEntidadById(Integer identidad);
	
	public List<TipoEntidad> getTiposEntidad();
	
	public List<TipoDocumentoIdentidad> getTipoDocumentoIdentidad();
	
}
	