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
 * Implementa el interfaz Façade - Región
 * 
 * Redirige las peticiones a los métodos del DAO
 * 
 */@Service
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;

	/**
	 * Almacena Región en BD
	 * @param region Región a guardar
	 */
	@Override
	@Transactional
	public void save(Region region) {
		// TODO Auto-generated method stub
		regionDao.save(region);
	}

	/**
	 * Devuelve una región por Identificador o null si no existe
	 * @param id Identificador de región
	 * @return Región/null
	 */
	@Override
	public Region findById(Long id) {
		// TODO Auto-generated method stub
		return regionDao.findById(id).orElse(null);
	}

	/**
	 * Devuelve una región por Identificador con todos sus centros asociados o null si no existe
	 * @param id Identificador de región
	 * @return Región
	 */
	@Override
	@Transactional(readOnly = true)
	public Region findByIdWithCentros(Long id) {
		// TODO Auto-generated method stub
		return regionDao.findByIdWithCentros(id);
	}

	/**
	 * Borra la región con Identificador pasado por parámetro
	 * @param id Id de la región a borrar
	 * 
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		regionDao.deleteById(id);
	}

	/**
	 * Obtiene todas las regiones y las devuelve en un List
	 * @return List<Region>
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return (List<Region>) regionDao.findAll();
	}

	/**
	 * Obtiene todas las regiones y las devuelve en un objeto de paginación
	 * @param pageable
	 * @return PAge<Region>
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Region> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return regionDao.findAll(pageable);
	}

	/**
	 * Obtiene todas las regiones según criterios de filtrado para el listado
	 * pasados por parámetro y las devuelve en un objeto de paginación
	 * @param pageable
	 * @param keyword Criterios de selección
	 * @return Page<Region>
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Region> findAllWithKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return regionDao.findAllWithKeyword(pageable, keyword);
	}

	/**
	 * Devuelve las regiones ya asociadas a las Pruebas y sus Pruebas (datosfecha)
	 * @return List<Region>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllJoinDatos() {
		// TODO Auto-generated method stub
		return regionDao.findAllJoinDatos();
	}

	
	
}
