package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;
import pe.com.vendemas.weblogistica.domain.RolUsuario;

@Repository("rolUsuarioDaoDefinition")
public class RolUsuarioDaoDefinition extends DaoDefinition<RolUsuario> { 
	
	public RolUsuarioDaoDefinition() {
		super(RolUsuario.class);
	}

	@Override
	public RolUsuario mapRow(ResultSet rs, int rowNumber) throws SQLException {
		RolUsuario rolUsuario = super.mapRow(rs, rowNumber);
		return rolUsuario;
	}

}