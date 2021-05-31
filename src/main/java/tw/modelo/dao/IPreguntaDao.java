/**
 * 
 */
package tw.modelo.dao;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.modelo.entidades.Pregunta;



/**
 * DAO
 * Interfaz del modelo de datos para la entidad Preguntas (asociadas a perfiles)
 *
 */
public interface IPreguntaDao extends JpaRepository<Pregunta, Long> {
	
/**
 * Devuelve todas las preguntas en un objeto de paginación filtrando
 * por criterios de selección
 * @param pageable
 * @param keyword Criterios de Selección
 * @return Page<Pregunta>
 */
@Query ("SELECT p FROM Pregunta p LEFT JOIN p.tipopregunta tp WHERE CONCAT(p.denominacion, ' ', tp.opcion) LIKE %?1%") 
public Page <Pregunta> findAllWithKeyword( Pageable pageable,  String  keyword);  
	

}
