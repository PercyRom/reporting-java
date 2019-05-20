package pe.com.vendemas.weblogistica.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.TipoProductoDao;
import pe.com.vendemas.weblogistica.dao.definition.TipoProductoDaoDefinition;
import pe.com.vendemas.weblogistica.dao.util.AbstractDao;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.TipoProducto;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "tipoProductoDao")
public class TipoProductoDaoImpl extends AbstractDao implements TipoProductoDao {
	
	private static final Logger logger = LogManager.getLogger(TipoProductoDaoImpl.class);
	
	@Autowired
	@Qualifier("tipoProductoDaoDefinition")
	private TipoProductoDaoDefinition tipoProductoDaoDefinition;
	
	@Override
	public Integer create(TipoProducto entity) {
		return null;
	}

	@Override
	public TipoProducto getById(TipoProducto entity) {
		return null;
	}

	@Override
	public void update(TipoProducto entity) {

	}

	@Override
	public List<TipoProducto> getAll() {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_TIPO_PRODUCTO_LISTAR_VIGENTES;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<TipoProducto> result = null;
		TipoProducto obj = null;
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
			result = new ArrayList<TipoProducto>();
			
			int rowNum = 0;
			while(rs.next()) {
				obj = tipoProductoDaoDefinition.mapRow(rs, rowNum);
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

	@Override
	public List<TipoProducto> getList(TipoProducto entity) {
		return null;
	}

}
