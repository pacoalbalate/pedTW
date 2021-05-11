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
	
	public List<Centro> findAll();

	public Page<Centro> findAll(Pageable pageable);
	
	public Page<Centro> findAllWithKeyword(Pageable pageable, String keyword);

	public Centro findById(Long id);
	
	public void save(Centro centro);

	public void delete(Long id);
	
	public Page<Centro> findByIdInWithKeyword(Pageable pageable, List<Long> centrosId, String keyword);

	public List<Centro> findAllJoinDatos();  
	
	public List<Centro> findAllJoinDatosInRegionesId(List<Long> regionesId);
	
	public List<Centro> findByRegion_idIsNullOrderByDenominacionAsc(); 


}
