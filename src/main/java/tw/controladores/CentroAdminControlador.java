package tw.controladores;


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
import tw.modelo.entidades.AuxOpciones;
import tw.modelo.servicios.IAuxOpcionesService;
import tw.modelo.servicios.ICentroService;
import tw.modelo.servicios.IDatosPerfilService;

@Controller 
@SessionAttributes({"centro", "criterios"})
@RequestMapping("/")
public class CentroAdminControlador {
	
	@Autowired
	private ICentroService centroService;
	
	@Autowired
	private IAuxOpcionesService auxopcionesService;
	
	@Autowired
	private IDatosPerfilService datosperfilService;

	@GetMapping("admin/centro/list")
	public String listado(@RequestParam Map<String, Object> params, 
				Model modelo, RedirectAttributes flash) {
		String Pag_actual = "admin/centro/list";
		
		//Mantener los datos de navegacion existentes
		PaginaCriterios criterios;
		if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			if (criterios.getPagActual().equals(Pag_actual)) {
				criterios.setParametros(params);
			} else {
				criterios = new PaginaCriterios(params);
				criterios.setPagActual(Pag_actual);
				criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Denominación", "Pacientes",  "Tipo Centro", "Acciones")));
				criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "denominacion", "pacientes", "tipocentro", "")));
			}
		} else {
			criterios = new PaginaCriterios(params);
			criterios.setPagActual(Pag_actual);
			criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Denominación", "Pacientes",  "Tipo Centro", "Acciones")));
			criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "denominacion", "pacientes", "tipocentro", "")));
		}
		Pageable pageable = PageRequest.of(criterios.getPageNo(), criterios.getPageSize(), Sort.by(Sort.Direction.valueOf(criterios.getOrderBy()),criterios.getSortBy()));
		
		Page<Centro> centros = centroService.findAllWithKeyword(pageable, criterios.getFiltro());
		PagNavegador<Centro> pageSelect = new PagNavegador<>(Pag_actual, centros);

		modelo.addAttribute("titulo", "Gestión de <b>Centros</b>");
		modelo.addAttribute("criterios", criterios);
		modelo.addAttribute("pagina", pageSelect);

		return "centro/list";
	}

	@GetMapping("admin/centro/new")
	public String formulario_new(Model modelo) {
		Centro centro = new Centro();
		List<AuxOpciones> tipocentros = auxopcionesService.findByTipoOrderById("TIPO_CENTRO");
		
		modelo.addAttribute("titulo", "Formulario de <b>Edición de Centro</b>");
		modelo.addAttribute("centro", centro);
		modelo.addAttribute("tipocentros", tipocentros);
		modelo.addAttribute("numdatoscentro", 0);
		return "centro/form";
	}

	@GetMapping("admin/centro/edit/{regId}/")
	public String formulario_edita(@PathVariable("regId") Long Id,
				Model modelo, RedirectAttributes flash) {
		Centro centro = (Centro) centroService.findById(Id);
		if (centro == null) {
			flash.addFlashAttribute("error", "El centro no existe en la base de datos");
			return "redirect:/admin/centro/list";
		}
		List<AuxOpciones> tipocentros = auxopcionesService.findByTipoOrderById("TIPO_CENTRO");
		Long numdatoscentro = datosperfilService.cuentaDatosCentro(Id);

		modelo.addAttribute("titulo", "Formulario de <b>Edición de Centro</b>");
		modelo.addAttribute("centro", centro);
		modelo.addAttribute("tipocentros", tipocentros);
		modelo.addAttribute("numdatoscentro", numdatoscentro);

		return "centro/form";
	}
	
	
	@PostMapping({"admin/centro/save"})
	public String guarda(@Valid @ModelAttribute("centro") Centro centro, BindingResult resultado, 
				Map<String, Object> modelo, RedirectAttributes flash) {
			//, SessionStatus status) {
		if(resultado.hasErrors()) {
			List<AuxOpciones> tipocentros = auxopcionesService.findByTipoOrderById("TIPO_CENTRO");
			Long numdatoscentro = 0L;
			if (centro.getId()!=null) {
				numdatoscentro = datosperfilService.cuentaDatosCentro(centro.getId());
			}
			modelo.put("titulo", "Formulario de <b>Edición de Centro</b>");
			modelo.put("centro", centro);
			modelo.put("tipocentros", tipocentros);
			modelo.put("numdatoscentro", numdatoscentro);
			modelo.put("danger", "La datos no se han almacenado con éxito");
			return "centro/form";
		}
		centroService.save(centro);
		modelo.remove("centro");
		//status.setComplete();
		flash.addFlashAttribute("success", "El centro se ha almacenado con éxito");
		//return "redirect:/admin/centro/edit/"+centro.getId()+"/";
		return "redirect:/admin/centro/list";
	}

	@PostMapping("admin/centro/del")
	public String borra(@RequestParam("regId") Long Id,
				RedirectAttributes flash) {
		if(Id>0) {
			centroService.delete(Id);
			flash.addFlashAttribute("warning", "Se han eliminado los datos asociados al centro.");
			flash.addFlashAttribute("success", "El centro se ha eliminado con éxito.");
		} else {
			flash.addFlashAttribute("error", "El centro no éxiste en la base de datos");
		}
		return "redirect:/admin/centro/list";
	}
	
	
}








