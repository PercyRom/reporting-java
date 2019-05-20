package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.consult.StprSeguridadRolOpcion;
import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;

@Repository("stprSeguridadRolOpcionDaoDefinition")
public class StprSeguridadRolOpcionDaoDefinition extends DaoDefinition<StprSeguridadRolOpcion> {

	public StprSeguridadRolOpcionDaoDefinition() {
		super(StprSeguridadRolOpcion.class);
	}
	
	@Override
	public StprSeguridadRolOpcion mapRow(ResultSet rs, int rowNumber) throws SQLException {
		StprSeguridadRolOpcion objResultado = super.mapRow(rs, rowNumber);
		return objResultado;
	}
}