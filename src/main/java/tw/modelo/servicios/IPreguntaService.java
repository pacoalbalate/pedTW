
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
	
	/**
	 * Guarda la pregunta en bd
	 * @param pregunta 
	 */
	void save(Pregunta pregunta);

	/**
	 * Lee pregunta de BD por Identificador
	 * @param id
	 * @return Pregunta
	 */
	Pregunta findById(Long id);

	/**
	 * Borra pregunta de la BD por identificador
	 * @param id Identificador a borrar
	 * 
	 */
	void delete(Long id);

	/**
	 * Devuelve todas las preguntas en un List
	 * @return
	 */
	List<Pregunta> findAll();

	/**
	 * Devuelve todas las preguntas en un objeto de paginación
	 * @param pageable 
	 * @return Page <Pregunta>
	 */
	Page<Pregunta> findAll(Pageable pageable);

	/**
	 * Devuelve todas las preguntas en un objeto de paginación filtrando
	 * por criterios de selección
	 * @param pageable
	 * @param keyword Criterios de Selección
	 * @return Page <Pregunta>
	 */
	Page<Pregunta> findAllWithKeyword(Pageable pageable, String keyword);
	
}
