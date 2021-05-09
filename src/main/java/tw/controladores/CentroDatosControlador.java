package tw.controladores;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import tw.modelo.entidades.DatosFecha;
import tw.modelo.entidades.DatosPerfil;
import tw.modelo.entidades.Pregunta;
import tw.modelo.entidades.Rol;
import tw.modelo.entidades.AuxOpciones;
import tw.modelo.entidades.Centro;
import tw.modelo.entidades.Dato;
import tw.modelo.servicios.IAuxOpcionesService;
import tw.modelo.servicios.ICentroService;
import tw.modelo.servicios.IDatosFechaService;
import tw.modelo.servicios.IDatosPerfilService;
import tw.modelo.servicios.IPreguntaService;
import tw.modelo.servicios.IRolService;
/**
 * Clase controladora encargada de llas altas de pruebas y perfiles en 
 * un centro
 */
@Controller 
@SessionAttributes({"datosfecha", "datosperfil", "criterios"})
@RequestMapping("/")
public class CentroDatosControlador {
	
	@Autowired
	private IDatosFechaService datosfechaService;
	
	@Autowired
	private IDatosPerfilService datosperfilService;
	
	@Autowired
	private IPreguntaService preguntaService;
	
	@Autowired
	private ICentroService centroService;

	@Autowired
	private IAuxOpcionesService auxopcionesService;
	
	@Autowired
	private IRolService rolService;

	/** 
	 * Busca el centro asegurando que el usuario tiene 
	 * permisos sobre el mismo
	 * @return
	 */
	@GetMapping("centro/")
	public String busca_centro() {
		Long ctoId = getIdInRole("ROLE_CENTRO");

		if (ctoId<0) {
			return "/login/error_permisos";
		} else {
			return "redirect:/centro/"+ctoId+"/datos/list";
		}
	}

	/**
	 * Presenta los datos ya existentes en el centro
	 * @param ctoId
	 * @param params
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@GetMapping("centro/{ctoId}/datos/list")
	public String listado(@PathVariable("ctoId") Long ctoId, @RequestParam Map<String, Object> params, 
				Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_CENTRO")!=ctoId) {
			return "/login/error_permisos";
		}
		String Pag_actual = "centro/"+ctoId+"/datos/list";
		
		//Mantener los datos de navegacion existentes
		PaginaCriterios criterios;
		if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			if (criterios.getPagActual().equals(Pag_actual)) {
				criterios.setParametros(params);
			} else {
				criterios = new PaginaCriterios(params);
				criterios.setPagActual(Pag_actual);
				criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Fecha",  "Tipo prueba", "Pruebas Totales", "Acciones")));
				criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "fecha",  "tp.opcion", "totalpruebas", "")));
			}
		} else {
			criterios = new PaginaCriterios(params);
			criterios.setPagActual(Pag_actual);
			criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Fecha",  "Tipo prueba", "Pruebas Totales", "Acciones")));
			criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "fecha",  "tp.opcion", "totalpruebas", "")));
		}
		Pageable pageable = PageRequest.of(criterios.getPageNo(), criterios.getPageSize(), Sort.by(Sort.Direction.valueOf(criterios.getOrderBy()),criterios.getSortBy()));
		
		Page<DatosFecha> datosfechas = datosfechaService.findAllWithKeyword(pageable, criterios.getFiltro(), ctoId);
		PagNavegador<DatosFecha> pageSelect = new PagNavegador<>(Pag_actual, datosfechas);

		modelo.addAttribute("titulo", "Gestión de <b>Datos por Fecha</b>");
		modelo.addAttribute("criterios", criterios);
		modelo.addAttribute("pagina", pageSelect);

		return "datos/list";
	}
	
	/**
	 * De de alta nuevo grupo de pruebas 
	 * @param ctoId
	 * @param modelo
	 * @return
	 */
	@GetMapping("centro/{ctoId}/datos/new")
	public String formulario_new(@PathVariable("ctoId") Long ctoId, Model modelo) {

		if (getIdInRole("ROLE_CENTRO")!=ctoId) {
			return "/login/error_permisos";
		}
		DatosFecha datosfecha = new DatosFecha();
		Centro centro = centroService.findById(ctoId);
		datosfecha.setCentro(centro);
		datosfecha.setFecha(new Date());
		List<AuxOpciones> tipopruebas = auxopcionesService.findByTipoOrderById("OPC_TIPO_PRUEBA");
		List<Pregunta> preguntas = preguntaService.findAll();

		modelo.addAttribute("titulo", "Formulario de <b>Edición de DatosFecha</b>");
		modelo.addAttribute("datosfecha", datosfecha);
		modelo.addAttribute("tipopruebas", tipopruebas);
		modelo.addAttribute("preguntas", preguntas);
		modelo.addAttribute("sumapositivos", 0);
		return "datos/form";
	}

