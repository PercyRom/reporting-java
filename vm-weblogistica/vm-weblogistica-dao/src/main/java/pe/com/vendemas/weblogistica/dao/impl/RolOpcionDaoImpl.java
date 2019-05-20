package pe.com.vendemas.weblogistica.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.RolOpcionDao;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.RolOpcion;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "rolOpcionDao")
public class RolOpcionDaoImpl implements RolOpcionDao {
	
	private static final Logger logger = LogManager.getLogger(RolOpcionDaoImpl.class);
	
	@Autowired
	private  DataSource dataSource;
		
	@Override
	public Integer createRolOpcion(RolOpcion obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RolOpcion> getRolOpciones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRolOpcion(RolOpcion obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mantRolOpcion(RolOpcion obj) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ROL_OPCION_MANT;
		String sql_message = null;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		int i=1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, obj.getIdRol());
			csmt.setString(i++, obj.getOpciones());
			csmt.setString(i++, obj.getUsuarioSession());
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			conn.commit();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
		}catch(SQLException e) {
			logger.error(ErrorUtil.formatError(e,sql_message));
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(conn);
		}
	}

}
