package pe.com.vendemas.weblogistica.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.consult.StprEstadoCatologo;
import pe.com.vendemas.weblogistica.dao.EstadoCatalogoDao;
import pe.com.vendemas.weblogistica.dao.util.AbstractDao;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "estadoCatalogoDao")
public class EstadoCatalogoDaoImpl extends AbstractDao implements EstadoCatalogoDao {
	
	private static final Logger logger = LogManager.getLogger(EstadoCatalogoDaoImpl.class);
	
	@Override
	public StprEstadoCatologo getEstadoIds(StprEstadoCatologo entity) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ESTADO_CATALOGO_OBTENER_IDS;
		String sql_message = null;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		StprEstadoCatologo result = null;
		int i=1;
		try {
			
			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(i++, entity.getDescripcionCatalogo());
			csmt.setString(i++, entity.getDescripcionItem());
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			conn.commit();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			result = entity;
			result.setIdItem(csmt.getInt(i-1));
			result.setIdCatalogo(csmt.getInt(i-2));
			
		}catch(SQLException e) {
			logger.error(ErrorUtil.formatError(e,sql_message));
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(conn);
		}
		
		return result;
	}

}
