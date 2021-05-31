package tw.modelo.servicios;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import tw.modelo.entidades.DatosPerfil;


/** 
 * Interfaz de servicios de acceso Jquery a Perfiles
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso a la entidad
 *
 */
public interface IDatosPerfilService {
	
	/**
	 * Grabación del perfil en base de datos
	 * @param datosperfil El perfil a grabar
	 */
	void save(DatosPerfil datosperfil);

	/**
	 * Busca un perfil por identificador
	 * @param id
	 * @return
	 */
	DatosPerfil findById(Long id);

	/**
	 * Borrado de un perfil
	 * @param id El perfil a borrar
	 */
	void delete(Long id);

	/**
	 * Busca Perfiles por regiones, fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param regiones lista de regiones
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados
	 */
	Page<Object> findByIdInRegionWithKeywordDistint(Pageable pageable, List<Long> regiones, String keyword, Date desde,
			Date hasta);

	/**
	 * Busca Perfiles por centros, fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param centros lista de centros
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados
	 */
	Page<Object> findByIdInCentroWithKeywordDistint(Pageable pageable, String keyword, List<Long> centros, Date desde,
			Date hasta);

	/**
	 * Busca Perfiles por Pruebas (DatosFecha), fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param datos lista de Pruebas
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados
	 */
	Page<Object> findByIdInDatoWithKeywordDistint(Pageable pageable, List<String> datos, String keyword, Date desde,
			Date hasta);

	/**
	 * Busca Perfiles por Regiones, Pruebas (DatosFecha), fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param regiones lista de regiones
	 * @param datos lista de Pruebas
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados
	 */
	Page<Object> findByIdInRegionDatoWithKeywordDistint(Pageable pageable, List<Long> regiones, String keyword,
			List<String> datos, Date desde, Date hasta);

	/**
		 * Busca Perfiles por Centros, Pruebas (DatosFecha), fechas y criterios de selección,
		 * devolviéndolos en el objeto paginable según el parámetro
		 * @param pageable El objeto paginable
		 * @param centros lista de centros
		 * @param datos lista de Pruebas
		 * @param keyword criterios de selección
		 * @param desde Fecha desde
		 * @param hasta Fecha hasta
		 * @return El objeto paginable con los perfiles cargados
		 */
	Page<Object> findByIdInCentroDatoWithKeywordDistint(Pageable pageable, List<Long> centros, String keyword,
			List<String> datos, Date desde, Date hasta);

	/**
	 * Busca Perfiles por Regiones, Centros (DatosFecha), fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param regiones lista de regiones
	 * @param centros lista de centros
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados
	 */
	Page<Object> findByIdInRegionCentroWithKeywordDistint(Pageable pageable, List<Long> regiones, String keyword,
			List<Long> centros, Date desde, Date hasta);

	/**
	 * Busca Perfiles por Regiones, Centros, Pruebas (DatosFecha), fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param regiones lista de regiones
	 * @param centros lista de centros
	 * @param datos lista de Pruebas
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados
	 */
	Page<Object> findByIdInWithKeywordDistint(Pageable pageable, List<Long> regiones, String keyword,
			List<Long> centros, List<String> datos, Date desde, Date hasta);

	/**
	 * Devuelve la suma de psitivos de los perfiles de una Prueba
	 * @param datofechaId Identificador de la Prueba (datosfecha)
	 * @return 
	 */
	Long sumaPositivosPerfil(Long datofechaId);

	/**
	 * Cuenta los perfiles de un Centro
	 * @param centroId Identificador del centro
	 * @return
	 */
	Long cuentaDatosCentro(Long centroId);

	/**
	 * Cuenta los perfiles que contienen una pregunta pasada por parámetro
	 * @param preguntaId
	 * @return
	 * 
	 */
	Long cuentaDatosPregunta(Long preguntaId);

	/**
	 * Busca Perfiles por fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados 
	 */
	Page<Object> findAllWithKeywordDistintObject(Pageable pageable, String keyword, Date desde, Date hasta);

	/**
	 * Cuenta los perfiles de una Prueba
	 * @param preguntaId Identificador de la Prueba
	 * @return
	 */
	List<Long> cuentaPrueba(Long preguntaId);
}
