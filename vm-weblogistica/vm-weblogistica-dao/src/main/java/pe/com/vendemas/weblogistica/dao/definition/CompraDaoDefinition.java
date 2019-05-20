package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;
import pe.com.vendemas.weblogistica.domain.Compra;

@Repository("compraDaoDefinition")
public class CompraDaoDefinition extends DaoDefinition<Compra> {

	public CompraDaoDefinition() {
		super(Compra.class);
	}
	
	@Override
	public Compra mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Compra objResultado = super.mapRow(rs, rowNumber);
		return objResultado;
	}

}
