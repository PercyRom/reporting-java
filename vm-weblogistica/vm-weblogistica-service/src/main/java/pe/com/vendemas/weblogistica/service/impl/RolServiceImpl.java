package pe.com.vendemas.weblogistica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.dao.RolDao;
import pe.com.vendemas.weblogistica.dao.RolOpcionDao;
import pe.com.vendemas.weblogistica.domain.Rol;
import pe.com.vendemas.weblogistica.domain.RolOpcion;
import pe.com.vendemas.weblogistica.filterBean.RolFilter;
import pe.com.vendemas.weblogistica.service.RolService;

@Service("rolServiceImpl")
public class RolServiceImpl implements RolService {
	
	@Autowired
	@Qualifier("rolDao")
	private RolDao rolDao;
	
	@Autowired
	@Qualifier("rolOpcionDao")
	private RolOpcionDao rolOpcionDao;

	@Override
	public Integer nuevoRol(Rol rol) {
		// TODO Auto-generated method stub
		return rolDao.createRol(rol);
	}

	@Override
	public List<Rol> obtenerRoles() {
		// TODO Auto-generated method stub
		return rolDao.getRoles();
	}

	@Override
	public void actualizarRol(Rol rol) {
		// TODO Auto-generated method stub
		rolDao.updateRol(rol);
	}

	@Override
	public List<Rol> obtenerRolesByUsuario(Integer idUsuario) {
		return rolDao.getRolesByUser(idUsuario);
	}

	@Override
	public StprPaginado obtenerRolesPaginado(RolFilter filter) {
		return rolDao.getRolesPaginado(filter);
	}
	
	@Override
	public Integer guardarRol(Rol rol) {
		Integer result=0;
		
		if(rol.getIdRol()>0) {
			rolDao.updateRol(rol);
			result = rol.getIdRol();
		}else {
			result = rolDao.createRol(rol);
			rol.setIdRol(result);
		}
		
		if(result!= null && result>0) {
			RolOpcion rolOpcion = new RolOpcion();
			rolOpcion.setIdRol(rol.getIdRol());
			rolOpcion.setUsuarioSession(rol.getUsuarioSession());
			rolOpcion.setOpciones(rol.getOpciones());
			rolOpcionDao.mantRolOpcion(rolOpcion);
		}
		
		return result;
	}

	@Override
	public Rol obtenerRolById(Integer id) {
		return rolDao.getRolById(id);
	}
	
}
