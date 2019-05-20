package pe.com.vendemas.weblogistica.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import pe.com.vendemas.weblogistica.dao.UsuarioDao;
import pe.com.vendemas.weblogistica.dao.definition.UsuarioDaoDefinition;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.Usuario;
import pe.com.vendemas.weblogistica.filterBean.UsuarioFilter;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao{
	
	private static final Logger logger = LogManager.getLogger(UsuarioDaoImpl.class);
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private  DataSource dataSource;
	
	@Autowired
	@Qualifier("usuarioDaoDefinition")
	private UsuarioDaoDefinition usuarioDaoDefinition;

	@Override
	public Integer createUsuario(Usuario usuario) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_USUARIO_REGISTRAR;
		String sql_message;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		Integer result = null;
		int i=1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(i++, usuario.getCodigoUsuario());
			csmt.setString(i++, usuario.getEmail());
			csmt.setString(i++, usuario.getNombres());
			csmt.setString(i++, usuario.getApellidoPaterno());
			csmt.setString(i++, usuario.getApellidoMaterno());
			csmt.setString(i++, usuario.getClave());
			csmt.setString(i++, usuario.getUsuarioSession());
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			conn.commit();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new Exception(sql_message);
			}
			
			result = (Integer) csmt.getObject(i-1);
			
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(conn);
		}
		
		return result;
	}

	@Override
	public List<Usuario> getUsuarios(Usuario usuario) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_USUARIO_LISTAR;
		String sql_message;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<Usuario> result = null;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(1, usuario.getCodigoUsuario());
			csmt.setString(2, usuario.getNombres());
			csmt.setString(3, usuario.getApellidoPaterno());
			csmt.registerOutParameter(4, Types.REF_CURSOR);
			csmt.registerOutParameter(5, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(5);
			if(sql_message != null && !sql_message.equals("")) {
				throw new Exception(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(4);
			result = new ArrayList<Usuario>();
			
			int rowNum = 0;
			while(rs.next()) {
				Usuario obj = usuarioDaoDefinition.mapRow(rs, rowNum);
				result.add(obj);
			    rowNum++;
			}
			
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
	public void updateUsuario(Usuario usuario){
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_USUARIO_ACTUALIZAR;
		String sql_message;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		int i=1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, usuario.getIdUsuario());
			csmt.setString(i++, usuario.getCodigoUsuario());
			csmt.setString(i++, usuario.getEmail());
			csmt.setString(i++, usuario.getNombres());
			csmt.setString(i++, usuario.getApellidoPaterno());
			csmt.setString(i++, usuario.getApellidoMaterno());
			csmt.setString(i++, usuario.getClave());
			csmt.setInt(i++, usuario.getIdEstado());
			csmt.setString(i++, usuario.getUsuarioSession().toString());
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			conn.commit();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new Exception(sql_message);
			}
			
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(conn);
		}
		
	}

	@Override
	public StprPaginado getUsuariosPaginado(UsuarioFilter filter) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_USUARIO_LISTAR_PAGINADO;
		
		String sql_message;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		StprPaginado result = null;
		List<Usuario> listUsuarios = null;
		Usuario obj = null;
		int i =1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(i++, filter.getCodigoUsuario());
			csmt.setString(i++, filter.getNombres());
			csmt.setString(i++, filter.getApellidoPaterno());
			csmt.setInt(i++, filter.getNumPagina());
			csmt.setInt(i++, filter.getCantidadFilasPagina());
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new Exception(sql_message);
			}
			
			result = new StprPaginado();
			rs = (ResultSet) csmt.getObject(i-1);
			listUsuarios = new ArrayList<Usuario>();
			
			int rowNum = 0;
			while(rs.next()) {
				obj = usuarioDaoDefinition.mapRow(rs, rowNum);
				listUsuarios.add(obj);
			    rowNum++;
			}
			
			result.setListObjetos(listUsuarios);
			result.setTotalPaginas(csmt.getInt(i-2));
			result.setTotalObjetosBD(csmt.getInt(i-3));
			
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
	public Usuario getUsuarioByCodigo(String codigoUsuario) {
		
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_USUARIO_OBTENER_BY_CODIGO;
		String sql_message;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		Usuario result = null;
		int i =1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(i++, codigoUsuario);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new Exception(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
//			result = new ArrayList<Usuario>();
			
			int rowNum = 0;
			while(rs.next()) {
				result = usuarioDaoDefinition.mapRow(rs, rowNum);
			    rowNum++;
			}
			
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
	public Integer getUsuariosTotal(UsuarioFilter usuarioFilter) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_USUARIO_TOTAL_USUARIOS;
		String sql_message;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		Integer result = 0;
		int i =1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setString(i++, usuarioFilter.getCodigoUsuario());
			csmt.setString(i++, usuarioFilter.getNombres());
			csmt.setString(i++, usuarioFilter.getApellidoPaterno());
			csmt.registerOutParameter(i++, Types.INTEGER);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new Exception(sql_message);
			}
			
			result = (Integer) csmt.getObject(i-1);
			
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(conn);
		}
		
		return result;
	}

	@Override
	public Usuario getUsuarioById(Integer idUsuario) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_USUARIO_OBTENER_BY_ID;
		String sql_message;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		Usuario result = null;
		int i =1;
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
				throw new Exception(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
			int rowNum = 0;
			while(rs.next()) {
				result = usuarioDaoDefinition.mapRow(rs, rowNum);
			    rowNum++;
			}
			
		}catch(Exception e) {
			logger.error(ErrorUtil.formatError(e));
		}finally {
			DatabaseUtil.close(rs);
			DatabaseUtil.close(csmt);
			DatabaseUtil.close(conn);
		}
		
		return result;
	}

