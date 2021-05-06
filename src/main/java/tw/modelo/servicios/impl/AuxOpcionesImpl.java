/**
 * 
 */
package tw.modelo.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.modelo.dao.IAuxOpcionesDao;
import tw.modelo.entidades.AuxOpciones;
import tw.modelo.servicios.IAuxOpcionesService;

/**
 * @author Portatil
 * Para implementar el patron Fachada hacia el acceso a datos
 */
@Service
public class AuxOpcionesImpl implements IAuxOpcionesService {

	@Autowired
	private IAuxOpcionesDao auxOpcionesDao;

	@Override
	@Transactional(readOnly = true)
	public AuxOpciones findById(Long id) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public AuxOpciones findById(String id) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findById(Long.parseLong(id)).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuxOpciones> findByTipoOrderById(String tipo) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findByTipoOrderById(tipo);
	}

	@Override
	@Transactional(readOnly = true)
	public AuxOpciones findByTipoAndOpcion(String tipo, String opcion) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findByTipoAndOpcion(tipo, opcion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuxOpciones> findByTipoNotOrderById(String tipo) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findByTipoNotOrderById(tipo); 
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuxOpciones> findByTipoContainingOrderById(String tipo) {
		// TODO Auto-generated method stub
		return auxOpcionesDao.findByTipoContainingOrderById(tipo);
	}


}
