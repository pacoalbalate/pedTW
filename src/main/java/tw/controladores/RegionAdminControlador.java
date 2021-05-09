package tw.controladores;
/**
 * Clase del controlador.
 * Encargada de la gestión de regiones por 
 * parte del usuario gestor
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tw.controladores.utilidades.paginas.PagNavegador;
import tw.controladores.utilidades.paginas.PaginaCriterios;
import tw.modelo.entidades.Centro;
import tw.modelo.entidades.Region;
import tw.modelo.servicios.ICentroService;
import tw.modelo.servicios.IRegionService;

@Controller 
@SessionAttributes({"region", "criterios"})
@RequestMapping("/")
public class RegionAdminControlador {
	
	@Autowired
	//@Qualifier("RegionDaoImplDupl")
	private IRegionService regionService;
	
	@Autowired
	private ICentroService centroService;
	
	/**
	 * Listado de las regiones existentes
	 * 
	 * @param params
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@GetMapping("admin/region/list")
	public String listado(@RequestParam Map<String, Object> params, 
				Model modelo, RedirectAttributes flash) {
		String Pag_actual = "admin/region/list";
		
		//Mantener los datos de navegacion existentes
		PaginaCriterios criterios;
		if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			if (criterios.getPagActual().equals(Pag_actual)) {
				criterios.setParametros(params);
			} else {
				criterios = new PaginaCriterios(params);
				criterios.setPagActual(Pag_actual);
				criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Denominación", "Habitantes", "Acciones")));
				criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "denominacion", "habitantes", "")));
			}
		} else {
			criterios = new PaginaCriterios(params);
			criterios.setPagActual(Pag_actual);
			criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Denominación", "Habitantes", "Acciones")));
			criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "denominacion", "habitantes", "")));
		}
		Pageable pageable = PageRequest.of(criterios.getPageNo(), criterios.getPageSize(), Sort.by(Sort.Direction.valueOf(criterios.getOrderBy()),criterios.getSortBy()));
		
		Page<Region> regiones = regionService.findAllWithKeyword(pageable, criterios.getFiltro());
		PagNavegador<Region> pageSelect = new PagNavegador<>(Pag_actual, regiones);

		modelo.addAttribute("titulo", "Gestión de <b>Regiones</b>");
		modelo.addAttribute("criterios", criterios);
		modelo.addAttribute("pagina", pageSelect);

		return "region/list";
	}

	/**
	 * Creación nueva región
	 * 
	 * @param modelo
	 * @return
	 */
	@GetMapping("admin/region/new")
	public String formulario_new(Model modelo) {
		Region region = new Region();
		
		List<Centro> centros = centroService.findByRegion_idIsNullOrderByDenominacionAsc();

		modelo.addAttribute("titulo", "Formulario de <b>Edición de Región</b>");
		modelo.addAttribute("region", region);
		modelo.addAttribute("centroslibres", centros);
		return "region/form";
	}

	/**
	 * Edición de región existente
	 * 
	 * @param Id
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@GetMapping("admin/region/edit/{regId}/")
	public String formulario_edita(@PathVariable("regId") Long Id,
				Model modelo, RedirectAttributes flash) {
		Region region = (Region) regionService.findByIdWithCentros(Id);
//		Region region = (Region) regionService.findById(Id);
		if (region == null) {
			flash.addFlashAttribute("error", "La región no existe en la base de datos");
			return "redirect:/admin/region/list";
		}
		List<Centro> centros = centroService.findByRegion_idIsNullOrderByDenominacionAsc();

		modelo.addAttribute("titulo", "Formulario de <b>Edición de Región</b>");
		modelo.addAttribute("region", region);
		modelo.addAttribute("centroslibres", centros);

		return "region/form";
	}

	/**
	 * Método encargado de asociar un centro a una región
	 * @param Id
	 * @param centroId
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@PostMapping("admin/region/edit/{regId}/asoc")
	public String asocia_centro(@PathVariable("regId") Long Id,
				@RequestParam(value = "centro", required = true) Long centroId,
				Model modelo, RedirectAttributes flash) {
		Region region = (Region) regionService.findById(Id);
		Centro centro = (Centro) centroService.findById(centroId);
		if (centro == null || region== null) {
			flash.addFlashAttribute("error", "La región o el centro no existen en la base de datos");
			return "redirect:/admin/region/list";
		} else {
			centro.setRegion(region);
			centroService.save(centro);
			flash.addFlashAttribute("success", "Centro asociado con éxito");
		}
		return "redirect:/admin/region/edit/"+Id+"/";
	}

	/**
	 * Método encargado de deshacer la asociación entre un centro y una región
	 * @param Id
	 * @param centroId
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@GetMapping("admin/region/edit/{regId}/desa/{centroId}/")
	public String desasocia_centro(@PathVariable("regId") Long Id,
				@PathVariable("centroId") Long centroId, Model modelo,
				RedirectAttributes flash) {
		Centro centro = (Centro) centroService.findById(centroId);
		if (centro == null) {
			flash.addFlashAttribute("error", "El centro no existe en la base de datos");
			return "redirect:/admin/region/list";
		} else {
			centro.setRegion(null);
			centroService.save(centro);
			flash.addFlashAttribute("success", "Centro desasociado con éxito");
		}
		return "redirect:/admin/region/edit/"+Id+"/";
	}
	
	/**
	 * Guarda en BD la región editada o creada
	 * 
	 * @param region
	 * @param resultado
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@PostMapping({"admin/region/save"})
	public String guarda(@Valid @ModelAttribute("region") Region region, BindingResult resultado, 
				Map<String, Object> modelo, RedirectAttributes flash) {
			//, SessionStatus status) {
		if(resultado.hasErrors()) {
			List<Centro> centros = centroService.findByRegion_idIsNullOrderByDenominacionAsc();
			modelo.put("titulo", "Formulario de <b>Edición de Región</b>");
			modelo.put("region", region);
			modelo.put("centroslibres", centros);
			modelo.put("danger", "La datos no se han almacenado con éxito");
			return "region/form";
		}
		regionService.save(region);
		modelo.remove("region");
		//status.setComplete();
		flash.addFlashAttribute("success", "La región se ha almacenado con éxito");
		return "redirect:/admin/region/edit/"+region.getId()+"/";
		//return "redirect:/admin/region/list";
	}

	/**
	 * Borra una región
	 * 
	 * @param Id
	 * @param flash
	 * @return
	 */
	@PostMapping("admin/region/del")
	public String borra(@RequestParam("regId") Long Id,
				RedirectAttributes flash) {
		if(Id>0) {
			Region regiondel = regionService.findById(Id);
			List<Centro> centros = regiondel.getCentros();
			int contadorcentros = 0;
			for (Centro centro : centros) {
				centro.setRegion(null);
				centroService.save(centro);
				contadorcentros = contadorcentros +1;
			}
			regionService.delete(Id);
			flash.addFlashAttribute("info", "Se han desasociado "+ contadorcentros +" centros.");
			flash.addFlashAttribute("success", "La región se ha eliminado con éxito");
		} else {
			flash.addFlashAttribute("error", "La región no éxiste en la base de datos");
		}
		return "redirect:/admin/region/list";
	}
	

}








