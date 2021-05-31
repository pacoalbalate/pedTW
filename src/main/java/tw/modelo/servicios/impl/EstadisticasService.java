package tw.modelo.servicios.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import tw.modelo.entidades.Centro;
import tw.modelo.entidades.DatosFecha;
import tw.modelo.entidades.DatosPerfil;
import tw.modelo.entidades.Region;
import tw.modelo.servicios.ICentroService;
import tw.modelo.servicios.IEstadisticasService;
import tw.modelo.servicios.IRegionService;
/**
 * Implementa el interfaz Façade - Datos Gráficos
 * 
 * Redirige las peticiones a los métodos del DAO
 * 
 */
@Service
public class EstadisticasService implements IEstadisticasService {

	@Autowired
	private ICentroService centroService;
	
	@Autowired
	private IRegionService regionService;

	/**
	 * Devuelve en un String el json resultado para presentar por pantalla
	 * el diagrama de barras del total de positivos por Centro
	 * @return   
	 */
	@Override
	public String obtenerDiagramaBarrasPorCentroyTotalPositivos() {
		Gson gsonObj = new Gson();
		Map<Object, Object> map = null;
		String dataPoints=null;
	
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

		List<Centro> centros = centroService.findAll();

		for (Centro centro : centros) {
			long tp = 0;
			for (DatosFecha datosFecha :  centro.getDatosfecha()) {
				for (DatosPerfil datosPerfil : datosFecha.getDatosperfiles()) {
					tp+=datosPerfil.getTotalpositivos();
				}
			}
		
			map = new HashMap<Object, Object>();
			map.put("label", centro.getDenominacion());
		// 	map.put("y", centro.getPacientes());
			map.put("y", tp);
			list.add(map);
			
		}
		
		dataPoints = gsonObj.toJson(list);
		return dataPoints;
	}

	/**
	 * Devuelve en un String el json resultado para presentar por pantalla
	 * el diagrama de sectores de porcentaje de positivos por región
	 * @return   
	 */
	@Override
	public String obtenerDiagramaSectoresPorRegionyCentros() {
		Gson gsonObj = new Gson();
		Map<Object, Object> map = null;
		String dataPoints=null;
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		// int nCentros=centroService.findAll().size();
		
		
		List<Region> regiones = regionService.findAll();
// Calcular el total de positivos general
		long npTotal=0;
		for (Region regionT : regiones) {
			
			map = new HashMap<Object, Object>();
			map.put("label", regionT.getDenominacion());
			for (Centro centroT : regionT.getCentros()) {
				
				for (DatosFecha datosFechaT :  centroT.getDatosfecha()) {
					for (DatosPerfil datosPerfilT : datosFechaT.getDatosperfiles()) {
						npTotal+=datosPerfilT.getTotalpositivos();
					}
				}
			}
		}
			
		
		
		for (Region region : regiones) {
			long tp = 0;
			map = new HashMap<Object, Object>();
			map.put("label", region.getDenominacion());
			for (Centro centro : region.getCentros()) {
				
				for (DatosFecha datosFecha :  centro.getDatosfecha()) {
					for (DatosPerfil datosPerfil : datosFecha.getDatosperfiles()) {
						tp+=datosPerfil.getTotalpositivos();
					}
				}
			}
			
				//map.put("y", tp);
			map.put("y", (tp*100/npTotal));
			list.add(map);
			
		}
		
		dataPoints = gsonObj.toJson(list);
		return dataPoints;
	}
	
	/**
	 * Devuelve en un String el json resultado de los datos para presentar
	 * por pantalla los graficos que se le solicita segun el tipo y grupo indicados
	 * @return   
	 */
	@Override
	public String obtenerDatosGrafica(List<DatosPerfil> lista, String tipo, String grupo) {
		Gson gsonObj = new Gson();
		Map<Object, Object> map = null;
		String dataPoints=null;
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

		for (Entry<String, Long> datosPerfil : getGrupoAndTotal(lista, tipo, grupo).entrySet()) {
			map = new LinkedHashMap<Object, Object>();
			map.put("label", datosPerfil.getKey());
			map.put("y", datosPerfil.getValue());
			list.add(map);
			
		}
		
		dataPoints = gsonObj.toJson(list);
		return dataPoints;
	}
	
	
	
	/**
	 * Agrupa y totaliza los datos para presentar por pantalla los graficos
	 * que se le solicita segun el tipo y grupo indicados
	 * @return   
	 */
	private Map<String,Long> getGrupoAndTotal(List<DatosPerfil> lista, String tipo, String grupo){
		Map<String,Long> grupos= new LinkedHashMap<String, Long>();
		
		for (DatosPerfil datosPerfil : lista) {
			String denominacion="";
			switch(grupo) {
			  case "Centro":
				  denominacion=datosPerfil.getDatosfecha().getCentro().getDenominacion();
			    break;
			  case "Fecha":
				  denominacion=datosPerfil.getDatosfecha().getFecha().toString();
			    break;
			  case "Tipo de Prueba":
				  denominacion=datosPerfil.getDatosfecha().getTipoprueba().getOpcion();
			    break;
			  case "Región":
				  denominacion=datosPerfil.getDatosfecha().getCentro().getRegion().getDenominacion();
			    break;
			  case "Tipo de Centro":
				  denominacion=datosPerfil.getDatosfecha().getCentro().getTipocentro().getOpcion();
			    break;
			  default:
				  denominacion=datosPerfil.getDatos().get(Integer.parseInt(grupo)).getDato();
			    // code block
			}
			
			Long num=0L;
			switch(tipo) {
			  case "pruebas":
					num=datosPerfil.getDatosfecha().getTotalpruebas();
			    break;
			  case "positivos":
					num = datosPerfil.getTotalpositivos();
			    break;
			  	//default:
			    // code block
			}
			
			
			if(grupos.containsKey(denominacion)) {
				 num+=grupos.get(denominacion);
			}
			grupos.put(denominacion,num);
		}
		
		return grupos;
		
	}
	
}
