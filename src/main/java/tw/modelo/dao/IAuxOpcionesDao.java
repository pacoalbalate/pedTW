/**
 * 
 */
package tw.modelo.dao;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import tw.modelo.entidades.AuxOpciones;



/**
 * DAO
 * Interfaz del modelo de datos para la Entidad Auxiliar de Opciones
 * (Datos parametrizables de la aplicación)
 *
 */
public interface IAuxOpcionesDao extends JpaRepository<AuxOpciones, Long> {
	
/**
 * Devuelve lista de opciones ordenadas por el id
 * @param tipo de opcion
 * @return list de Dao opciones
 */
public List <AuxOpciones> findByTipoOrderById(String tipo); 

/**
 * Devuelve opcion por tipo y nombre
 * @param tipo
 * @param opcion
 * @return DAO Opciones
 * 
 */
public AuxOpciones findByTipoAndOpcion(String tipo, String opcion);

/**
 * Devuelve lista de opciones que contienen el parametro indicado 
 * como parte del tipo, ordenadas por el id
 * @param tipo de opcion
 * @return list de Dao opciones
 */
public List <AuxOpciones> findByTipoContainingOrderById(String tipo);



}
