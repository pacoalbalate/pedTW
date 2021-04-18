/**
 * 
 */
package tw.modelo.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.modelo.dao.IDatosFechaDao;
import tw.modelo.entidades.DatosFecha;
import tw.modelo.servicios.IDatosFechaService;

/**
 * @author Portatil
 * Para implementar el patron Fachada hacia el acceso a datos
 */
@Service
public class DatosFechaServiceImpl implements IDatosFechaService {

	@Autowired
	private IDatosFechaDao datosfechaDao;

	@Override
	@Transactional
	public void save(DatosFecha datosfecha) {
		// TODO Auto-generated method stub
		datosfechaDao.save(datosfecha);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		datosfechaDao.deleteById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public DatosFecha findById(Long id) {
		// TODO Auto-generated method stub
		return datosfechaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DatosFecha> findAll() {
		// TODO Auto-generated method stub
		return (List<DatosFecha>) datosfechaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DatosFecha> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return datosfechaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DatosFecha> findAllWithKeyword(Pageable pageable, String keyword, Long centroId) {
		// TODO Auto-generated method stub
		return datosfechaDao.findAllWithKeyword(pageable, keyword, centroId);
	}


	@Override
	@Transactional(readOnly = true)
	public Page<DatosFecha> findAllWithKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return datosfechaDao.findAllWithKeyword(pageable, keyword);
	}

	//@Override
	//@Transactional(readOnly = true)
	//public Page<DatosFecha> findByIdInWithKeyword(Pageable pageable, List<Long> lista, String keyword) {
		// TODO Auto-generated method stub
		//if (lista == null) {
		//	return datosfechaDao.findAllWithKeyword(pageable, keyword);
		//} else {
		//	return datosfechaDao.findByIdInWithKeyword(pageable, lista, keyword);
		//}
	//}
	

}
