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
import tw.modelo.entidades.Pregunta;
import tw.modelo.entidades.AuxOpciones;
import tw.modelo.servicios.IAuxOpcionesService;
import tw.modelo.servicios.IDatosPerfilService;
import tw.modelo.servicios.IPreguntaService;

@Controller 
@SessionAttributes({"pregunta", "criterios"})
@RequestMapping("/")
public class PreguntaAdminControlador {
	
	@Autowired
	private IPreguntaService preguntaService;
	
	@Autowired
	private IAuxOpcionesService auxopcionesService;
	
	@Autowired
	private IDatosPerfilService datosperfilService;

	@GetMapping("admin/pregunta/list")
	public String listado(@RequestParam Map<String, Object> params, 
				Model modelo, RedirectAttributes flash) {
		String Pag_actual = "admin/pregunta/list";
		
		//Mantener los datos de navegacion existentes
		PaginaCriterios criterios;
		if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			if (criterios.getPagActual().equals(Pag_actual)) {
				criterios.setParametros(params);
			} else {
				criterios = new PaginaCriterios(params);
				criterios.setPagActual(Pag_actual);
				criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Denominación",  "Tipo pregunta", "Acciones")));
				criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "denominacion",  "tp.opcion", "")));
			}
		} else {
			criterios = new PaginaCriterios(params);
			criterios.setPagActual(Pag_actual);
			criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Denominación", "Tipo pregunta", "Acciones")));
			criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "denominacion", "tp.opcion", "")));
		}
		Pageable pageable = PageRequest.of(criterios.getPageNo(), criterios.getPageSize(), Sort.by(Sort.Direction.valueOf(criterios.getOrderBy()),criterios.getSortBy()));
		
		Page<Pregunta> preguntas = preguntaService.findAllWithKeyword(pageable, criterios.getFiltro());
		PagNavegador<Pregunta> pageSelect = new PagNavegador<>(Pag_actual, preguntas);

		modelo.addAttribute("titulo", "Gestión de <b>Preguntas de Perfil</b>");
		modelo.addAttribute("criterios", criterios);
		modelo.addAttribute("pagina", pageSelect);

		return "pregunta/list";
	}

	@GetMapping("admin/pregunta/new")
	public String formulario_new(Model modelo) {
		Pregunta pregunta = new Pregunta();
		List<AuxOpciones> tipopreguntas = auxopcionesService.findByTipoOrderById("TIPO_PREGUNTA");
		
		modelo.addAttribute("titulo", "Formulario de <b>Edición de Pregunta</b>");
		modelo.addAttribute("pregunta", pregunta);
		modelo.addAttribute("tipopreguntas", tipopreguntas);
		modelo.addAttribute("numdatospregunta", 0);
		return "pregunta/form";
	}

	@GetMapping("admin/pregunta/edit/{regId}/")
	public String formulario_edita(@PathVariable("regId") Long Id,
				Model modelo, RedirectAttributes flash) {
		Pregunta pregunta = (Pregunta) preguntaService.findById(Id);
		if (pregunta == null) {
			flash.addFlashAttribute("error", "La pregunta no existe en la base de datos");
			return "redirect:/admin/pregunta/list";
		}
		List<AuxOpciones> tipopreguntas = auxopcionesService.findByTipoOrderById("TIPO_PREGUNTA");
		Long numdatospregunta = datosperfilService.cuentaDatosPregunta(Id);

		modelo.addAttribute("titulo", "Formulario de <b>Edición de Pregunta</b>");
		modelo.addAttribute("pregunta", pregunta);
		modelo.addAttribute("tipopreguntas", tipopreguntas);
		modelo.addAttribute("numdatospregunta", numdatospregunta);

		return "pregunta/form";
	}
	
	
	@PostMapping({"admin/pregunta/save"})
	public String guarda(@Valid @ModelAttribute("pregunta") Pregunta pregunta, BindingResult resultado, 
				Map<String, Object> modelo, RedirectAttributes flash) {
			//, SessionStatus status) {
		if(resultado.hasErrors()) {
			List<AuxOpciones> tipopreguntas = auxopcionesService.findByTipoOrderById("TIPO_PREGUNTA");
			Long numdatospregunta = 0L;
			if (pregunta.getId()!=null) {
				numdatospregunta = datosperfilService.cuentaDatosPregunta(pregunta.getId());
			}
			modelo.put("titulo", "Formulario de <b>Edición de Pregunta</b>");
			modelo.put("pregunta", pregunta);
			modelo.put("tipopreguntas", tipopreguntas);
			modelo.put("numdatospregunta", numdatospregunta);
			modelo.put("danger", "La datos no se han almacenado con éxito");
			return "pregunta/form";
		}
		preguntaService.save(pregunta);
		modelo.remove("pregunta");
		//status.setComplete();
		flash.addFlashAttribute("success", "La pregunta se ha almacenado con éxito");
		//return "redirect:/admin/pregunta/edit/"+pregunta.getId()+"/";
		return "redirect:/admin/pregunta/list";
	}

	@PostMapping("admin/pregunta/del")
	public String borra(@RequestParam("regId") Long Id,
				RedirectAttributes flash) {
		if(Id>0) {
			preguntaService.delete(Id);
			flash.addFlashAttribute("warning", "Se han eliminado los datos asociados a la pregunta del perfil.");
			flash.addFlashAttribute("success", "La pregunta se ha eliminado con éxito.");
		} else {
			flash.addFlashAttribute("error", "La pregunta no éxiste en la base de datos");
		}
		return "redirect:/admin/pregunta/list";
	}
	
}








