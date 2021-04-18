/**
 * 
 */
package tw.modelo.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.modelo.entidades.Region;

/** 
 * @author Portatil
 *
 */
public interface IRegionService {
	
	public List<Region> findAll();
	
	public Page<Region> findAll(Pageable pageable);
	
	public Page<Region> findAllWithKeyword(Pageable pageable, String keyword);
	
	public List<Region> findAllJoinDatos(); 
	
	public void save(Region region);

	public Region findById(Long id);

	public Region findByIdWithCentros(Long id);  
	
	public void delete(Long id);

}
