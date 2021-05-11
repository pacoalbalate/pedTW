
package tw.modelo.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.modelo.entidades.Pregunta;



/** 
 * Interfaz de servicios de acceso Jquery a Preguntas
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso a la entidad
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
