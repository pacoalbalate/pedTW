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

import tw.modelo.dao.IPreguntaDao;
import tw.modelo.entidades.Pregunta;
import tw.modelo.servicios.IPreguntaService;

/**
 * @author Portatil
 * Para implementar el patron Fachada hacia el acceso a datos
 */
@Service
public class PreguntaServiceImpl implements IPreguntaService {

	@Autowired
	private IPreguntaDao preguntaDao;

	@Override
	@Transactional
	public void save(Pregunta pregunta) {
		// TODO Auto-generated method stub
		preguntaDao.save(pregunta);
	}

	@Override
	@Transactional(readOnly = true)
	public Pregunta findById(Long id) {
		// TODO Auto-generated method stub
		return preguntaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		preguntaDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Pregunta> findAll() {
		// TODO Auto-generated method stub
		return (List<Pregunta>) preguntaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Pregunta> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return preguntaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Pregunta> findAllWithKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return preguntaDao.findAllWithKeyword(pageable, keyword);
	}
	

}
