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
	
	public void save(DatosPerfil datosperfil);

	public DatosPerfil findById(Long id);

	public void delete(Long id);
	
	public Long sumaPositivosPerfil(Long datofechaId);
	
	public Long cuentaDatosCentro(Long centroId);

	public Long cuentaDatosPregunta(Long preguntaId);
	
	public Page <Object> findByIdInCentroWithKeywordDistint( Pageable pageable,  String  keyword, List<Long> centros, Date desde, Date hasta);

	public Page <Object> findByIdInRegionWithKeywordDistint( Pageable pageable,  List<Long> regiones,  String  keyword, Date desde, Date hasta);  
	
	public Page <Object> findByIdInDatoWithKeywordDistint( Pageable pageable,  List<String> datos,  String  keyword, Date desde, Date hasta);
	
	public Page <Object> findByIdInRegionDatoWithKeywordDistint( Pageable pageable,  List<Long> regiones,  String  keyword,  List<String> datos, Date desde, Date hasta);  

	public Page <Object> findByIdInRegionCentroWithKeywordDistint( Pageable pageable,  List<Long> regiones,  String  keyword,  List<Long> centros, Date desde, Date hasta);   
	
	public Page <Object> findByIdInCentroDatoWithKeywordDistint( Pageable pageable,  List<Long> centros,  String  keyword,  List<String> datos, Date desde, Date hasta);  
	
	public Page <Object> findByIdInWithKeywordDistint( Pageable pageable,  List<Long> regiones,  String  keyword,  List<Long> centros, List<String> datos, Date desde, Date hasta);   

	public Page <Object> findAllWithKeywordDistintObject( Pageable pageable,  String  keyword, Date desde, Date hasta);

	public List<Long> cuentaPrueba(Long preguntaId); 
}
