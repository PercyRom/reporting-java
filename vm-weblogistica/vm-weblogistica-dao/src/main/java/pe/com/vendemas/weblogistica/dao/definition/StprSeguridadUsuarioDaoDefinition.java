package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.consult.StprSeguridadUsuario;
import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;

@Repository("stprSeguridadUsuarioDaoDefinition")
public class StprSeguridadUsuarioDaoDefinition extends DaoDefinition<StprSeguridadUsuario>{

	public StprSeguridadUsuarioDaoDefinition() {
		super(StprSeguridadUsuario.class);
	}
	
	@Override
	public StprSeguridadUsuario mapRow(ResultSet rs, int rowNumber) throws SQLException {
		StprSeguridadUsuario objResultado = super.mapRow(rs, rowNumber);
		return objResultado;
	}
}
