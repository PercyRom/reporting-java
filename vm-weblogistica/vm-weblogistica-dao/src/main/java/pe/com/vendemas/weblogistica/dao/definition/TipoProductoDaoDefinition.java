package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;
import pe.com.vendemas.weblogistica.domain.TipoProducto;

@Repository("tipoProductoDaoDefinition")
public class TipoProductoDaoDefinition extends DaoDefinition<TipoProducto> {

	public TipoProductoDaoDefinition() {
		super(TipoProducto.class);
	}
	
	@Override
	public TipoProducto mapRow(ResultSet rs, int rowNumber) throws SQLException {
		TipoProducto objResultado = super.mapRow(rs, rowNumber);
		return objResultado;
	}
}