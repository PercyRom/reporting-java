package pe.com.vendemas.weblogistica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.vendemas.weblogistica.dao.TipoProductoDao;
import pe.com.vendemas.weblogistica.domain.TipoProducto;
import pe.com.vendemas.weblogistica.service.TipoProductoService;

@Service("tipoProductoServiceImpl")
public class TipoProductoServiceImpl implements TipoProductoService {
	
	@Autowired
	@Qualifier("tipoProductoDao")
	private TipoProductoDao tipoProductoDao;
	
	@Override
	public List<TipoProducto> obtenerTipoProductos() {
		return tipoProductoDao.getAll();
	}

}
