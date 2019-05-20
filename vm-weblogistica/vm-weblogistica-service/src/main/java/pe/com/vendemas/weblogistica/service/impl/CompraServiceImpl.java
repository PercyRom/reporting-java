package pe.com.vendemas.weblogistica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.vendemas.weblogistica.consult.StprEstadoCatologo;
import pe.com.vendemas.weblogistica.dao.CompraDao;
import pe.com.vendemas.weblogistica.dao.EntradaDao;
import pe.com.vendemas.weblogistica.dao.EstadoCatalogoDao;
import pe.com.vendemas.weblogistica.dao.util.ConstantesDAO;
import pe.com.vendemas.weblogistica.domain.Compra;
import pe.com.vendemas.weblogistica.domain.Entrada;
import pe.com.vendemas.weblogistica.service.CompraService;

@Service("compraServiceImpl")
public class CompraServiceImpl implements CompraService {
	
	@Autowired
	@Qualifier("compraDao")
	private CompraDao compraDao;
	
	@Autowired
	@Qualifier("estadoCatalogoDao")
	private EstadoCatalogoDao estadoCatalogoDao;
	
	@Autowired
	@Qualifier("entradaDao")
	private EntradaDao entradaDao;
	
	@Override
	public Integer guardarCompra(Compra compra) {
		Integer result = null;
		StprEstadoCatologo estado;
		estado = estadoCatalogoDao.getEstadoIds(new StprEstadoCatologo(ConstantesDAO.CTLG_ESTADOS_COMPRA, ConstantesDAO.ITEM_STAT_CMPR_REGISTRADO));
		
		compra.setIdEstadoCatalogo(estado.getIdCatalogo());
		compra.setIdEstadoItem(estado.getIdItem());
		result = compraDao.create(compra);
		
		if(result!=null) {
			for (Entrada entity : compra.getEntradas()) {
				entity.setIdCompra(result);
				entity.setIdAlmacenDestino(33);
				entity.setIdCourier(1);
				entity.setIdTipoMovimiento(ConstantesDAO.TIPO_MOVIMIENTO_COMPRA);
				entity.setIdEstadoCatalogo(estado.getIdCatalogo());
				entity.setIdEstadoItem(estado.getIdItem());
				entity.setUsuarioSession(compra.getUsuarioSession());
			}
			entradaDao.save(compra.getEntradas());
		}
	
		return result;
	}

}
