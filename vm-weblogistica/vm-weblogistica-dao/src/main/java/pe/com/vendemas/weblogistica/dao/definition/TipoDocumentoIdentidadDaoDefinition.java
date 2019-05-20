package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;
import pe.com.vendemas.weblogistica.domain.TipoDocumentoIdentidad;

@Repository("tipoDocumentoIdentidadDaoDefinition")
public class TipoDocumentoIdentidadDaoDefinition extends DaoDefinition<TipoDocumentoIdentidad> {
	
	public TipoDocumentoIdentidadDaoDefinition() {
		super(TipoDocumentoIdentidad.class);
	}

	@Override
	public TipoDocumentoIdentidad mapRow(ResultSet rs, int rowNumber) throws SQLException {
		TipoDocumentoIdentidad tipoDocumentoIdentidad = super.mapRow(rs, rowNumber);
		return tipoDocumentoIdentidad;
	}

}
