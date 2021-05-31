package tw.modelo.servicios;

import java.util.List;

import tw.modelo.entidades.DatosPerfil;

/** 
 * Interfaz de servicios de acceso Jquery a Datos Gráficos
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso
 *
 */
public interface IEstadisticasService {
	
	/**
	 * Devuelve en un String el json resultado para presentar por pantalla
	 * el diagrama de barras del total de positivos por Centro
	 * @return   
	 */
	String obtenerDiagramaBarrasPorCentroyTotalPositivos();

	/**
	 * Devuelve en un String el json resultado para presentar por pantalla
	 * el diagrama de sectores de porcentaje de positivos por región
	 * @return   
	 */
	String obtenerDiagramaSectoresPorRegionyCentros();

	/**
	 * Devuelve en un String el json resultado de los datos para presentar
	 * por pantalla los graficos que se le solicita segun el tipo y grupo indicados
	 * @return   
	 */
	String obtenerDatosGrafica(List<DatosPerfil> lista, String tipo, String grupo);

}
