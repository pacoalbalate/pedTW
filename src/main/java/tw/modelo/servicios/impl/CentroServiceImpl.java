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
* Implementa el interfaz Façade - Centro
* 
* Redirige las peticiones a los métodos del DAO
* 
*/
@Service
public class CentroServiceImpl implements ICentroService {

	@Autowired
	private ICentroDao centroDao;

	/**
	 * Guarda el centro en BD
	 * @param centro
	 */
	@Override
	@Transactional
	public void save(Centro centro) {
		// TODO Auto-generated method stub
		centroDao.save(centro);
	}
	/**
	 * Búsqueda de centro por identificador
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public Centro findById(Long id) {
		// TODO Auto-generated method stub
		return centroDao.findById(id).orElse(null);
	}
	
	/**
	 * Borrado del centro por identificador
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		centroDao.deleteById(id);
	}
	
	/**
	 * Devuelve lista de todos los centros
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Centro> findAll() {
		// TODO Auto-generated method stub
		return (List<Centro>) centroDao.findAll();
	}
	
	/**
	 * Devuelve los centros en un objeto paginable para
	 * presentar por pantalla
	 * @param pageable el objeto página
	 * @return 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Centro> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return centroDao.findAll(pageable);
	}

	/**
	 * Devuelve los centros en un objeto paginable para
	 * presentar por pantalla según criterios de seleccion
	 * @param pageable el objeto página
	 * @param keyword Criterios de selección
	 * @return 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Centro> findAllWithKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return centroDao.findAllWithKeyword(pageable, keyword);
	}

	/**
	 * Devuelve los centros en un objeto paginable para
	 * presentar por pantalla todos o por conjunto de identificadores y criterios de selección
	 * @param pageable el objeto página
	 * @param centrosId lista de identificadores
	 * @param keyword criterios de selección
	 * @return 
	 */
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

	/** 
	 * Devuelve lista de centros con datosfecha (pruebas) asociadas
	 * de todos los centros y sus pruebas
	 * @return 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Centro> findAllJoinDatos() {
		// TODO Auto-generated method stub
		return centroDao.findAllJoinDatos();
	}

	/** 
	 * Devuelve lista de todos los centros con datosfecha (pruebas)
	 * de los pertenecientes a un conjunto de identificadores de región
	 * @param regionesId lista de identificadores de región
	 * @return 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Centro> findAllJoinDatosInRegionesId(List<Long> regionesId) {
		// TODO Auto-generated method stub
		return centroDao.findAllJoinDatosInRegionesId(regionesId);
	}

	/** 
	 * Devuelve lista de todos los centros 
	 * de los pertenecientes a un conjunto de identificadores de región
	 * @param regionesId lista de identificadores de región
	 * @return 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Centro> findAllInRegionesId(List<Long> regionesId) {
		// TODO Auto-generated method stub
		return centroDao.findAllInRegionesId(regionesId);
	}
	
	/**
	 * Método que devuelve los centros sin asociar a region
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List <Centro> findByRegion_idIsNullOrderByDenominacionAsc() {
		return centroDao.findByRegion_idIsNullOrderByDenominacionAsc();
	}

}
