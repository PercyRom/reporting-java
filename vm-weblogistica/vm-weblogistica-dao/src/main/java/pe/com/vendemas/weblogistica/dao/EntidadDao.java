package pe.com.vendemas.weblogistica.dao;

import java.util.List;

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.domain.Entidad;
import pe.com.vendemas.weblogistica.filterBean.EntidadFilter;

public interface EntidadDao {
	
	public StprPaginado getEntidadPaginado(EntidadFilter filter);

	public Integer entidadSave(Entidad entidad);

	public Entidad getEntidadById(Integer identidad);

	public Integer entidadUpdate(Entidad entidad);
	
	public List<Entidad> getEntidadByTipo(Integer idTipoEntidad);
}