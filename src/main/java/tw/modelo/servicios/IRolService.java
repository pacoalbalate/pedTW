package tw.modelo.servicios;

import java.util.List;


import tw.modelo.entidades.Rol;



/** 
 * Interfaz de servicios de acceso Jquery a ROL
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso a la entidad
 *
 */
public interface IRolService {
	
	public List<Rol> findAllByIdUser(Long id); 
	
	public List<Rol> findAllByNameUser(String nombreusuario);  

	public Rol findById(Long id);
	
	public void save(Rol rol);

	public void delete(Long id);
	
}
