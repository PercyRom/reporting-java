package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;
import pe.com.vendemas.weblogistica.domain.TipoEntidad;

@Repository("tipoEntidadDaoDefinition")
public class TipoEntidadDaoDefinition extends DaoDefinition<TipoEntidad> { 
	
	public TipoEntidadDaoDefinition() {
		super(TipoEntidad.class);
	}

	@Override
	public TipoEntidad mapRow(ResultSet rs, int rowNumber) throws SQLException {
		TipoEntidad tipoEntidad = super.mapRow(rs, rowNumber);
		return tipoEntidad;
	}

}