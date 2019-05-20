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

import pe.com.vendemas.weblogistica.dao.OpcionDao;
import pe.com.vendemas.weblogistica.dao.definition.OpcionDaoDefinition;
import pe.com.vendemas.weblogistica.dao.util.AbstractDao;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.Opcion;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "opcionDao")
public class OpcionDaoImpl extends AbstractDao implements OpcionDao {
	
	private static final Logger logger = LogManager.getLogger(OpcionDaoImpl.class);
	
	@Autowired
	@Qualifier("opcionDaoDefinition")
	private OpcionDaoDefinition opcionDaoDefinition;
	
	@Override
	public List<Opcion> getOpcionesByUsuario(Integer cod_usuario) {
		
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_OPCION_OBTENER_BY_USUARIO;
		String sql_message;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<Opcion> result = null;
		Opcion obj;
		int i =1;
		try {
			
			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, cod_usuario);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new Exception(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
			result = new ArrayList<Opcion>();
			
			int rowNum = 0;
			while(rs.next()) {
				obj = opcionDaoDefinition.mapRow(rs, rowNum);
				result.add(obj);
			    rowNum++;
			}
			
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(rs);
			DatabaseUtil.close(conn);
		}
		
		return result;
		
	}

	@Override
	public List<Opcion> getOpcionesByRol(Integer idRol) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_OPCION_OBTENER_OPCIONES_BY_ROL;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<Opcion> result = null;
		int i=1;
		try {
			
			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, idRol);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
			result = new ArrayList<Opcion>();
			
			int rowNum = 0;
			while(rs.next()) {
				Opcion obj = opcionDaoDefinition.mapRow(rs, rowNum);
				result.add(obj);
			    rowNum++;
			}
		
		}catch(SQLException e) {
			logger.error(ErrorUtil.formatError(e,sql_message));
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(rs);
			DatabaseUtil.close(conn);
		}
		
		return result;
	}

	@Override
	public Integer create(Opcion entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Opcion> getAll() {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_OPCION_OBTENER_OPCIONES;
		String sql_message;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<Opcion> result = null;
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
				throw new Exception(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
			result = new ArrayList<Opcion>();
			
			int rowNum = 0;
			while(rs.next()) {
				Opcion obj = opcionDaoDefinition.mapRow(rs, rowNum);
				result.add(obj);
			    rowNum++;
			}
			
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(rs);
			DatabaseUtil.close(conn);
		}
		
		return result;
	}

	@Override
	public Opcion getById(Opcion entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Opcion entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Opcion> getList(Opcion entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
