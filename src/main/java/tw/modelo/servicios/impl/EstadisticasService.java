package tw.modelo.servicios.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import tw.modelo.entidades.Centro;
import tw.modelo.entidades.Region;
import tw.modelo.servicios.ICentroService;
import tw.modelo.servicios.IEstadisticasService;
import tw.modelo.servicios.IRegionService;

@Service
public class EstadisticasService implements IEstadisticasService {

	@Autowired
	private ICentroService centroService;
	
	@Autowired
	private IRegionService regionService;

	@Override
	public String obtenerDiagramaBarrasPorCentroyPacientes() {
		Gson gsonObj = new Gson();
		Map<Object, Object> map = null;
		String dataPoints=null;
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

		List<Centro> centros = centroService.findAll();

		for (Centro centro : centros) {
			map = new HashMap<Object, Object>();
			map.put("label", centro.getDenominacion());
			map.put("y", centro.getPacientes());
			list.add(map);
			
		}
		
		dataPoints = gsonObj.toJson(list);
		return dataPoints;
	}

	@Override
	public String obtenerDiagramaSectoresPorRegionyCentros() {
		Gson gsonObj = new Gson();
		Map<Object, Object> map = null;
		String dataPoints=null;
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		int nCentros=centroService.findAll().size();
		List<Region> regiones = regionService.findAll();

		for (Region region : regiones) {
			map = new HashMap<Object, Object>();
			map.put("label", region.getDenominacion());
			map.put("y", (region.getCentros().size()*100/nCentros));
			list.add(map);
			
		}
		
		dataPoints = gsonObj.toJson(list);
		return dataPoints;
	}

}
