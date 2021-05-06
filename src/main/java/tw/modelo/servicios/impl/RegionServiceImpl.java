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

import tw.modelo.dao.IRegionDao;
import tw.modelo.entidades.Region;
import tw.modelo.servicios.IRegionService;

/**
 * @author Portatil
 * Para implementar el patron Fachada hacia el acceso a datos
 */
@Service
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;

	@Override
	@Transactional
	public void save(Region region) {
		// TODO Auto-generated method stub
		regionDao.save(region);
	}

	@Override
	public Region findById(Long id) {
		// TODO Auto-generated method stub
		return regionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Region findByIdWithCentros(Long id) {
		// TODO Auto-generated method stub
		return regionDao.findByIdWithCentros(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		regionDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return (List<Region>) regionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Region> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return regionDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Region> findAllWithKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return regionDao.findAllWithKeyword(pageable, keyword);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllJoinDatos() {
		// TODO Auto-generated method stub
		return regionDao.findAllJoinDatos();
	}

	
	
	//@Override
	//@Transactional(readOnly = true)
	//public Page<Region> findByIdInWithKeyword(Pageable pageable, List<Long> regiones, String keyword) {
	//	// TODO Auto-generated method stub
	//	if (regiones == null) {
	//		return regionDao.findAllWithKeyword(pageable, keyword);
	//	} else {
	//		return regionDao.findByIdInWithKeyword(pageable, regiones, keyword);
	//	}
	//}

}