	/**
	 * Crea el formulario para la edición o alta 
	 * de perfiles a una prueba existente
	 * 
	 * @param ctoId
	 * @param Id
	 * @param perfilId
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@GetMapping("centro/{ctoId}/datos/edit/{regId}/")
	public String formulario_edita(@PathVariable("ctoId") Long ctoId, @PathVariable("regId") Long Id,
			@RequestParam(value = "perfil", required = false) Long perfilId, Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_CENTRO")!=ctoId) {
			return "/login/error_permisos";
		}
		DatosFecha datosfecha = (DatosFecha) datosfechaService.findById(Id);
		if (datosfecha == null) {
			flash.addFlashAttribute("error", "Los datos no existen en la base de datos");
			return "redirect:/centro/"+ctoId+"/datos/list";
		}
		List<AuxOpciones> tipopruebas = auxopcionesService.findByTipoOrderById("OPC_TIPO_PRUEBA");
		List<AuxOpciones> opcionesdatos = auxopcionesService.findByTipoContainingOrderById("OPC_");
		List<Pregunta> preguntas = preguntaService.findAll();
		Long sumapositivos = datosperfilService.sumaPositivosPerfil(datosfecha.getId());
		if (sumapositivos==null) sumapositivos=0L;

		//Si solicita un perfil lo recuperamos para editarlo, sino creamos uno vacio a añadir
		DatosPerfil datosperfil;
		if ((perfilId!=null) && (perfilId>0)) {
			datosperfil = datosperfilService.findById(perfilId);
		} else {
			datosperfil= new DatosPerfil();
			datosperfil.setDatosfecha(datosfecha);
			datosperfil.setId(0L);
			for (Pregunta pregunta : preguntas) {
				Dato dato = new Dato(datosperfil, pregunta, "");
				if (pregunta.getTipopregunta().getOpcion().equals("Fecha")) {
				    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					dato.setDato(formato.format(new Date()));
				} else if (pregunta.getTipopregunta().getOpcion().equals("Número")) {
					dato.setDato("0");
				}
				datosperfil.addDato(dato);
			}
		}

		modelo.addAttribute("titulo", "Formulario de <b>Edición de Datos por Fecha</b>");
		modelo.addAttribute("datosfecha", datosfecha);
		modelo.addAttribute("tipopruebas", tipopruebas);
		modelo.addAttribute("preguntas", preguntas);
		modelo.addAttribute("datosperfil", datosperfil);
		modelo.addAttribute("opcionesdatos", opcionesdatos);
		modelo.addAttribute("sumapositivos", sumapositivos);
		
		return "datos/form";
	}
	
	/**
	 * Grabación de la prueba editada o creada
	 * 
	 * @param datosfecha
	 * @param resultado
	 * @param ctoId
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@PostMapping({"centro/{ctoId}/datos/save"})
	public String guarda(@Valid @ModelAttribute("datosfecha") DatosFecha datosfecha, BindingResult resultado, 
			@PathVariable("ctoId") Long ctoId, Map<String, Object> modelo, RedirectAttributes flash) {
			//, SessionStatus status) {

		if (getIdInRole("ROLE_CENTRO")!=ctoId) {
			return "/login/error_permisos";
		}
		
		Long sumapositivos = datosperfilService.sumaPositivosPerfil(datosfecha.getId());
		if (sumapositivos==null) sumapositivos=0L;
		Long totalpruebas = datosfecha.getTotalpruebas();
		if (totalpruebas==null) totalpruebas=0L;
		if (sumapositivos>totalpruebas) {
			resultado.rejectValue("totalpruebas", "error.user", "La suma de positivos es superior al numero de pruebas realizadas.");
		}
		
		if(resultado.hasErrors()) {
			List<AuxOpciones> tipopruebas = auxopcionesService.findByTipoOrderById("OPC_TIPO_PRUEBA");
			List<AuxOpciones> opcionesdatos = auxopcionesService.findByTipoContainingOrderById("OPC_");
			List<Pregunta> preguntas = preguntaService.findAll();

			modelo.put("titulo", "Formulario de <b>Edición de Datos por Fecha</b>");
			modelo.put("datosfecha", datosfecha);
			modelo.put("tipopruebas", tipopruebas);
			modelo.put("preguntas", preguntas);
			modelo.put("opcionesdatos", opcionesdatos);
			modelo.put("sumapositivos", sumapositivos);
			modelo.put("danger", "La datos no se han almacenado con éxito");
			return "datos/form";
		}
		
		// si existe ya uno con mismos criterios puede hacerse por grupos de analisis o considerar no dejar
		//private AuxOpciones tipoprueba; 
		//private Date fecha;
		//private Centro centro;
		
		datosfechaService.save(datosfecha);
		modelo.remove("datosfecha");
		//status.setComplete();
		flash.addFlashAttribute("success", "La datos se han almacenado con éxito");
		return "redirect:/centro/"+ctoId+"/datos/edit/"+datosfecha.getId()+"/";
		//return "/centro/"+ctoId+"/datos/list"; 
	}

	/**
	 * Grabación del perfil creado o editado
	 * @param datosperfil
	 * @param resultado
	 * @param ctoId
	 * @param regId
	 * @param params
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@PostMapping("centro/{ctoId}/datos/{regId}/perfil/save")
	public String guarda_perfil(@Valid @ModelAttribute("datosperfil") DatosPerfil datosperfil, BindingResult resultado, 
			//@ModelAttribute("datosfecha") DatosFecha datosfecha,
			@PathVariable("ctoId") Long ctoId, @PathVariable("regId") Long regId,
			@RequestParam Map<String, Object> params,
			Map<String, Object> modelo, RedirectAttributes flash) {
			//, SessionStatus status) {

		if (getIdInRole("ROLE_CENTRO")!=ctoId) {
			return "/login/error_permisos";
		}
		DatosFecha datosfecha = (DatosFecha) datosfechaService.findById(regId);
		if (datosfecha == null) {
			flash.addFlashAttribute("error", "Los datos no existen en la base de datos");
			return "redirect:/centro/"+ctoId+"/datos/list";
		}
		List<Pregunta> preguntas = preguntaService.findAll();

		//Modificamos los antiguos datos del perfil con los nuevos valores
		for (Dato dato : datosperfil.getDatos()) {
			dato.setDatosPerfil(null);
			if (params.get(dato.getPregunta().getDenominacion()) != null) {
				dato.setDato((String) params.get(dato.getPregunta().getDenominacion()));
				dato.setDatosPerfil(datosperfil);
			}
			//Validaciones de los datos
			if (dato.getPregunta().getTipopregunta().getOpcion().equals("Número")) {
				if (!isNumeric(dato.getDato())) {
					dato.setDato("0");
				}
			}
		}
		
		Long sumapositivos = datosperfilService.sumaPositivosPerfil(datosfecha.getId());
		if (sumapositivos==null) sumapositivos=0L;
		//Errores de los datos 
		//Error-> mas positivos que pruebas realizadas
		Long totalpositivos = sumapositivos+datosperfil.getTotalpositivos();
		if (totalpositivos>datosfecha.getTotalpruebas()) {
			if (datosperfil.getId()==0) {
				resultado.rejectValue("totalpositivos", "error.user", "La suma de positivos es superior al numero de pruebas realizadas.");
			} else {
				DatosPerfil datosperfilantes = (DatosPerfil) datosperfilService.findById(datosperfil.getId());
				if ((totalpositivos-datosperfilantes.getTotalpositivos())>datosfecha.getTotalpruebas()) {
					resultado.rejectValue("totalpositivos", "error.user", "La suma de positivos es superior al numero de pruebas realizadas.");
				}			
			}
		}
		
		
		if(resultado.hasErrors()) {
			List<AuxOpciones> tipopruebas = auxopcionesService.findByTipoOrderById("OPC_TIPO_PRUEBA");
			List<AuxOpciones> opcionesdatos = auxopcionesService.findByTipoContainingOrderById("OPC_");
			modelo.put("datosperfil", datosperfil);
			modelo.put("datosfecha", datosfecha);
			
			modelo.put("titulo", "Formulario de <b>Edición de Datos por Fecha</b>");
			modelo.put("tipopruebas", tipopruebas);
			modelo.put("preguntas", preguntas);
			modelo.put("opcionesdatos", opcionesdatos);
			modelo.put("sumapositivos", sumapositivos);
			modelo.put("danger", "Los datos no se han almacenado con éxito");
			return "datos/form";
		}

		datosperfilService.save(datosperfil);
		modelo.remove("datosperfil");
		//status.setComplete();
		flash.addFlashAttribute("success", "La datos se han almacenado con éxito");
		return "redirect:/centro/"+ctoId+"/datos/edit/"+regId+"/";
		//return "/centro/"+ctoId+"/datos/list"; 
	}
	
	/**
	 * Borra una prueba con sus perfiles
	 * @param Id
	 * @param ctoId
	 * @param flash
	 * @return
	 */
	@PostMapping("centro/{ctoId}/datos/del")
	public String borra(@RequestParam("regId") Long Id, @PathVariable("ctoId") Long ctoId,
				RedirectAttributes flash) {

		if (getIdInRole("ROLE_CENTRO")!=ctoId) {
			return "/login/error_permisos";
		}
		if(Id>0) {
			datosfechaService.delete(Id);
			flash.addFlashAttribute("success", "Los datos se han eliminado con éxito");
		} else {
			flash.addFlashAttribute("error", "Los datos no éxisten en la base de datos");
		}
		return "redirect:/centro/"+ctoId+"/datos/list";
	}
	
