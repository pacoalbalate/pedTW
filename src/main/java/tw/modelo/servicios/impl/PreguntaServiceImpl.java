package tw.modelo.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.modelo.dao.IPreguntaDao;
import tw.modelo.entidades.Pregunta;
import tw.modelo.servicios.IPreguntaService;

/**
 * Implementa el interfaz Façade - Pregunta
 * 
 * Redirige las peticiones a los métodos del DAO
 * 
 */@Service
public class PreguntaServiceImpl implements IPreguntaService {

	@Autowired
	private IPreguntaDao preguntaDao;

	/**
	 * Guarda la pregunta en bd
	 * @param pregunta 
	 */
	@Override
	@Transactional
	public void save(Pregunta pregunta) {
		// TODO Auto-generated method stub
		preguntaDao.save(pregunta);
	}

	/**
	 * Lee pregunta de BD por Identificador
	 * @param id
	 * @return Pregunta
	 */
	@Override
	@Transactional(readOnly = true)
	public Pregunta findById(Long id) {
		// TODO Auto-generated method stub
		return preguntaDao.findById(id).orElse(null);
	}

	/**
	 * Borra pregunta de la BD por identificador
	 * @param id Identificador a borrar
	 * 
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		preguntaDao.deleteById(id);
	}
	
	/**
	 * Devuelve todas las preguntas en un List
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Pregunta> findAll() {
		// TODO Auto-generated method stub
		return (List<Pregunta>) preguntaDao.findAll();
	}

	/**
	 * Devuelve todas las preguntas en un objeto de paginación
	 * @param pageable 
	 * @return Page <Pregunta>
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Pregunta> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return preguntaDao.findAll(pageable);
	}

	/**
	 * Devuelve todas las preguntas en un objeto de paginación filtrando
	 * por criterios de selección
	 * @param pageable
	 * @param keyword Criterios de Selección
	 * @return Page <Pregunta>
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Pregunta> findAllWithKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return preguntaDao.findAllWithKeyword(pageable, keyword);
	}
	

}
