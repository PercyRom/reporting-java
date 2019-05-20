package pe.com.vendemas.weblogistica.dao.definition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.util.DaoDefinition;
import pe.com.vendemas.weblogistica.domain.Usuario;

@Repository("usuarioDaoDefinition")
public class UsuarioDaoDefinition extends DaoDefinition<Usuario> {

	public UsuarioDaoDefinition() {
		super(Usuario.class);
	}
	
	@Override
	public Usuario mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Usuario usuario = super.mapRow(rs, rowNumber);
		return usuario;
	}

}