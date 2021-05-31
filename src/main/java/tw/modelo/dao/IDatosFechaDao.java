/**
 * 
 */
package tw.modelo.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.modelo.entidades.DatosFecha;



/**
 * DAO
 * Interfaz del modelo de datos para la entidad DatosFecha (Pruebas) 
 *
 */
public interface IDatosFechaDao extends JpaRepository<DatosFecha, Long> {
	
	
/**
 * Devuelve las pruebas en objeto paginable por criterios de selección del listado
 * @param pageable el objeto paginable
 * @param keyword Criterios de selección
 * @return
 */
@Query ("SELECT d FROM DatosFecha d LEFT JOIN d.tipoprueba tp WHERE CONCAT(d.totalpruebas, ' ', d.fecha, ' ', tp.opcion) LIKE %?1%") 
public Page <DatosFecha> findAllWithKeyword( Pageable pageable,  String  keyword);  

/**
 * Devuelve las pruebas en objeto paginable por identificador del centro y
 * criterios de selección
 * @param pageable el objeto paginable
 * @param keyword Criterios de selección
 * @param centroId identificador del centro
 * @return
 */
@Query ("SELECT d FROM DatosFecha d LEFT JOIN d.centro c LEFT JOIN d.tipoprueba tp WHERE c.id IN ?2 AND CONCAT(d.totalpruebas, ' ', d.fecha, ' ', tp.opcion) LIKE %?1%") 
public Page <DatosFecha> findAllWithKeyword( Pageable pageable,  String  keyword, Long centroId);  
	
//@Query ("SELECT d FROM DatosFecha d LEFT JOIN d.tipoprueba tp WHERE CONCAT(d.totalpruebas, ' ', d.fecha, ' ', tp.opcion) LIKE %?1%") 
//public Page <DatosFecha> findAllWithKeyword( Pageable pageable,  String  keyword);  

//@Query ("SELECT d FROM DatosFecha d LEFT JOIN d.tipoprueba tp WHERE d.id IN ?1 AND CONCAT(d.totalpruebas, ' ', d.fecha, ' ', tp.opcion) LIKE %?2%") 
//public Page <DatosFecha> findByIdInWithKeyword( Pageable pageable,  List<Long> lista,  String  keyword);  


}
