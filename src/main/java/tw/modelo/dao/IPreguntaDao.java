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
 * @author Portatil
 *
 */
public interface IPreguntaDao extends JpaRepository<Pregunta, Long> {
	
@Query ("SELECT p FROM Pregunta p LEFT JOIN p.tipopregunta tp WHERE CONCAT(p.denominacion, ' ', tp.opcion) LIKE %?1%") 
public Page <Pregunta> findAllWithKeyword( Pageable pageable,  String  keyword);  
	

}
