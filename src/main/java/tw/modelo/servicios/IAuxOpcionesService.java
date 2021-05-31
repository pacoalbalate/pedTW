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
	/**
	 * Busca por Id de opción
	 * @param el id
	 * @return DAO Opciones
	 * 
	 */
	AuxOpciones findById(Long id);

	/**
	 * Busca por Id de opción
	 * sobrecarga del metodo en formato String
	 * @param el id
	 * @return DAO Opciones
	 * 
	 */
	AuxOpciones findById(String id);

	/**
	 * Devuelve lista de opciones ordenadas por el id
	 * @param tipo de opcion
	 * @return list de Dao opciones
	 */
	List<AuxOpciones> findByTipoOrderById(String tipo);

	/**
	 * Devuelve opcion por tipo y nombre
	 * @param tipo
	 * @param opcion
	 * @return DAO Opciones
	 * 
	 */
	AuxOpciones findByTipoAndOpcion(String tipo, String opcion);

	/**
	 * Devuelve lista de opciones que contienen el parametro indicado 
	 * como parte del tipo, ordenadas por el id
	 * @param tipo de opcion
	 * @return list de Dao opciones
	 */
	List<AuxOpciones> findByTipoContainingOrderById(String tipo);

}
