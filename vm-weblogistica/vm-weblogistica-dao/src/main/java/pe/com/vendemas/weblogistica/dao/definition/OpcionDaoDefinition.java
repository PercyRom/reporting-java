package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;
import pe.com.vendemas.weblogistica.domain.Opcion;

@Repository("opcionDaoDefinition")
public class OpcionDaoDefinition extends DaoDefinition<Opcion> {

	public OpcionDaoDefinition() {
		super(Opcion.class);
	}
	
	@Override
	public Opcion mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Opcion objResultado = super.mapRow(rs, rowNumber);
		return objResultado;
	}
}