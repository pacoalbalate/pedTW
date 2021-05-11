/**
 * 
 */
package tw.modelo.dao;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.modelo.entidades.AuxOpciones;



/**
 * DAO
 * Interfaz del modelo de datos para la Entidad Opciones
 * (Datos parametrizables de la aplicación)
 *
 */
public interface IAuxOpcionesDao extends JpaRepository<AuxOpciones, Long> {
	
public List <AuxOpciones> findByTipoOrderById(String tipo); 

public AuxOpciones findByTipoAndOpcion(String tipo, String opcion);
	
public List <AuxOpciones> findByTipoNotOrderById(String tipo); 

public List <AuxOpciones> findByTipoContainingOrderById(String tipo);



}
