package tw.modelo.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.modelo.entidades.DatosFecha;




/** 
 * Interfaz de servicios de acceso Jquery a DatosFecha
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso a la entidad (Pruebas)
 *
 */
public interface IDatosFechaService {
	
	
	/**
	 * Graba en bd la prueba
	 * @param datosfecha la prueba a grabar
	 */
	void save(DatosFecha datosfecha);

	/**
	 * Borra de bd la prueba
	 * @param id el identificador de la prueba a borrar
	 */
	void delete(Long id);

	/**
	 * Busca la prueba por identificador
	 * @param id el identificador de la prueba
	 * @return La prueba
	 */
	DatosFecha findById(Long id);

	/**
	 * Busca todas las pruebas
	 * @return Lista de pruebas
	 */
	List<DatosFecha> findAll();

	/**
	 * Devuelve las pruebas en objeto paginable
	 * @param pageable el objeto paginable
	 * @return 
	 */
	Page<DatosFecha> findAll(Pageable pageable);

	/**
	 * Devuelve las pruebas en objeto paginable por identificador del centro y
	 * criterios de selección
	 * @param pageable el objeto paginable
	 * @param keyword Criterios de selección
	 * @param centroId identificador del centro
	 * @return
	 */
	Page<DatosFecha> findAllWithKeyword(Pageable pageable, String keyword, Long centroId);

	/**
	 * Devuelve las pruebas en objeto paginable por criterios de selección del listado
	 * @param pageable el objeto paginable
	 * @param keyword Criterios de selección
	 * @return
	 */
	Page<DatosFecha> findAllWithKeyword(Pageable pageable, String keyword);

	
	

}
