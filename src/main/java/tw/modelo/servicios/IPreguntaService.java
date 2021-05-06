/**
 * 
 */
package tw.modelo.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.modelo.entidades.Pregunta;



/** 
 * @author Portatil
 *
 */
public interface IPreguntaService {
	
	public List<Pregunta> findAll();

	public Page<Pregunta> findAll(Pageable pageable);
	
	public Page<Pregunta> findAllWithKeyword(Pageable pageable, String keyword);

	public Pregunta findById(Long id);
	
	public void save(Pregunta pregunta);

	public void delete(Long id);
	
}
