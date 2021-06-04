package tw.modelo.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.modelo.entidades.Centro;



/** 
 * Interfaz de servicios de acceso Jquery a Centro
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso a la entidad
 *
 */
public interface ICentroService {
	
	/**
	 * Guarda el centro en BD
	 * @param centro
	 */
	void save(Centro centro);

	/**
	 * Búsqueda de centro por identificador
	 * @param id
	 */
	Centro findById(Long id);

	/**
	 * Borrado del centro por identificador
	 * @param id
	 */
	void delete(Long id);

	/**
	 * Devuelve lista de todos los centros
	 * @return
	 */
	List<Centro> findAll();

	/**
	 * Devuelve los centros en un objeto paginable para
	 * presentar por pantalla
	 * @param pageable el objeto página
	 * @return 
	 */
	Page<Centro> findAll(Pageable pageable);

	/**
	 * Devuelve los centros en un objeto paginable para
	 * presentar por pantalla según criterios de seleccion
	 * @param pageable el objeto página
	 * @param keyword Criterios de selección
	 * @return 
	 */
	Page<Centro> findAllWithKeyword(Pageable pageable, String keyword);

	/**
	 * Devuelve los centros en un objeto paginable para
	 * presentar por pantalla todos o por conjunto de identificadores y criterios de selección
	 * @param pageable el objeto página
	 * @param centrosId lista de identificadores
	 * @param keyword criterios de selección
	 * @return 
	 */
	Page<Centro> findByIdInWithKeyword(Pageable pageable, List<Long> centrosId, String keyword);

	/** 
	 * Devuelve lista de centros con datosfecha (pruebas) asociadas
	 * de todos los centros y sus pruebas
	 * @return 
	 */
	List<Centro> findAllJoinDatos();

	/** 
	 * Devuelve lista de todos los centros con datosfecha (pruebas)
	 * de los pertenecientes a un conjunto de identificadores de región
	 * @param regionesId lista de identificadores de región
	 * @return 
	 */
	List<Centro> findAllJoinDatosInRegionesId(List<Long> regionesId);

	/** 
	 * Devuelve lista de todos los centros 
	 * de los pertenecientes a un conjunto de identificadores de región
	 * @param regionesId lista de identificadores de región
	 * @return 
	 */
	List<Centro> findAllInRegionesId(List<Long> regionesId);

	/**
	 * Método que devuelve los centros sin asociar a region
	 * @return
	 */
	List<Centro> findByRegion_idIsNullOrderByDenominacionAsc();

}
