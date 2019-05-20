package pe.com.vendemas.weblogistica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.vendemas.weblogistica.consult.StprEntidadContactos;
import pe.com.vendemas.weblogistica.dao.EntidadContactosDao;
import pe.com.vendemas.weblogistica.domain.EntidadContactos;
import pe.com.vendemas.weblogistica.service.EntidadContactosService;

@Service("entidadContactosServiceImpl")
public class EntidadContactosServiceImpl implements EntidadContactosService {
	
	@Autowired
	@Qualifier("entidadContactosDao")
	private EntidadContactosDao entidadContactosDao;
	
	@Override
	public List<StprEntidadContactos> listContactosByEntidad(Integer idEntidad) {
		return entidadContactosDao.getListByEntidad(idEntidad);
	}
	
	@Override
	public Integer entidadContactoSave(EntidadContactos contacto) {
		return entidadContactosDao.save(contacto);
	}

}
