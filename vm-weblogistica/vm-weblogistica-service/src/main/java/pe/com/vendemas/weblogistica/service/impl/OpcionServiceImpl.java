package pe.com.vendemas.weblogistica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.vendemas.weblogistica.dao.OpcionDao;
import pe.com.vendemas.weblogistica.domain.Opcion;
import pe.com.vendemas.weblogistica.service.OpcionService;

@Service("opcionServiceImpl")
public class OpcionServiceImpl implements OpcionService {
	
	@Autowired
	@Qualifier("opcionDao")
	private OpcionDao opcionDao;
	
	@Override
	public Integer registrarOpcion(Opcion opcion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Opcion> obtenerOpciones() {
		// TODO Auto-generated method stub
		return opcionDao.getAll();
	}

	@Override
	public void actualizarOpcion(Opcion opcion) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Opcion> obtenerOpcionesByRol(Integer idRol) {
		// TODO Auto-generated method stub
		return opcionDao.getOpcionesByRol(idRol);
	}

}
