/**
 * 
 */
package tw.modelo.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.modelo.entidades.DatosPerfil;


/**
 * DAO
 * Interfaz del modelo de datos para la entidad Perfil
 *
 */
public interface IDatosPerfilDao extends JpaRepository<DatosPerfil, Long> { 
	
	
	/**
	 * Busca Perfiles por fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados 
	 */
	@Query ("SELECT DISTINCT dp, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?1% AND df.fecha BETWEEN ?2 AND ?3 ") 
	public Page <Object> findAllWithKeywordDistintObject( Pageable pageable,  String  keyword, Date desde, Date hasta);   

	/**
	 * Busca Perfiles por fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados
	 */
	@Query ("SELECT DISTINCT dp, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?1% AND df.fecha BETWEEN ?2 AND ?3 ") 
	public Page <Object> findAllWithKeywordDistint( Pageable pageable,  String  keyword, Date desde, Date hasta);  

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
	@Query ("SELECT DISTINCT dp, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE r.id IN ?1 AND c.id IN ?3 AND dt.dato IN ?4 AND CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?2%  AND df.fecha BETWEEN ?5 AND ?6 ") 
	public Page <Object> findByIdInWithKeywordDistint( Pageable pageable,  List<Long> regiones,  String  keyword,  List<Long> centros, List<String> datos, Date desde, Date hasta);   

	/**
	 * Busca Perfiles por Regiones, Centros, fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param regiones lista de regiones
	 * @param centros lista de centros
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados
	 */
	@Query ("SELECT DISTINCT dp, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE r.id IN ?1 AND c.id IN ?3 AND CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?2% AND df.fecha BETWEEN ?4 AND ?5 ") 
	public Page <Object> findByIdInRegionCentroWithKeywordDistint( Pageable pageable,  List<Long> regiones,  String  keyword,  List<Long> centros, Date desde, Date hasta);   

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
	@Query ("SELECT DISTINCT dp, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp  LEFT JOIN dp.datos dt WHERE c.id IN ?2 AND CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?1% AND df.fecha BETWEEN ?3 AND ?4 ") 
	public Page <Object> findByIdInCentroWithKeywordDistint( Pageable pageable,  String  keyword, List<Long> centros, Date desde, Date hasta);  

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
	@Query ("SELECT DISTINCT dp, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE r.id IN ?1 AND CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?2% AND df.fecha BETWEEN ?3 AND ?4 ") 
	public Page <Object> findByIdInRegionWithKeywordDistint( Pageable pageable,  List<Long> regiones,  String  keyword, Date desde, Date hasta);  

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
	@Query ("SELECT DISTINCT dp, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE dt.dato IN ?1 AND CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?2% AND df.fecha BETWEEN ?3 AND ?4 ") 
	public Page <Object> findByIdInDatoWithKeywordDistint( Pageable pageable,  List<String> datos,  String  keyword, Date desde, Date hasta);  

	/**
	 * Busca Perfiles por Regiones, Centros (DatosFecha), fechas y criterios de selección,
	 * devolviéndolos en el objeto paginable según el parámetro
	 * @param pageable El objeto paginable
	 * @param regiones lista de regiones
	 * @param datos lista de datos
	 * @param keyword criterios de selección
	 * @param desde Fecha desde
	 * @param hasta Fecha hasta
	 * @return El objeto paginable con los perfiles cargados
	 */
	@Query ("SELECT DISTINCT dp, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE r.id IN ?1 AND dt.dato IN ?3 AND CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?2% AND df.fecha BETWEEN ?4 AND ?5 ") 
	public Page <Object> findByIdInRegionDatoWithKeywordDistint( Pageable pageable,  List<Long> regiones,  String  keyword,  List<String> datos, Date desde, Date hasta);  

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
	@Query ("SELECT DISTINCT dp, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE c.id IN ?1 AND dt.dato IN ?3 AND CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?2% AND df.fecha BETWEEN ?4 AND ?5 ") 
	public Page <Object> findByIdInCentroDatoWithKeywordDistint( Pageable pageable,  List<Long> centros,  String  keyword,  List<String> datos, Date desde, Date hasta);  

	/**
	 * Devuelve la suma de psitivos de los perfiles de una Prueba
	 * @param datofechaId Identificador de la Prueba (datosfecha)
	 * @return 
	 */
	@Query("SELECT SUM(dp.totalpositivos) FROM DatosPerfil dp LEFT JOIN dp.datosfecha df WHERE df.id = ?1")
	public Long sumaPositivosPerfil(Long datofechaId);

	/**
	 * Cuenta los perfiles de un Centro
	 * @param centroId Identificador del centro
	 * @return
	 */
	@Query("SELECT COUNT(dp.id) FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c WHERE c.id = ?1")
	public Long cuentaDatosCentro(Long centroId);

	/**
	 * Cuenta los perfiles que contienen una pregunta pasada por parámetro
	 * @param preguntaId
	 * @return
	 * 
	 */
	@Query("SELECT COUNT(dp.id) FROM DatosPerfil dp LEFT JOIN dp.datos dt LEFT JOIN dt.pregunta p WHERE p.id = ?1")
	public Long cuentaDatosPregunta(Long preguntaId); 

	/**
	 * Cuenta los perfiles de una Prueba
	 * @param preguntaId Identificador de la Prueba
	 * @return
	 */
	@Query ("SELECT count(dp.id) FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?1%") 
	public List<Long> cuentaPrueba(Long preguntaId); 

	//@Query("SELECT COUNT(df.id), SUM(df.totalpruebas) FROM DatosFecha df")
	//@Query ("SELECT  DISTINCT dp, COUNT(dp.id), SUM(df.totalpruebas), SUM(dp.totalpositivos), dp.id, r.denominacion, c.denominacion, tc.opcion, df.totalpruebas, df.fecha, tp.opcion, dp.totalpositivos FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?1% ") 
	//@Query ("SELECT COUNT(dp.id), SUM(dp.totalpositivos) FROM DatosPerfil dp LEFT JOIN dp.datosfecha df LEFT JOIN df.centro c LEFT JOIN c.region r LEFT JOIN c.tipocentro tc LEFT JOIN df.tipoprueba tp LEFT JOIN dp.datos dt WHERE CONCAT(df.fecha, ' ', r.denominacion, ' ', c.denominacion, ' ', tc.opcion, ' ', tp.opcion, ' ', df.totalpruebas, ' ', dp.totalpositivos, ' ', dt.dato) LIKE %?1%") 

}