	/**
	 * Borra un perfil 
	 * 
	 * @param ctoId
	 * @param dfId
	 * @param Id
	 * @param flash
	 * @return
	 */
	@PostMapping("centro/{ctoId}/datos/edit/{dfId}/del")
	public String borra_Perfil(@PathVariable("ctoId") Long ctoId, @PathVariable("dfId") Long dfId, @RequestParam("regId") Long Id,
				RedirectAttributes flash) {

		if (getIdInRole("ROLE_CENTRO")!=ctoId) {
			return "/login/error_permisos";
		}
		if(Id>0) {
			datosperfilService.delete(Id);
			flash.addFlashAttribute("success", "El perfil se ha eliminado con éxito");
		} else {
			flash.addFlashAttribute("error", "El perfil no éxiste en la base de datos");
		}
		return "redirect:/centro/"+ctoId+"/datos/edit/"+dfId+"/";
	}
	
	
	/**
	 * validadción de datos numéricos
	 * @param cadena
	 * @return
	 */
    private static boolean isNumeric(String cadena) {
        boolean esNumerico;

        try {
            Integer.parseInt(cadena);
            esNumerico = true;
        } catch (NumberFormatException excepcion) {
        	esNumerico= false;
        }

        return esNumerico;
    }


    
    
    
	/**
	 * Autenticación del usuario en el centro
	 * @return indiceRol
	 */
	public Long getIdInRole(String... permitidos) {
		
		SecurityContext contexto = SecurityContextHolder.getContext();
		if(contexto == null) {
			return -1L;
		}
		
		Authentication auth = contexto.getAuthentication();
		if(auth == null) {
			return -1L;
		}
		
		List<Rol> roles = rolService.findAllByNameUser(auth.getName());
		if(roles.size() < 1) {
			return -1L;
		}
		Long resultado = -1L;
		for (String permitido : permitidos){
			if(roles.get(0).getRol().equals(permitido)) {
				return roles.get(0).getCentro_region();
			}
		}
		return resultado;
	}


    
}








