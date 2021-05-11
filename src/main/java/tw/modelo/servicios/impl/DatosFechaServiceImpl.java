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
 * Implementa el interfaz Façade - DatosFecha (Pruebas)
 * 
 * Redirige las peticiones a los métodos del DAO
 * 
 */
@Service
public class DatosFechaServiceImpl implements IDatosFechaService {

	@Autowired
	private IDatosFechaDao datosfechaDao;

	/**
	 * graba en bd la prueba
	 * @param datosfecha la prueba a grabar
	 */
	@Override
	@Transactional
	public void save(DatosFecha datosfecha) {
		// TODO Auto-generated method stub
		datosfechaDao.save(datosfecha);
	}

	/**
	 * borra de bd la prueba
	 * @param id el identificador de la prueba a borrar
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		datosfechaDao.deleteById(id);
	}


	/**
	 * busca la prueba por identificador
	 * @param id el identificador de la prueba
	 * @return La prueba
	 */
	@Override
	@Transactional(readOnly = true)
	public DatosFecha findById(Long id) {
		// TODO Auto-generated method stub
		return datosfechaDao.findById(id).orElse(null);
	}

	/**
	 * Busca todas las pruebas
	 * @return Lista de pruebas
	 */
	@Override
	@Transactional(readOnly = true)
	public List<DatosFecha> findAll() {
		// TODO Auto-generated method stub
		return (List<DatosFecha>) datosfechaDao.findAll();
	}

	/**
	 * Devuelve las pruebas en objeto paginable
	 * @param pageable el objeto paginable
	 * @return 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<DatosFecha> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return datosfechaDao.findAll(pageable);
	}

	/**
	 * Devuelve las pruebas en objeto paginable por identificador del centro y
	 * criterios de selección
	 * @param pageable el objeto paginable
	 * @param keyword Criterios de selección
	 * @param centroId identificador del centro
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<DatosFecha> findAllWithKeyword(Pageable pageable, String keyword, Long centroId) {
		// TODO Auto-generated method stub
		return datosfechaDao.findAllWithKeyword(pageable, keyword, centroId);
	}

	/**
	 * Devuelve las pruebas en objeto paginable por criterios de selección
	 * @param pageable el objeto paginable
	 * @param keyword Criterios de selección
	 * @return
	 */
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
