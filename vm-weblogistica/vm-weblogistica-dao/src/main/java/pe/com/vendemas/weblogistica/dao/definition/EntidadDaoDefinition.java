package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;
import pe.com.vendemas.weblogistica.domain.Entidad;

@Repository("entidadDaoDefinition")
public class EntidadDaoDefinition extends DaoDefinition<Entidad> { 
	
	public EntidadDaoDefinition() {
		super(Entidad.class);
	}

	@Override
	public Entidad mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Entidad rol = super.mapRow(rs, rowNumber);
		return rol;
	}

}