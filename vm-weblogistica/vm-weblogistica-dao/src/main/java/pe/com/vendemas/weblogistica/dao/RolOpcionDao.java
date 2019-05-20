package pe.com.vendemas.weblogistica.dao;

import java.util.List;

import pe.com.vendemas.weblogistica.domain.RolOpcion;

public interface RolOpcionDao {
	
	//CRUD BASICO
		public Integer createRolOpcion(RolOpcion obj);
		public List<RolOpcion> getRolOpciones();
		public void updateRolOpcion(RolOpcion obj);
		
		public void mantRolOpcion(RolOpcion obj);
	
}
