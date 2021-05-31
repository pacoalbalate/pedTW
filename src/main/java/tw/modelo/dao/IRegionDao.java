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
	

/**
 * Devuelve una región por Identificador con todos sus centros asociados o null si no existe
 * @param id Identificador de región
 * @return Región
 */
@Query ("SELECT DISTINCT r FROM Region r LEFT JOIN r.centros c WHERE r.id=?1") 
public Region findByIdWithCentros(Long id);  
	
/**
 * Obtiene todas las regiones según criterios de filtrado para el listado
 * pasados por parámetro y las devuelve en un objeto de paginación
 * @param pageable
 * @param keyword Criterios de selección
 * @return Page<Region>
 * 
 */
@Query ("SELECT DISTINCT r FROM Region r LEFT JOIN r.centros c WHERE CONCAT(r.denominacion, ' ', r.habitantes) LIKE %?1%") 
public Page <Region> findAllWithKeyword( Pageable pageable,  String  keyword);  

/**
 * Devuelve las regiones ya asociadas a las Pruebas y sus Pruebas (datosfecha)
 * @return List<Region>
 */
@Query ("SELECT DISTINCT r FROM Region r JOIN r.centros c JOIN c.datosfecha df") 
public List<Region> findAllJoinDatos();  
	

}
