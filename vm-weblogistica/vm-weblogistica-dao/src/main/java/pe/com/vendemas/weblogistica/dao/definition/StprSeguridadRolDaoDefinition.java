package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.consult.StprSeguridadRol;
import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;

@Repository("stprSeguridadRolDaoDefinition")
public class StprSeguridadRolDaoDefinition  extends DaoDefinition<StprSeguridadRol>{

	public StprSeguridadRolDaoDefinition() {
		super(StprSeguridadRol.class);
	}
	
	@Override
	public StprSeguridadRol mapRow(ResultSet rs, int rowNumber) throws SQLException {
		StprSeguridadRol objResultado = super.mapRow(rs, rowNumber);
		return objResultado;
	}
}