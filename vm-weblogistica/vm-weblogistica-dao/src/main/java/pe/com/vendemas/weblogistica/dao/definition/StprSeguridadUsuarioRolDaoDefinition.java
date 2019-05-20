package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.consult.StprSeguridadUsuarioRol;
import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;

@Repository("stprSeguridadUsuarioRolDaoDefinition")
public class StprSeguridadUsuarioRolDaoDefinition extends DaoDefinition<StprSeguridadUsuarioRol> {

	public StprSeguridadUsuarioRolDaoDefinition() {
		super(StprSeguridadUsuarioRol.class);
	}
	
	@Override
	public StprSeguridadUsuarioRol mapRow(ResultSet rs, int rowNumber) throws SQLException {
		StprSeguridadUsuarioRol objResultado = super.mapRow(rs, rowNumber);
		return objResultado;
	}
}