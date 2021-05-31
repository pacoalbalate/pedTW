package tw.modelo.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.modelo.entidades.Region;

/** 
 * Interfaz de servicios de acceso Jquery a Región
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso a la entidad
 *
 */
public interface IRegionService {
	
	/**
	 * Almacena Región en BD
	 * @param region Región a guardar
	 */
	void save(Region region);

	/**
	 * Devuelve una región por Identificador o null si no existe
	 * @param id Identificador de región
	 * @return Región/null
	 */
	Region findById(Long id);

	/**
	 * Devuelve una región por Identificador con todos sus centros asociados o null si no existe
	 * @param id Identificador de región
	 * @return Región
	 */
	Region findByIdWithCentros(Long id);

	/**
	 * Borra la región con Identificador pasado por parámetro
	 * @param id Id de la región a borrar
	 * 
	 */
	void delete(Long id);

	/**
	 * Obtiene todas las regiones y las devuelve en un List
	 * @return List <Region>
	 * 
	 */
	List<Region> findAll();

	/**
	 * Obtiene todas las regiones y las devuelve en un objeto de paginación
	 * @param pageable
	 * @return Page <Region>
	 * 
	 */
	Page<Region> findAll(Pageable pageable);

	/**
	 * Obtiene todas las regiones según criterios de filtrado para el listado
	 * pasados por parámetro y las devuelve en un objeto de paginación
	 * @param pageable
	 * @param keyword Criterios de selección
	 * @return Page <Region>
	 * 
	 */
	Page<Region> findAllWithKeyword(Pageable pageable, String keyword);

	/**
	 * Devuelve las regiones ya asociadas a las Pruebas y sus Pruebas (datosfecha)
	 * @return List <Region>
	 */
	List<Region> findAllJoinDatos();

}