//	@Autowired
//	@Qualifier("usuarioDaoDefinition")
//	private UsuarioDaoDefinition usuarioDaoDefinition;
//	
//	@Autowired
//	@Qualifier("opcionDaoDefinition")
//	private OpcionDaoDefinition opcionDaoDefinition;
//
//	@Autowired
//	@Qualifier("stprSeguridadUsuarioDaoDefinition")
//	private StprSeguridadUsuarioDaoDefinition stprSeguridadUsuarioDaoDefinition;
//	
//	@Autowired
//	@Qualifier("stprSeguridadUsuarioRolDaoDefinition")
//	private StprSeguridadUsuarioRolDaoDefinition stprSeguridadUsuarioRolDaoDefinition;
		
//	@SuppressWarnings("unchecked")
//	@Override
//	public Usuario obtenerUsuarioPorCodigo(String codigoUsuario) {
//		List<Usuario> lista = new ArrayList<Usuario>();
//		SimpleJdbcCall jdbcCall = null;
//		
//		Usuario objUsuario = null;
//		try{
//			jdbcCall = new SimpleJdbcCall(jdbcTemplate);
//			jdbcCall.withSchemaName(ConstantesDAO.SCHEMA_NAME);
//			jdbcCall.withProcedureName(ConstantesDAO.SP_WL_OBTENER_USUARIO);
//			jdbcCall.withoutProcedureColumnMetaDataAccess();
//			jdbcCall.declareParameters(
//				new SqlParameter("var_cuenta",Types.VARCHAR)
//			);
//			
//			Map<String, Object> inParamMap = new HashMap<String, Object>();
//			inParamMap.put("var_cuenta",codigoUsuario);
//			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//			jdbcCall.returningResultSet("resultado", usuarioDaoDefinition);
//			lista = jdbcCall.executeObject(List.class,in);
//			if(lista!=null && lista.size()>0) {
//				objUsuario = lista.get(0);	
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return objUsuario;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<StprSeguridadUsuario> listarUsuarios(Map<String, String> filtros) {
//		
//		String codigoUsuario = (String)filtros.get("codigoUsuario");
//		String apellidoPaterno = (String)filtros.get("apellidoPaterno");
//		String apellidoMaterno = (String)filtros.get("apellidoMaterno");
//		String nombres = (String)filtros.get("nombres");
//		String estado = (String)filtros.get("estado");
//		
//		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
//		String nombreStore = ConstantesDAO.SP_WL_SEGURIDAD_USUARIOS_LISTAR;
//		
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
//		jdbcCall.withSchemaName(nombreEsquema);
//		jdbcCall.withProcedureName(nombreStore);
//		jdbcCall.withoutProcedureColumnMetaDataAccess();
//
//		jdbcCall.declareParameters(
//			new SqlParameter("var_codigo_usuario", Types.VARCHAR),
//			new SqlParameter("var_apellido_paterno", Types.VARCHAR),
//			new SqlParameter("var_apellido_materno", Types.VARCHAR),
//			new SqlParameter("var_nombres", Types.VARCHAR),
//			new SqlParameter("var_estado", Types.VARCHAR)
//		);
//		
//		Map<String, Object> inParam = new HashMap<String, Object>();
//		inParam.put("var_codigo_usuario", codigoUsuario);
//		inParam.put("var_apellido_paterno", apellidoPaterno);
//		inParam.put("var_apellido_materno", apellidoMaterno);
//		inParam.put("var_nombres", nombres);
//		inParam.put("var_estado", estado);
//		
//		SqlParameterSource in = new MapSqlParameterSource(inParam);
//		jdbcCall.returningResultSet("resultado", stprSeguridadUsuarioDaoDefinition);
//		List<StprSeguridadUsuario> result = new ArrayList<StprSeguridadUsuario>();
//		try {
//			 result = jdbcCall.executeObject(List.class, in);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	@Override
//	public Integer registrarUsuario(Map<String,String> datosUsuario) {
//		
//		String strIdUsuario = datosUsuario.get("idUsuario");
//		if(strIdUsuario == null || strIdUsuario.equals("")) {
//			strIdUsuario = "0";
//		}
//		
//		String codigoUsuario = datosUsuario.get("codigoUsuario");
//		String clave = datosUsuario.get("clave");
//		String email = datosUsuario.get("email");
//		String nombres = datosUsuario.get("nombres");
//		String apellidoPaterno = datosUsuario.get("apellidoPaterno");
//		String apellidoMaterno = datosUsuario.get("apellidoMaterno");
//		String strEstado = datosUsuario.get("idEstado");
//		String usuarioAuditoria = datosUsuario.get("usuarioAuditoria");
//		String fechaAuditoria = datosUsuario.get("fechaAuditoria");
//		
//		Integer result = 0;
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
//		jdbcCall.withSchemaName(ConstantesDAO.SCHEMA_NAME);
//		jdbcCall.withProcedureName(ConstantesDAO.SP_WL_SEGURIDAD_USUARIO_REGISTRAR);
//		jdbcCall.withoutProcedureColumnMetaDataAccess();
//		
//		jdbcCall.declareParameters(
//				new SqlParameter("var_id", Types.INTEGER),
//				new SqlParameter("var_codigo_usuario", Types.VARCHAR),
//				new SqlParameter("var_clave", Types.VARCHAR),
//				new SqlParameter("var_email", Types.VARCHAR),
//				new SqlParameter("var_nombres", Types.VARCHAR),
//				new SqlParameter("var_apellidopaterno", Types.VARCHAR),
//				new SqlParameter("var_apellidomaterno", Types.VARCHAR),
//				new SqlParameter("var_estado", Types.INTEGER),
//				new SqlParameter("usuario_auditoria", Types.VARCHAR),
//				new SqlParameter("fecha_auditoria", Types.VARCHAR),
//				new SqlOutParameter("resultado", Types.INTEGER));
//		
//		Map<String, Object> inParamMap = new HashMap<String, Object>();
//		inParamMap.put("var_id",Integer.parseInt(strIdUsuario));
//		inParamMap.put("var_codigo_usuario",codigoUsuario);
//		inParamMap.put("var_clave",clave);
//		inParamMap.put("var_email",email);
//		inParamMap.put("var_nombres",nombres);
//		inParamMap.put("var_apellidopaterno",apellidoPaterno);
//		inParamMap.put("var_apellidomaterno",apellidoMaterno);
//		inParamMap.put("var_estado",Integer.parseInt(strEstado));
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
//	public List<StprSeguridadUsuarioRol> listarUsuarioRoles(Map<String, String> filtros) {
//		
//		String strIdUsuario = (String)filtros.get("idUsuario");
//		if(strIdUsuario == null || strIdUsuario.equals("")) {
//			strIdUsuario = "0";
//		}
//
//		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
//		String nombreStore = ConstantesDAO.SP_WL_SEGURIDAD_USUARIO_ROLES_LISTAR;
//		
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
//		jdbcCall.withSchemaName(nombreEsquema);
//		jdbcCall.withProcedureName(nombreStore);
//		jdbcCall.withoutProcedureColumnMetaDataAccess();
//
//		jdbcCall.declareParameters(
//			new SqlParameter("var_id_usuario", Types.INTEGER)
//		);
//		
//		Map<String, Object> inParam = new HashMap<String, Object>();
//		inParam.put("var_id_usuario", Integer.parseInt(strIdUsuario));
//
//		SqlParameterSource in = new MapSqlParameterSource(inParam);
//		jdbcCall.returningResultSet("resultado", stprSeguridadUsuarioRolDaoDefinition);
//		List<StprSeguridadUsuarioRol> result = new ArrayList<StprSeguridadUsuarioRol>();
//		try {
//			 result = jdbcCall.executeObject(List.class, in);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	@Override
//	public Integer registrarUsuarioRol(Map<String, String> filtros) {
//
//		String strIdUsuario = filtros.get("idUsuario");
//		if(strIdUsuario == null || strIdUsuario.equals("")) {
//			strIdUsuario = "0";
//		}
//		
//		String strIdRol = filtros.get("idRol");
//		if(strIdRol == null || strIdRol.equals("")) {
//			strIdRol = "0";
//		}
//		
//		String vigente = filtros.get("vigente");
//
//		String usuarioAuditoria = filtros.get("usuarioAuditoria");
//		String fechaAuditoria = filtros.get("fechaAuditoria");
//		
//		String operacion = filtros.get("operacion");
//		
//		Integer result = 0;
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
//		jdbcCall.withSchemaName(ConstantesDAO.SCHEMA_NAME);
//		jdbcCall.withProcedureName(ConstantesDAO.SP_WL_SEGURIDAD_USUARIO_ROL_REGISTRAR);
//		jdbcCall.withoutProcedureColumnMetaDataAccess();
//		
//		jdbcCall.declareParameters(
//				new SqlParameter("var_id_usuario", Types.INTEGER),
//				new SqlParameter("var_id_rol", Types.INTEGER),
//				new SqlParameter("var_vigente", Types.VARCHAR),
//				new SqlParameter("usuario_auditoria", Types.VARCHAR),
//				new SqlParameter("fecha_auditoria", Types.VARCHAR),
//				new SqlParameter("var_operacion", Types.VARCHAR),
//				new SqlOutParameter("resultado", Types.INTEGER));
//		
//		Map<String, Object> inParamMap = new HashMap<String, Object>();
//		inParamMap.put("var_id_usuario",Integer.parseInt(strIdUsuario));
//		inParamMap.put("var_id_rol",Integer.parseInt(strIdRol));
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