/**
 * 
 */
package tw.modelo.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.modelo.entidades.DatosFecha;




/** 
 * @author Portatil
 *
 */
public interface IDatosFechaService {
	
	
	public DatosFecha findById(Long id);

	public List<DatosFecha> findAll();

	public Page<DatosFecha> findAll(Pageable pageable);

	public Page<DatosFecha> findAllWithKeyword(Pageable pageable, String keyword); 
	
	public Page <DatosFecha> findAllWithKeyword( Pageable pageable,  String  keyword, Long centroId);
	
	//public Page<DatosFecha> findByIdInWithKeyword(Pageable pageable, List<Long> lista, String keyword);
	
	public void save(DatosFecha datosfecha);

	public void delete(Long id);
	
	

}
