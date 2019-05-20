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
import pe.com.vendemas.weblogistica.dao.RolDao;
import pe.com.vendemas.weblogistica.dao.definition.RolDaoDefinition;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.Rol;
import pe.com.vendemas.weblogistica.filterBean.RolFilter;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "rolDao")
public class RolDaoImpl implements RolDao {
	
	private static final Logger logger = LogManager.getLogger(RolDaoImpl.class);
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private  DataSource dataSource;
	
	@Autowired
	@Qualifier("rolDaoDefinition")
	private RolDaoDefinition rolDaoDefinition;
	
//	@Autowired
//	@Qualifier("stprSeguridadRolDaoDefinition")
//	private StprSeguridadRolDaoDefinition stprSeguridadRolDaoDefinition;
	
//	@Autowired
//	@Qualifier("stprSeguridadRolOpcionDaoDefinition")
//	private StprSeguridadRolOpcionDaoDefinition stprSeguridadRolOpcionDaoDefinition;
	
	@Override
	public Integer createRol(Rol rol) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ROL_REGISTRAR;
		String sql_message = null;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		Integer result = null;
		int i=1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(i++, rol.getNombreRol());
			csmt.setString(i++, rol.getDescripcionRol());
			csmt.setString(i++, rol.getUsuarioSession());
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
	public List<Rol> getRoles() {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ROL_OBTENER_ROLES;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<Rol> result = null;
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
			result = new ArrayList<Rol>();
			
			int rowNum = 0;
			while(rs.next()) {
				Rol obj = rolDaoDefinition.mapRow(rs, rowNum);
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
	public void updateRol(Rol rol) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ROL_ACTUALIZAR;
		String sql_message = null;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		int i=1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, rol.getIdRol());
			csmt.setString(i++, rol.getNombreRol());
			csmt.setString(i++, rol.getDescripcionRol());
			csmt.setString(i++, rol.getVigente());
			csmt.setString(i++, rol.getUsuarioSession().toString());
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

	@Override
	public List<Rol> getRolesByUser(Integer idUsuario) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ROL_OBTENER_ROLES_BY_USUARIO;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<Rol> result = null;
		int i=1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, idUsuario);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new SQLException(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
			result = new ArrayList<Rol>();
			
			int rowNum = 0;
			while(rs.next()) {
				Rol obj = rolDaoDefinition.mapRow(rs, rowNum);
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
	public Rol getRolById(Integer id) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ROL_OBTENER_BY_ID;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		Rol result = null;
		int i =1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, id);
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
				result = rolDaoDefinition.mapRow(rs, rowNum);
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
	public StprPaginado getRolesPaginado(RolFilter filter) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ROL_LISTAR_PAGINADO;
		String sql_message = null;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		StprPaginado result = null;
		List<Rol> listRoles = null;
		Rol obj = null;
		int i =1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(i++, filter.getNombreRol());
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
			listRoles = new ArrayList<Rol>();
			
			int rowNum = 0;
			while(rs.next()) {
				obj = rolDaoDefinition.mapRow(rs, rowNum);
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
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<StprSeguridadRol> listarRoles(RolFilter filterRol) {
//			
//		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
//		String nombreStore = ConstantesDAO.SP_WL_SEGURIDAD_ROLES_LISTAR;
//		String sql_message;
//				
//		List<StprSeguridadRol> result = null;
//		try {
//
//			Connection con = dataSource.getConnection();
//			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?)}";
//			CallableStatement stmt = con.prepareCall(commandText);
//			stmt.setString(1, filterRol.getNombreRol());
//			stmt.setString(2, filterRol.getVigente());
//			stmt.registerOutParameter(3, Types.REF_CURSOR);
//			stmt.registerOutParameter(4, Types.VARCHAR);
//			stmt.execute();
//			
//			sql_message = stmt.getString(4);
//			if(sql_message != null && !sql_message.equals("")) {
//				throw new SQLException(sql_message);
//			}
//			
//			ResultSet rs = (ResultSet) stmt.getObject(3);
//			result = new ArrayList<StprSeguridadRol>();
//			
//			int rowNum = 0;
//			while(rs.next()) {
//				StprSeguridadRol obj = stprSeguridadRolDaoDefinition.mapRow(rs, rowNum);
//				result.add(obj);
//			    rowNum++;
//			}
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//		
//	}

//	@Transactional
//	@Override
//	public Integer registrarRol(Map<String, String> datosRol) {
//
//		String strIdRol = datosRol.get("idRol");
//		if(strIdRol == null || strIdRol.equals("")) {
//			strIdRol = "0";
//		}
//		
//		String nombreRol = datosRol.get("nombreRol");
//		String descripcionRol = datosRol.get("descripcionRol");
//		String vigente = datosRol.get("vigente");
//
//		String usuarioAuditoria = datosRol.get("usuarioAuditoria");
//		String fechaAuditoria = datosRol.get("fechaAuditoria");
//		
//		Integer result = 0;
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
//		jdbcCall.withSchemaName(ConstantesDAO.SCHEMA_NAME);
//		jdbcCall.withProcedureName(ConstantesDAO.SP_WL_SEGURIDAD_ROL_REGISTRAR);
//		jdbcCall.withoutProcedureColumnMetaDataAccess();
//		
//		jdbcCall.declareParameters(
//				new SqlParameter("var_id", Types.INTEGER),
//				new SqlParameter("var_nombre_rol", Types.VARCHAR),
//				new SqlParameter("var_descripcion_rol", Types.VARCHAR),
//				new SqlParameter("var_vigente", Types.VARCHAR),
//				new SqlParameter("usuario_auditoria", Types.VARCHAR),
//				new SqlParameter("fecha_auditoria", Types.VARCHAR),
//				new SqlOutParameter("resultado", Types.INTEGER));
//		
//		Map<String, Object> inParamMap = new HashMap<String, Object>();
//		inParamMap.put("var_id",Integer.parseInt(strIdRol));
//		inParamMap.put("var_nombre_rol",nombreRol);
//		inParamMap.put("var_descripcion_rol",descripcionRol);
//		inParamMap.put("var_vigente",vigente);
//		inParamMap.put("usuario_auditoria",usuarioAuditoria);
//		inParamMap.put("fecha_auditoria",fechaAuditoria);
//		
//		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//		result = jdbcCall.executeObject(Integer.class, in);
//
//		return result;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<StprSeguridadRolOpcion> listarRolOpciones(Map<String, String> filtros) {
//		
//		String strIdRol = (String)filtros.get("idRol");
//		if(strIdRol == null || strIdRol.equals("")) {
//			strIdRol = "0";
//		}
//
//		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
//		String nombreStore = ConstantesDAO.SP_WL_SEGURIDAD_ROL_OPCIONES_LISTAR;
//		
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
//		jdbcCall.withSchemaName(nombreEsquema);
//		jdbcCall.withProcedureName(nombreStore);
//		jdbcCall.withoutProcedureColumnMetaDataAccess();
//
//		jdbcCall.declareParameters(
//			new SqlParameter("var_id_rol", Types.INTEGER)
//		);
//		
//		Map<String, Object> inParam = new HashMap<String, Object>();
//		inParam.put("var_id_rol", Integer.parseInt(strIdRol));
//
//		SqlParameterSource in = new MapSqlParameterSource(inParam);
//		jdbcCall.returningResultSet("resultado", stprSeguridadRolOpcionDaoDefinition);
//		List<StprSeguridadRolOpcion> result = new ArrayList<StprSeguridadRolOpcion>();
//		try {
//			 result = jdbcCall.executeObject(List.class, in);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	@Transactional
//	@Override
//	public Integer registrarRolOpcion(Map<String, String> datosRolOpcion) {
//		
//		String strIdRol = datosRolOpcion.get("idRol");
//		if(strIdRol == null || strIdRol.equals("")) {
//			strIdRol = "0";
//		}
//		
//		String strIdOpcion = datosRolOpcion.get("idOpcion");
//		if(strIdOpcion == null || strIdOpcion.equals("")) {
//			strIdOpcion = "0";
//		}
//		
//		String vigente = datosRolOpcion.get("vigente");
//
//		String usuarioAuditoria = datosRolOpcion.get("usuarioAuditoria");
//		String fechaAuditoria = datosRolOpcion.get("fechaAuditoria");
//		
//		String operacion = datosRolOpcion.get("operacion");
//		
//		Integer result = 0;
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
//		jdbcCall.withSchemaName(ConstantesDAO.SCHEMA_NAME);
//		jdbcCall.withProcedureName(ConstantesDAO.SP_WL_SEGURIDAD_ROL_OPCION_REGISTRAR);
//		jdbcCall.withoutProcedureColumnMetaDataAccess();
//		
//		jdbcCall.declareParameters(
//				new SqlParameter("var_id_rol", Types.INTEGER),
//				new SqlParameter("var_id_opcion", Types.INTEGER),
//				new SqlParameter("var_vigente", Types.VARCHAR),
//				new SqlParameter("usuario_auditoria", Types.VARCHAR),
//				new SqlParameter("fecha_auditoria", Types.VARCHAR),
//				new SqlParameter("var_operacion", Types.VARCHAR),
//				new SqlOutParameter("resultado", Types.INTEGER));
//		
//		Map<String, Object> inParamMap = new HashMap<String, Object>();
//		inParamMap.put("var_id_rol",Integer.parseInt(strIdRol));
//		inParamMap.put("var_id_opcion",Integer.parseInt(strIdOpcion));
//		inParamMap.put("var_vigente",vigente);
//		inParamMap.put("usuario_auditoria",usuarioAuditoria);
//		inParamMap.put("fecha_auditoria",fechaAuditoria);
//		inParamMap.put("var_operacion",operacion);
//		
//		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//		result = jdbcCall.executeObject(Integer.class, in);
//
//		return result;
//	}
	
}
