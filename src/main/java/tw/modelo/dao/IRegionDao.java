/**
 * 
 */
package tw.modelo.dao;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import tw.modelo.entidades.Region;

/**
  * DAO
 * Interfaz del modelo de datos para la entidad Región
 *
 */
public interface IRegionDao extends JpaRepository<Region, Long> {
	

	
@Query ("SELECT DISTINCT r FROM Region r LEFT JOIN r.centros c WHERE r.id=?1") 
public Region findByIdWithCentros(Long id);  
	
@Query ("SELECT DISTINCT r FROM Region r LEFT JOIN r.centros c WHERE CONCAT(r.denominacion, ' ', r.habitantes) LIKE %?1%") 
public Page <Region> findAllWithKeyword( Pageable pageable,  String  keyword);  

@Query ("SELECT DISTINCT r FROM Region r JOIN r.centros c JOIN c.datosfecha df") 
public List<Region> findAllJoinDatos();  
	

//@Query ("SELECT r FROM Region r LEFT JOIN r.centros c WHERE r.id IN ?1 AND CONCAT(r.denominacion, ' ', r.habitantes) LIKE %?2%") 
//public Page <Region> findByIdInWithKeyword( Pageable pageable,  List<Long> lista,  String  keyword);  

//@Query ("SELECT r FROM Region r WHERE CONCAT(r.denominacion, ' ', r.habitantes) LIKE %:Keyword%") 
//public Page <Region> findAllWithKeyword(@Param("Keyword") String keyword, Pageable pageable);

//public Page <Region> findByHabitantesIn(Iterable<Long> habitantes, Pageable pageable);  
	
}
