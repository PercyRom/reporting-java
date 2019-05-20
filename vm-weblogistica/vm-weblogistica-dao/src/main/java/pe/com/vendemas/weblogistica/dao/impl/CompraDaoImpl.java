package pe.com.vendemas.weblogistica.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.CompraDao;
import pe.com.vendemas.weblogistica.dao.util.AbstractDao;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.Compra;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "compraDao")
public class CompraDaoImpl extends AbstractDao implements CompraDao {
	
	private static final Logger logger = LogManager.getLogger(CompraDaoImpl.class);
	
	@Override
	public Integer create(Compra entity) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_COMPRAS_REGISTRAR;
		String sql_message = null;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		Integer result = null;
		int i=1;
		try {
		
			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?,?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, entity.getIdTipoEntidadOrigen());
			csmt.setInt(i++, entity.getIdEntidadOrigen());
			csmt.setInt(i++, entity.getIdTipoProducto());
			csmt.setString(i++, entity.getNroOrdenCompra());
			csmt.setInt(i++, entity.getCantidad());
			csmt.setInt(i++, entity.getSeedStock()==null?0:entity.getSeedStock());
			csmt.setInt(i++, entity.getIdEstadoCatalogo());
			csmt.setInt(i++, entity.getIdEstadoItem() );
			csmt.setString(i++, entity.getUsuarioSession());
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			conn.commit();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			result = (Integer) csmt.getObject(i-1);
			
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

	@Override
	public Compra getById(Compra entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Compra entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Compra> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compra> getList(Compra entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
