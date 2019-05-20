package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;
import pe.com.vendemas.weblogistica.domain.Rol;

@Repository("rolDaoDefinition")
public class RolDaoDefinition extends DaoDefinition<Rol> { 
	
	public RolDaoDefinition() {
		super(Rol.class);
	}

	@Override
	public Rol mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Rol rol = super.mapRow(rs, rowNumber);
		return rol;
	}

}