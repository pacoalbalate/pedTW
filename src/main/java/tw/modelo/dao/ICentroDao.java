/**
 * 
 */
package tw.modelo.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.modelo.entidades.Centro;




/**
 * DAO
 * Interfaz del modelo de datos para entidad Centro
 *
 */
public interface ICentroDao extends JpaRepository<Centro, Long> {
	
	
/**
 * Devuelve los centros en un objeto paginable para
 * presentar por pantalla según criterios de seleccion
 * @param pageable el objeto página
 * @param keyword Criterios de selección
 * @return 
 */
@Query ("SELECT c FROM Centro c LEFT JOIN c.tipocentro tc WHERE CONCAT(c.denominacion, ' ', c.pacientes, ' ', tc.opcion) LIKE %?1%") 
public Page <Centro> findAllWithKeyword( Pageable pageable,  String  keyword);  

/**
 * Devuelve los centros en un objeto paginable para
 * presentar por pantalla por conjunto de identificadores y criterios de selección
 * @param pageable el objeto página
 * @param centrosId lista de identificadores
 * @param keyword criterios de selección
 * @return 
 */
@Query ("SELECT c FROM Centro c LEFT JOIN c.tipocentro tc WHERE c.id IN ?1 AND CONCAT(c.denominacion, ' ', c.pacientes, ' ', tc.opcion) LIKE %?2%") 
public Page <Centro> findByIdInWithKeyword( Pageable pageable,  List<Long> centrosId,  String  keyword);  

/** 
 * Devuelve lista de centros con datosfecha (pruebas) asociadas
 * de todos los centros y sus pruebas
 * @return 
 */
@Query ("SELECT DISTINCT c FROM Centro c JOIN c.region r JOIN c.datosfecha df") 
public List<Centro> findAllJoinDatos();  

/** 
 * Devuelve lista de todos los centros con datosfecha (pruebas)
 * de los pertenecientes a un conjunto de identificadores de región
 * @param regionesId lista de identificadores de región
 * @return 
 */
@Query ("SELECT DISTINCT c FROM Centro c JOIN c.region r JOIN c.datosfecha df WHERE r.id IN ?1") 
public List<Centro> findAllJoinDatosInRegionesId(List<Long> regionesId);  

/** 
 * Devuelve lista de todos los centros
 * de los pertenecientes a un conjunto de identificadores de región
 * @param regionesId lista de identificadores de región
 * @return 
 */
@Query ("SELECT DISTINCT c FROM Centro c JOIN c.region r WHERE r.id IN ?1") 
public List<Centro> findAllInRegionesId(List<Long> regionesId);  

/**
 * Método que devuelve los centros sin asociar a region
 * @return
 */
public List <Centro> findByRegion_idIsNullOrderByDenominacionAsc();  

////@Query ("SELECT r FROM Region r WHERE CONCAT(r.denominacion, ' ', r.habitantes) LIKE %:Keyword%") 
////public Page <Region> findAllWithKeyword(@Param("Keyword") String keyword, Pageable pageable);

//@Query ("SELECT c FROM Centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc WHERE r.id IN ?1 AND CONCAT(c.denominacion, ' ', c.pacientes, ' ', tc.opcion) LIKE %?2%") 
//public Page <Centro> findByIdInRegionWithKeyword( Pageable pageable, List<Long> centro,  String  keyword);  

//@Query ("SELECT c FROM Centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc WHERE r.id IN ?1 AND c.id IN ?2 AND CONCAT(c.denominacion, ' ', c.pacientes, ' ', tc.opcion) LIKE %?3%") 
//public Page <Centro> findByIdInRegionInCentroWithKeyword( Pageable pageable,  List<Long> region,  List<Long> centro,  String  keyword);  


}
