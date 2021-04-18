/**
 * 
 */
package tw.modelo.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.modelo.dao.IRolDao;
import tw.modelo.entidades.Rol;
import tw.modelo.servicios.IRolService;

/**
 * @author Portatil
 * Para implementar el patron Fachada hacia el acceso a datos
 */
@Service
public class RolServiceImpl implements IRolService {

	@Autowired
	private IRolDao rolDao;

	@Override
	@Transactional
	public void save(Rol rol) {
		// TODO Auto-generated method stub
		rolDao.save(rol);
	}

	@Override
	@Transactional(readOnly = true)
	public Rol findById(Long id) {
		// TODO Auto-generated method stub
		return rolDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		rolDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAllByIdUser(Long id) {
		// TODO Auto-generated method stub
		return rolDao.findAllByIdUser(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAllByNameUser(String nombreusuario) {
		// TODO Auto-generated method stub
		return rolDao.findAllByNameUser(nombreusuario);
	}
	

}
