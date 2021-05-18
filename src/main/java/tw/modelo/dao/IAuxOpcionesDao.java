/**
 * 
 */
package tw.modelo.dao;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	
@Query ("SELECT a FROM AuxOpciones a where tipo != 'TIPO_ROL' and tipo != 'TIPO_CENTRO'")
public List <AuxOpciones> findByTipoNotOrderByIdSinRol(String tipo); 

public List <AuxOpciones> findByTipoNotOrderById(String tipo); 

public List <AuxOpciones> findByTipoContainingOrderById(String tipo);



}
