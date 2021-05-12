package tw.modelo.servicios;

import java.util.List;


import tw.modelo.entidades.AuxOpciones;



/** 
 * Interfaz de servicios de acceso Jquery a Opciones
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso a la entidad
 *
 */
public interface IAuxOpcionesService {
	
	public AuxOpciones findById(Long id);
	
	public AuxOpciones findById(String id);
	
	public List<AuxOpciones> findByTipoOrderById(String tipo);
	
	public AuxOpciones findByTipoAndOpcion(String tipo, String opcion);

	public List <AuxOpciones> findByTipoNotOrderByIdSinRol(String tipo); 
	
	public List <AuxOpciones> findByTipoNotOrderById(String tipo); 
	
	public List <AuxOpciones> findByTipoContainingOrderById(String tipo);

}
