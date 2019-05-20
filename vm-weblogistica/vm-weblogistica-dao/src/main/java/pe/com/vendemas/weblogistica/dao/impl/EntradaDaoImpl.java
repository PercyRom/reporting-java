package pe.com.vendemas.weblogistica.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import pe.com.vendemas.weblogistica.dao.EntradaDao;
import pe.com.vendemas.weblogistica.dao.util.AbstractDao;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.Entrada;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "entradaDao")
public class EntradaDaoImpl extends AbstractDao implements EntradaDao {
	
	private static final Logger logger = LogManager.getLogger(EntradaDaoImpl.class);
	
	@Override
	public Integer create(Entrada entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entrada getById(Entrada entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Entrada entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Entrada> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entrada> getList(Entrada entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(List<Entrada> entradas) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ENTRADAS_REGISTRAR;
		String sql_message = null;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		Integer i=null;
		try {
		
			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)}";
			csmt = conn.prepareCall(commandText);
			
			for (Entrada entity : entradas) {
				i=1;
				csmt.setInt(i++, entity.getIdTipoMovimiento());
				csmt.setInt(i++, entity.getIdAlmacenDestino());
				csmt.setInt(i++, entity.getIdEstadoCatalogo());
				csmt.setInt(i++, entity.getIdEstadoItem());
				csmt.setString(i++, entity.getNroGuiaRemision());
				csmt.setString(i++, entity.getMposUnico());
				csmt.setString(i++, entity.getSerieInicial());
				csmt.setString(i++, entity.getSerieFinal());
				csmt.setString(i++, entity.getFechaPlanificado());
				csmt.setString(i++, entity.getFechaReal());
				csmt.setString(i++, entity.getFlagSeedStock());
				csmt.setInt(i++, entity.getCantidad());
				csmt.setInt(i++, entity.getCantidadSeedStock()==null?0:entity.getCantidadSeedStock() );
				csmt.setInt(i++, entity.getIdCourier());
				csmt.setInt(i++, entity.getIdCompra());
				csmt.setInt(i++, entity.getIdAveria()==null?0:entity.getIdAveria());
				csmt.setInt(i++, entity.getIdDevolucion()==null?0:entity.getIdDevolucion());
				csmt.setString(i++, entity.getUsuarioSession());
				csmt.setString(i++, entity.getEliminado());
				csmt.registerOutParameter(i++, Types.INTEGER);
				csmt.registerOutParameter(i, Types.VARCHAR);
				csmt.execute();
				
				sql_message = csmt.getString(i);
				if(sql_message != null && !sql_message.equals("")) {
					break;
				}
			}
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			conn.commit();
			
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

