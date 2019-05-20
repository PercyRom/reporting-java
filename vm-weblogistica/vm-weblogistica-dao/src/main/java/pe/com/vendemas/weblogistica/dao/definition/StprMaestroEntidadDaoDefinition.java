package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.consult.StprMaestroEntidad;
import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;

@Repository("stprMaestroEntidadDaoDefinition")
public class StprMaestroEntidadDaoDefinition  extends DaoDefinition<StprMaestroEntidad>{

	public StprMaestroEntidadDaoDefinition() {
		super(StprMaestroEntidad.class);
	}
	
	@Override
	public StprMaestroEntidad mapRow(ResultSet rs, int rowNumber) throws SQLException {
		StprMaestroEntidad objResultado = super.mapRow(rs, rowNumber);
		return objResultado;
	}
}