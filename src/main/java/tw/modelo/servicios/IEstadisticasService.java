package tw.modelo.servicios;
/** 
 * Interfaz de servicios de acceso Jquery a Datos Gráficos
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso
 *
 */
public interface IEstadisticasService {
	
	String obtenerDiagramaBarrasPorCentroyPacientes();
	
	String obtenerDiagramaSectoresPorRegionyCentros();

}
