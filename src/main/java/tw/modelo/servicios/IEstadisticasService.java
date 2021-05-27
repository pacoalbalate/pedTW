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
	
	String obtenerDiagramaBarrasPorCentroyTotalPositivos();
	
	String obtenerDiagramaSectoresPorRegionyCentros();
	
	String obtenerDatosGrafica(List<DatosPerfil> lista, String tipo, String grupo);

}
