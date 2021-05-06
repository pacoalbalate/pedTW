/**
 * 
 */
package tw.modelo.servicios;

import java.util.List;


import tw.modelo.entidades.Rol;



/** 
 * @author Portatil
 *
 */
public interface IRolService {
	
	public List<Rol> findAllByIdUser(Long id); 
	
	public List<Rol> findAllByNameUser(String nombreusuario);  

	public Rol findById(Long id);
	
	public void save(Rol rol);

	public void delete(Long id);
	
}
