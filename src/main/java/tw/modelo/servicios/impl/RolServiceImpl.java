package tw.modelo.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.modelo.dao.IRolDao;
import tw.modelo.entidades.Rol;
import tw.modelo.servicios.IRolService;

/**
 * Implementa el interfaz Façade - Roles
 * 
 * Redirige las peticiones a los métodos del DAO
 * 
 */@Service
public class RolServiceImpl implements IRolService {

	@Autowired
	private IRolDao rolDao;

	/**
	 * Guarda el rol de un usuario en la BD
	 * @param rol El rol a guardar
	 * 
	 */
	@Override
	@Transactional
	public void save(Rol rol) {
		// TODO Auto-generated method stub
		rolDao.save(rol);
	}

	/**
	 * Recupera un Rol por identificador del rol
	 * @param id Identificador del rol
	 * @return Rol
	 */
	@Override
	@Transactional(readOnly = true)
	public Rol findById(Long id) {
		// TODO Auto-generated method stub
		return rolDao.findById(id).orElse(null);
	}

	/**
	 * Borra de la bd el rol de un usuario
	 * @param id El ide del rol
	 * 
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		rolDao.deleteById(id);
	}

	/**
	 * Busca en la BD los roles de un usuario por id del usuario
	 * y los devuelve en un List
	 * @param id Identificador de usuario
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAllByIdUser(Long id) {
		// TODO Auto-generated method stub
		return rolDao.findAllByIdUser(id);
	}

	/**
	 * Busca en la BD los roles de un usuario por nombre del usuario
	 * y los devuelve en un List
	 * @param nombreusuario nombre del usuario
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAllByNameUser(String nombreusuario) {
		// TODO Auto-generated method stub
		return rolDao.findAllByNameUser(nombreusuario);
	}
	

}
