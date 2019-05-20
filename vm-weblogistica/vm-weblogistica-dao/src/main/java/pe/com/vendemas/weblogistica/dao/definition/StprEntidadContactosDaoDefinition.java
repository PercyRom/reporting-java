package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.consult.StprEntidadContactos;
import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;

@Repository("stprEntidadContactosDaoDefinition")
public class StprEntidadContactosDaoDefinition extends DaoDefinition<StprEntidadContactos>{
	
	public StprEntidadContactosDaoDefinition() {
		super(StprEntidadContactos.class);
	}
	
	@Override
	public StprEntidadContactos mapRow(ResultSet rs, int rowNumber) throws SQLException {
		StprEntidadContactos objResultado = super.mapRow(rs, rowNumber);
		return objResultado;
	}
}
