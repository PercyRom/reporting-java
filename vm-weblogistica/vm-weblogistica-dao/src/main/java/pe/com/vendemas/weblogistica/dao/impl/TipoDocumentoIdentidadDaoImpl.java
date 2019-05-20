package pe.com.vendemas.weblogistica.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.TipoDocumentoIdentidadDao;
import pe.com.vendemas.weblogistica.dao.definition.TipoDocumentoIdentidadDaoDefinition;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.TipoDocumentoIdentidad;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "tipoDocumentoIdentidadDao")
public class TipoDocumentoIdentidadDaoImpl implements TipoDocumentoIdentidadDao{

private static final Logger logger = LogManager.getLogger(TipoDocumentoIdentidadDaoImpl.class);
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private  DataSource dataSource;
	
	@Autowired
	@Qualifier("tipoDocumentoIdentidadDaoDefinition")
	private TipoDocumentoIdentidadDaoDefinition tipoDocumentoIdentidadDaoDefinition;
	
	@Override
	public List<TipoDocumentoIdentidad> getAll() {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_TIPO_DOC_IDENTIDAD_LISTAR;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<TipoDocumentoIdentidad> result = null;
		TipoDocumentoIdentidad obj = null;
		int i=1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
			result = new ArrayList<TipoDocumentoIdentidad>();
			
			int rowNum = 0;
			while(rs.next()) {
				obj = tipoDocumentoIdentidadDaoDefinition.mapRow(rs, rowNum);
				result.add(obj);
			    rowNum++;
			}
		
		}catch(SQLException e) {
			logger.error(ErrorUtil.formatError(e,sql_message));
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(rs);
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(conn);
		}
		
		return result;
	}

}
