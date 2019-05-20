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

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.dao.EntidadDao;
import pe.com.vendemas.weblogistica.dao.definition.EntidadDaoDefinition;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.Entidad;
import pe.com.vendemas.weblogistica.filterBean.EntidadFilter;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "entidadDao")
public class EntidadDaoImpl implements EntidadDao {
	
	private static final Logger logger = LogManager.getLogger(EntidadDaoImpl.class);
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private  DataSource dataSource;
	
	@Autowired
	@Qualifier("entidadDaoDefinition")
	private EntidadDaoDefinition entidadDaoDefinition;

	@Override
	public StprPaginado getEntidadPaginado(EntidadFilter filter) {	
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ENTIDAD_LISTAR_PAGINADO;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		StprPaginado result = null;
		List<Entidad> listRoles = null;
		Entidad obj = null;
		int i =1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(i++, filter.getNombreEntidad());
			csmt.setInt(i++, filter.getNumPagina());
			csmt.setInt(i++, filter.getCantidadFilasPagina());
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			result = new StprPaginado();
			rs = (ResultSet) csmt.getObject(i-1);
			listRoles = new ArrayList<Entidad>();
			
			int rowNum = 0;
			while(rs.next()) {
				obj = entidadDaoDefinition.mapRow(rs, rowNum);
				listRoles.add(obj);
			    rowNum++;
			}
			
			result.setListObjetos(listRoles);
			result.setTotalPaginas(csmt.getInt(i-2));
			result.setTotalObjetosBD(csmt.getInt(i-3));
			
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
	public Integer entidadSave(Entidad entidad) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ENTIDAD_REGISTRAR;
		String sql_message = null;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		Integer result = 0;
		int i=1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(i++, entidad.getNombreEntidad());
			csmt.setString(i++, entidad.getNombreComercial());
			csmt.setString(i++, entidad.getIdTipoEntidad());
			csmt.setString(i++, entidad.getIdentificadorFacturacion());
			csmt.setString(i++, entidad.getCodigoPais());
			csmt.setString(i++, entidad.getCodigoNbo());
			csmt.setString(i++, entidad.getUsuarioSession());
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			conn.commit();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			result = (int) csmt.getObject(i-1);
			
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
	public Entidad getEntidadById(Integer identidad) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ENTIDAD_OBTENER_BY_ID;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		Entidad result = null;
		int i =1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, identidad);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
			
			int rowNum = 0;
			while(rs.next()) {
				result = entidadDaoDefinition.mapRow(rs, rowNum);
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
	public Integer entidadUpdate(Entidad entidad) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ENTIDAD_ACTUALIZAR;
		String sql_message = null;

		CallableStatement csmt = null;
		Connection conn = null;

		Integer result = 0;
		int i = 1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call " + nombreEsquema + "." + nombreStore + "(?,?,?,?,?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, entidad.getIdEntidad());
			csmt.setString(i++, entidad.getNombreEntidad());
			csmt.setString(i++, entidad.getNombreComercial());
			csmt.setString(i++, entidad.getIdTipoEntidad());
			csmt.setString(i++, entidad.getIdentificadorFacturacion());
			csmt.setString(i++, entidad.getCodigoPais());
			csmt.setString(i++, entidad.getCodigoNbo());
			csmt.setString(i++, entidad.getVigente());
			csmt.setString(i++, entidad.getUsuarioSession());
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			conn.commit();

			sql_message = csmt.getString(i);
			if (sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			result = entidad.getIdEntidad();

		} catch (SQLException e) {
			logger.error(ErrorUtil.formatError(e, sql_message));
		} catch (Exception e) {
			logger.error(ErrorUtil.formatError(e));
		} finally {
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(conn);
		}
		return result;
	}


	@Override
	public List<Entidad> getEntidadByTipo(Integer idTipoEntidad) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ENTIDAD_OBTENER_BY_TIPO;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<Entidad> result = null;
		Entidad obj = null;
		int i =1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, idTipoEntidad);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
			
			int rowNum = 0;
			result = new ArrayList<Entidad>();
			while(rs.next()) {
				obj = entidadDaoDefinition.mapRow(rs, rowNum);
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
