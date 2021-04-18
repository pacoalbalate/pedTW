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

import tw.modelo.dao.ICentroDao;
import tw.modelo.entidades.Centro;
import tw.modelo.servicios.ICentroService;

/**
 * @author Portatil
 * Para implementar el patron Fachada hacia el acceso a datos
 */
@Service
public class CentroServiceImpl implements ICentroService {

	@Autowired
	private ICentroDao centroDao;

	@Override
	@Transactional
	public void save(Centro centro) {
		// TODO Auto-generated method stub
		centroDao.save(centro);
	}

	@Override
	@Transactional(readOnly = true)
	public Centro findById(Long id) {
		// TODO Auto-generated method stub
		return centroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		centroDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Centro> findAll() {
		// TODO Auto-generated method stub
		return (List<Centro>) centroDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Centro> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return centroDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Centro> findAllWithKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return centroDao.findAllWithKeyword(pageable, keyword);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Centro> findByIdInWithKeyword(Pageable pageable, List<Long> centrosId, String keyword) {
		// TODO Auto-generated method stub
		if (centrosId == null) {
			return centroDao.findAllWithKeyword(pageable, keyword);
		} else {
			return centroDao.findByIdInWithKeyword(pageable, centrosId, keyword);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Centro> findAllJoinDatos() {
		// TODO Auto-generated method stub
		return centroDao.findAllJoinDatos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Centro> findAllJoinDatosInRegionesId(List<Long> regionesId) {
		// TODO Auto-generated method stub
		return centroDao.findAllJoinDatosInRegionesId(regionesId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List <Centro> findByRegion_idIsNullOrderByDenominacionAsc() {
		return centroDao.findByRegion_idIsNullOrderByDenominacionAsc();
	}

}
