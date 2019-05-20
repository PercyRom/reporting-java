package pe.com.vendemas.weblogistica.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.vendemas.weblogistica.consult.StprEntidadContactos;
import pe.com.vendemas.weblogistica.consult.StprPaginado;
import pe.com.vendemas.weblogistica.dao.EntidadContactosDao;
import pe.com.vendemas.weblogistica.dao.EntidadDao;
import pe.com.vendemas.weblogistica.dao.TipoDocumentoIdentidadDao;
import pe.com.vendemas.weblogistica.dao.TipoEntidadDao;
import pe.com.vendemas.weblogistica.domain.Entidad;
import pe.com.vendemas.weblogistica.domain.EntidadContactos;
import pe.com.vendemas.weblogistica.domain.TipoDocumentoIdentidad;
import pe.com.vendemas.weblogistica.domain.TipoEntidad;
import pe.com.vendemas.weblogistica.filterBean.EntidadFilter;
import pe.com.vendemas.weblogistica.service.MaestroService;

@Service("maestroServiceImpl")
public class MaestroServiceImpl implements MaestroService {
	
	@Autowired
	@Qualifier("entidadDao")
	private EntidadDao entidadDao;
	
	@Autowired
	@Qualifier("tipoEntidadDao")
	private TipoEntidadDao tipoEntidadDao;
	
	@Autowired
	@Qualifier("entidadContactosDao")
	private EntidadContactosDao entidadContactosDao;
	
	@Autowired
	@Qualifier("tipoDocumentoIdentidadDao")
	private TipoDocumentoIdentidadDao tipoDocumentoIdentidadDao;
	
	@Override
	public StprPaginado listarEntidades(EntidadFilter filter) {
		return entidadDao.getEntidadPaginado(filter);
	}

	@Override
	public Entidad entidadSave(Entidad entidad) {

		Integer idEntidad = entidad.getIdEntidad();

		if (idEntidad > 0) {
			idEntidad = entidadDao.entidadUpdate(entidad);
		} else {
			idEntidad = entidadDao.entidadSave(entidad);
		}

		List<EntidadContactos> contactos = new ArrayList<EntidadContactos>();
		for (EntidadContactos contacto : entidad.getContactos()) {
			contacto.setIdEntidad(idEntidad);
			if (contacto.isEditado()) {
				int idEntidadContacto = entidadContactosDao.save(contacto);
				contacto.setIdEntidadContacto(idEntidadContacto);
			}
			contactos.add(contacto);
		}
		
		entidad.setContactos(contactos);

		return entidad;
	}

	@Override
	public Entidad getEntidadById(Integer identidad) {
		
		Entidad entidad = entidadDao.getEntidadById(identidad);
		List<StprEntidadContactos> result = entidadContactosDao.getListByEntidad(identidad);
		
		List<EntidadContactos> contactos = new ArrayList<EntidadContactos>();
		EntidadContactos contacto;
		for (StprEntidadContactos obj : result) {
			contacto = new EntidadContactos();
			contacto.setIdEntidadContacto(obj.getIdEntidadContacto());
			contacto.setIdEntidad(obj.getIdEntidad());
			contacto.setApellidoPaterno(obj.getApellidoPaterno());
			contacto.setApellidoMaterno(obj.getApellidoMaterno());
			contacto.setNombres(obj.getNombres());
			contacto.setIdTipoDocumentoIdentidad(obj.getIdTipoDocumentoIdentidad());
			contacto.setDocumentoIdentidad(obj.getDocumentoIdentidad());
			contacto.setTelefonoContacto(obj.getTelefonoContacto());
			contacto.setUsuarioSession(obj.getUsuarioSession());
			contactos.add(contacto);
		}
		
		entidad.setContactos(contactos);
		
		return entidad;
	}

	@Override
	public List<TipoEntidad> getTiposEntidad() {
		return tipoEntidadDao.getAll();
	}

	@Override
	public List<Entidad> listarEntidadesPorTipo(Integer idTipoEntidad) {
		return entidadDao.getEntidadByTipo(idTipoEntidad);
	}
	
	@Override
	public List<TipoDocumentoIdentidad> getTipoDocumentoIdentidad(){
		return tipoDocumentoIdentidadDao.getAll();
	}
	
}
