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

import pe.com.vendemas.weblogistica.consult.StprEntidadContactos;
import pe.com.vendemas.weblogistica.dao.EntidadContactosDao;
import pe.com.vendemas.weblogistica.dao.definition.StprEntidadContactosDaoDefinition;
import pe.com.vendemas.weblogistica.dao.util.AbstractDao;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.dao.util.DatabaseUtil;
import pe.com.vendemas.weblogistica.domain.EntidadContactos;
import pe.com.vendemas.weblogistica.util.ErrorUtil;

@Repository(value = "entidadContactosDao")
public class EntidadContactosDaoImpl extends AbstractDao implements EntidadContactosDao {
	
	private static final Logger logger = LogManager.getLogger(OpcionDaoImpl.class);
	
	@Autowired
	@Qualifier("stprEntidadContactosDaoDefinition")
	private StprEntidadContactosDaoDefinition stprEntidadContactosDaoDefinition;
	
	@Override
	public Integer create(EntidadContactos entity) {
		return null;
	}

	@Override
	public EntidadContactos getById(EntidadContactos entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(EntidadContactos entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadContactos> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadContactos> getList(EntidadContactos entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StprEntidadContactos> getListByEntidad(Integer idEntidad) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ENTIDAD_CONTACTOS_BY_ENTIDAD;
		String sql_message;
		
		ResultSet rs = null;
		CallableStatement csmt = null;
		Connection conn = null;
		
		List<StprEntidadContactos> result = null;
		StprEntidadContactos obj;
		int i =1;
		try {
			
			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, idEntidad);
			csmt.registerOutParameter(i++, Types.REF_CURSOR);
			csmt.registerOutParameter(i, Types.VARCHAR);
			csmt.execute();
			
			sql_message = csmt.getString(i);
			if(sql_message != null && !sql_message.equals("")) {
				throw new Exception(sql_message);
			}
			
			rs = (ResultSet) csmt.getObject(i-1);
			result = new ArrayList<StprEntidadContactos>();
			
			int rowNum = 0;
			while(rs.next()) {
				obj = stprEntidadContactosDaoDefinition.mapRow(rs, rowNum);
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
	public Integer save(EntidadContactos contacto) {
		String nombreEsquema = ConstantesDAO.SCHEMA_NAME;
		String nombreStore = ConstantesDAO.SP_ENTIDAD_CONTACTOS_REGISTRAR;
		String sql_message = null;
		
		CallableStatement csmt = null;
		Connection conn = null;
		
		Integer result = 0;
		int i=1;
		try {

			conn = dataSource.getConnection();
			String commandText = "{call "+nombreEsquema+"."+nombreStore+"(?,?,?,?,?,?,?,?,?,?,?)}";
			csmt = conn.prepareCall(commandText);
			csmt.setInt(i++, contacto.getIdEntidadContacto());
			csmt.setInt(i++, contacto.getIdEntidad());
			csmt.setString(i++, contacto.getApellidoPaterno());
			csmt.setString(i++, contacto.getApellidoMaterno());
			csmt.setString(i++, contacto.getNombres());
			csmt.setInt(i++, contacto.getIdTipoDocumentoIdentidad());
			csmt.setString(i++, contacto.getDocumentoIdentidad());
			csmt.setString(i++, contacto.getTelefonoContacto());
			csmt.setString(i++, contacto.getUsuarioSession());
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

}
