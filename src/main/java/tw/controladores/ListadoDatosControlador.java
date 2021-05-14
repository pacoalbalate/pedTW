package tw.controladores;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import tw.controladores.utilidades.paginas.PagNavegador;
import tw.controladores.utilidades.paginas.PaginaCriterios;
import tw.modelo.entidades.AuxOpciones;
import tw.modelo.entidades.Centro;
import tw.modelo.entidades.Dato;
import tw.modelo.entidades.DatosPerfil;
import tw.modelo.entidades.Pregunta;
import tw.modelo.entidades.Region;
import tw.modelo.entidades.Rol;
import tw.modelo.servicios.IAuxOpcionesService;
import tw.modelo.servicios.ICentroService;
import tw.modelo.servicios.IDatosPerfilService;
import tw.modelo.servicios.IEstadisticasService;
import tw.modelo.servicios.IPreguntaService;
import tw.modelo.servicios.IRegionService;
import tw.modelo.servicios.IRolService;
/**
 * Clase controladora. Encargada de generar los listados de perfiles
 */
@Controller 
@SessionAttributes({"criterios"})
@RequestMapping("/")
public class ListadoDatosControlador {
	
	@Autowired
	private IEstadisticasService estadisticasService;
	
	@Autowired
	//@Qualifier("RegionDaoImplDupl")
	private IRegionService regionService;
	
	@Autowired
	private ICentroService centroService;
	
	@Autowired
	private IAuxOpcionesService auxopcionesService;
	
	@Autowired
	private IDatosPerfilService datosperfilService;
	
	@Autowired
	private IPreguntaService preguntaService;
	
	@Autowired
	private IRolService rolService;

	/**
	 * Busca el centro asegurando que el usuario tiene permisos
	 * @return
	 */
	@GetMapping("listado/")
	public String busca_centro() {
		Long regId = getIdInRole("ROLE_REGION", "ROLE_GESTOR", "ROLE_CENTRO");

		if (regId<0) {
			return "/login/error_permisos";
		} else {
			return "redirect:/listado/"+regId+"/datos/view";
		}
	}
	
	/** Construye el listado y los filtros
	 * 
	 * @param params
	 * @param regId
	 * @param modelo
	 * @param request
	 * @param flash
	 * @return
	 */
	@GetMapping("listado/{regId}/datos/view")
	public String ver_datos(@RequestParam Map<String, Object> params, 
				@PathVariable("regId") Long regId,
				Model modelo, HttpServletRequest request, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR", "ROLE_CENTRO")!=regId) {
			return "/login/error_permisos";
		}

		String Pag_actual = "listado/"+regId+"/datos/view";
		
		//Mantener los datos de navegacion existentes
		PaginaCriterios criterios;
		if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			if (criterios.getPagActual().equals(Pag_actual)) {
				criterios.setParametros(params);
			} else {
				criterios = new PaginaCriterios(params);
				criterios.setPagActual(Pag_actual);
			}
		} else {
			criterios = new PaginaCriterios(params);
			criterios.setPagActual(Pag_actual);
		}
		Pageable pageable = PageRequest.of(criterios.getPageNo(), criterios.getPageSize(), Sort.by(Sort.Direction.valueOf(criterios.getOrderBy()),criterios.getSortBy()));

		//Preparamos filtro por las datos autorizados para el usuario
		List<Long> regionesIn = null;
		if (regId>0)  {
			regionesIn = new ArrayList<Long>();
			regionesIn.add(regId);
			//criterios.setFiltroIdsRegion(regionesIn);
		}

	
		//La extracción de datos Controlada para cada uno de los tres roles existentes
		Page<Object> datosperfil = null;
		List<Object> datosperfilGrafica = null;
//		Page<DatosPerfil> datosperfil = null;
		if (request.isUserInRole("ROLE_CENTRO")) { 
			datosperfil = datosperfilService.findByIdInWithKeywordDistint(pageable, criterios.getFiltroIdsRegion(), criterios.getFiltro(), regionesIn, criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
			datosperfilGrafica=datosperfilService.findByIdInWithKeywordDistint(null, criterios.getFiltroIdsRegion(), criterios.getFiltro(), regionesIn, criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta()).toList();
		} else if (request.isUserInRole("ROLE_REGION")) {
			datosperfil = datosperfilService.findByIdInWithKeywordDistint(pageable, regionesIn, criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
			datosperfilGrafica = datosperfilService.findByIdInWithKeywordDistint(null, regionesIn, criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta()).toList();
		} else if (request.isUserInRole("ROLE_GESTOR")) {
			datosperfil = datosperfilService.findByIdInWithKeywordDistint(pageable, criterios.getFiltroIdsRegion(), criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
			datosperfilGrafica = datosperfilService.findByIdInWithKeywordDistint(null, criterios.getFiltroIdsRegion(), criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta()).toList();

		}
		PagNavegador<Object> pageSelect = new PagNavegador<>(Pag_actual, datosperfil);

		List<Object> cdataList = datosperfil.toList();
		List<DatosPerfil> datosperfil_list = new ArrayList<DatosPerfil>();
		//List<Object[]> datosperfil_list = new ArrayList<Object[]>();
		for (Object cdata:cdataList) {
			
		   Object[] obj= (Object[]) cdata;
		   datosperfil_list.add((DatosPerfil)obj[0]);
		   //datosperfil_list.add(obj);
		   //System.out.println("1****" + obj[1]+"---"+ obj[2]);
		 }
		modelo.addAttribute("datosperfil_list", datosperfil_list);
		
		List<DatosPerfil> datosperfil_list1 = new ArrayList<DatosPerfil>();
		
		for (Object cdata:datosperfilGrafica) {
			
			   Object[] obj= (Object[]) cdata;
			   datosperfil_list1.add((DatosPerfil)obj[0]);
			   //datosperfil_list.add(obj);
			   //System.out.println("1****" + obj[1]+"---"+ obj[2]);
			 }
		if (!criterios.isFiltroIdActivo()) {
			datosperfil_list=datosperfil_list1;
		}
		String diagramaBarras=estadisticasService.obtenerDiagramaBarrasPorRegionYTotalDePruebas(datosperfil_list1);
		modelo.addAttribute("diagramaBarras", diagramaBarras);
		
		List<String> colcampos = new ArrayList<>(Arrays.asList("Id", "Región", "Centro", "Tipo Centro", "Pruebas", "Fecha", "Tipo Prueba", "Positivos Perfil"));	
		//List<String> bddcampos = new ArrayList<>(Arrays.asList("id", "", "", "", "", "", "", "totalpositivos"));
		List<String> bddcampos = new ArrayList<>(Arrays.asList("id", "r.denominacion", "c.denominacion", "tc.opcion", "df.totalpruebas", "df.fecha", "tp.opcion", "totalpositivos"));
		List<Pregunta> preguntas = preguntaService.findAll();
		for (Pregunta pregunta: preguntas) {
			colcampos.add(pregunta.getDenominacion());
			bddcampos.add("");
		}
		criterios.setNombreColCampos(colcampos);
		criterios.setNombreBddCampos(bddcampos);
		
		modelo.addAttribute("titulo", "Gestión de <b>Datos por Regiones</b>");
		modelo.addAttribute("criterios", criterios);
		modelo.addAttribute("pagina", pageSelect);
		//modelo.addAttribute("srvaux", auxopcionesService);
		modelo.addAttribute("regId", regId);

		//Mostramos solo los centros con datos y a los que tiene acceso
		List<Region> regionesselfiltro = null;
		if (request.isUserInRole("ROLE_GESTOR")) { 
			regionesselfiltro = regionService.findAllJoinDatos(); 
		}
		modelo.addAttribute("regionesselfiltro", regionesselfiltro);
		List<Centro> centrosselfiltro = null; 
		if (request.isUserInRole("ROLE_GESTOR")) { 
			centrosselfiltro = centroService.findAllJoinDatos(); 
		} else if (request.isUserInRole("ROLE_REGION")) {
			centrosselfiltro = centroService.findAllJoinDatosInRegionesId(regionesIn); 
		} 
		modelo.addAttribute("centrosselfiltro", centrosselfiltro);
		List<AuxOpciones> datosselfiltro = auxopcionesService.findByTipoNotOrderByIdSinRol("TIPO_PREGUNTA");
		modelo.addAttribute("datosselfiltro", datosselfiltro);

		return "listado/list_datos";
	}

	/**
	 * Realiza el filtrado por región
	 * 
	 * @param regionesId
	 * @param regId
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@PostMapping("listado/{regId}/datos/view/filter/region")
	public String ver_datos_filtraRegiones(@RequestParam(value = "regionesfiltrar", required = false) long[] regionesId,
			@PathVariable("regId") Long regId,
			Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR", "ROLE_CENTRO")!=regId) {
			return "/login/error_permisos";
		}

		List<Long> regionesIn = null;
		if (regionesId!= null)  {
			regionesIn = new ArrayList<Long>();
			for (Long regionId : regionesId) {
				regionesIn.add(regionId);
			}
		}
		
		PaginaCriterios criterios;
		if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			criterios.setFiltroIdsRegion(regionesIn);
		}

		if (regionesId!= null)  {
			flash.addFlashAttribute("warning", "El filtrado de regiones esta activado");
		} else {
			flash.addFlashAttribute("info", "El filtrado de regiones esta desactivado");
		}
		
		return "redirect:/listado/"+regId+"/datos/view";
	}

	/**
	 * Realiza el filtrado por centro
	 * 
	 * @param centrosId
	 * @param regId
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@PostMapping("listado/{regId}/datos/view/filter/centro")
	public String ver_datos_filtraCentros(@RequestParam(value = "centrosfiltrar", required = false) long[] centrosId,
			@PathVariable("regId") Long regId,
			Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR", "ROLE_CENTRO")!=regId) {
			return "/login/error_permisos";
		}
		List<Long> centrosIn = null;
		if (centrosId!= null)  {
			centrosIn = new ArrayList<Long>();
			for (Long centroId : centrosId) {
				centrosIn.add(centroId);
			}
		}
		
		PaginaCriterios criterios;
		if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			criterios.setFiltroIdsCentro(centrosIn);
		}

		if (centrosId!= null)  {
			flash.addFlashAttribute("warning", "El filtrado de centros esta activado");
		} else {
			flash.addFlashAttribute("info", "El filtrado de centros esta desactivado");
		}
		
		return "redirect:/listado/"+regId+"/datos/view";
	}

	/**
	 * Realiza el filtrado por fechas
	 * 
	 * @param datedesde
	 * @param datehasta
	 * @param regId
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@PostMapping("listado/{regId}/datos/view/filter/date")
	public String ver_datos_filtraFecha(
			@RequestParam(value = "datedesde", required = false) String datedesde,
			@RequestParam(value = "datehasta", required = false) String datehasta,
			@PathVariable("regId") Long regId,
			Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR", "ROLE_CENTRO")!=regId) {
			return "/login/error_permisos";
		}

		PaginaCriterios criterios = null;
		if ((datedesde== null) || (datehasta== null) || (datedesde.compareTo(datehasta)>0)) {
			flash.addFlashAttribute("danger", "El filtrado por fechas solicitado no se ha realizado. El intervalo de fechas no es correcto");
		} else if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			if (datedesde!= null) criterios.setFiltroDateDesde(datedesde);
			if (datehasta!= null) criterios.setFiltroDateHasta(datehasta);
		}
		
		if ((criterios!=null) && (criterios.isFiltroFechaActivo())) {
			flash.addFlashAttribute("warning", "El filtrado de datos por fechas esta activado");
		} else {
			flash.addFlashAttribute("info", "El filtrado de datos por fechas esta desactivado");
		}


		
		return "redirect:/listado/"+regId+"/datos/view";
	}
	
    
	/**
	 * Realiza el filtrado por los datos del perfil 
	 * 
	 * @param datosId
	 * @param regId
	 * @param modelo
	 * @param flash
	 * @return
	 */
	@PostMapping("listado/{regId}/datos/view/filter/dato")
	public String ver_datos_filtraDatos(@RequestParam(value = "datosfiltrar", required = false) String[] datosId,
			@PathVariable("regId") Long regId,
			Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR", "ROLE_CENTRO")!=regId) {
			return "/login/error_permisos";
		}
		List<String> datosIn = null;
		if (datosId!= null)  {
			datosIn = new ArrayList<String>();
			for (String datoId : datosId) {
				datosIn.add(datoId);
			}
		}
		
		PaginaCriterios criterios;
		if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			criterios.setFiltroIdsDato(datosIn);
		}

		if (datosId!= null)  {
			flash.addFlashAttribute("warning", "El filtrado de datos de perfil esta activado");
		} else {
			flash.addFlashAttribute("info", "El filtrado de datos de perfil esta desactivado");
		}
		
		return "redirect:/listado/"+regId+"/datos/view";
	}
	
    /**
     * Método encargado de generar un fichero para 
     * exportar el listado a CSV y escribirlo
     * @param response
     * @param regId
     * @param modelo
     * @param request
     * @param flash
     * @throws IOException
     */
   @GetMapping("listado/{regId}/datos/export/csv")
   public void exportaCSV(HttpServletResponse response,
			@PathVariable("regId") Long regId,
			Model modelo, HttpServletRequest request, RedirectAttributes flash) throws IOException {

	if (getIdInRole("ROLE_REGION", "ROLE_GESTOR", "ROLE_CENTRO")==regId) {

		//Mantener los datos de navegacion existentes
		PaginaCriterios criterios = (PaginaCriterios) modelo.getAttribute("criterios");

		//Preparamos filtro por las datos autorizados para el usuario
		List<Long> regionesIn = null;
		if (regId>0)  {
			regionesIn = new ArrayList<Long>();
			regionesIn.add(regId);
			//criterios.setFiltroIdsRegion(regionesIn);
		}

		Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.valueOf(criterios.getOrderBy()),criterios.getSortBy()));
		//La extracción de datos Controlada para cada uno de los tres roles existentes
		Page<Object> datosperfil = null;
		//Page<DatosPerfil> datosperfil = null;
		if (request.isUserInRole("ROLE_CENTRO")) { 
			datosperfil = datosperfilService.findByIdInWithKeywordDistint(pageable, criterios.getFiltroIdsRegion(), criterios.getFiltro(), regionesIn, criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
		} else if (request.isUserInRole("ROLE_REGION")) {
			datosperfil = datosperfilService.findByIdInWithKeywordDistint(pageable, regionesIn, criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
		} else if (request.isUserInRole("ROLE_GESTOR")) {
			datosperfil = datosperfilService.findByIdInWithKeywordDistint(pageable, criterios.getFiltroIdsRegion(), criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
		}

		List<Object> cdataList = datosperfil.toList();
	    List<DatosPerfil> datosperfil_list =  new ArrayList<DatosPerfil>();
		for (Object cdata:cdataList) {
		   Object[] obj= (Object[]) cdata;
		   datosperfil_list.add((DatosPerfil)obj[0]);
		 }
		
		response.setContentType("text/csv");
	    String headerKey = "Content-Disposition";
	    String headerValue = "attachment; filename=ListadoDatosPerfil.csv";
	    response.setHeader(headerKey, headerValue);
	        
	    ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

	    List<String> colcampos = new ArrayList<>(Arrays.asList("Id", "Región", "Centro", "Tipo Centro", "Pruebas", "Fecha", "Tipo Prueba", "Positivos Perfil"));	
		List<Pregunta> preguntas = preguntaService.findAll();
	    for (Pregunta pregunta: preguntas) {
			colcampos.add(pregunta.getDenominacion()); 
		}

	    String[] resultArray = new String[colcampos.size()];
	    colcampos.toArray(resultArray);
	    csvWriter.writeHeader(resultArray);
	    StringBuilder aux;
	    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		for (DatosPerfil datoperfil: datosperfil_list) {
			aux = new StringBuilder();
			aux.append(datoperfil.getId().toString()+",");
			aux.append('"'+datoperfil.getDatosfecha().getCentro().getRegion().getDenominacion()+'"'+',');
			aux.append('"'+datoperfil.getDatosfecha().getCentro().getDenominacion()+'"'+',');
			aux.append('"'+datoperfil.getDatosfecha().getCentro().getTipocentro().getOpcion() +'"'+',');
			aux.append(datoperfil.getDatosfecha().getTotalpruebas().toString()+",");
			aux.append('"'+ formato.format(datoperfil.getDatosfecha().getFecha()) +'"'+',');
			aux.append('"'+datoperfil.getDatosfecha().getTipoprueba().getOpcion() +'"'+',');
			aux.append(datoperfil.getTotalpositivos().toString()+",");

			for (Dato dato: datoperfil.getDatos()) {
				if (dato.getPregunta().getTipopregunta().getOpcion().equals("Texto")) {
					aux.append('"'+dato.getDato()+'"'+',');
				}
				if (dato.getPregunta().getTipopregunta().getOpcion().equals("Número")) {
					aux.append(dato.getDato()+",");
				}
				if (dato.getPregunta().getTipopregunta().getOpcion().equals("Fecha")) {
					aux.append('"'+ dato.getDato() +'"'+',');
				}
				if (dato.getPregunta().getTipopregunta().getOpcionestabla()!="") {
					aux.append('"'+dato.getDato()+'"'+',');
				}
			}
			
			int max = Math.max(0, aux.length() - 1); 
			aux.setLength(max);
			
	        //csvWriter.write(datoperfil, nameMapping);
			csvWriter.writeComment(aux.toString());
	     }
	    csvWriter.close();
	}

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

	
	
	
//	@GetMapping("listado/{regId}/")
//	public String formulario_ver(@PathVariable("regId") Long Id, Model modelo) {
//	
//		Region region = (Region) regionService.findById(Id);
//
//		modelo.addAttribute("titulo", "Formulario Región");
//		modelo.addAttribute("region", region);
//
//		return "/listado/form";
//	}

	
	/*
	 * @PostMapping({"/segundoPaso"}) public String muestraPagina(HttpServletRequest
	 * request, Model modelo) { String nombre=request.getParameter("nombreCentro");
	 * nombre += " fue el capturado"; String mensaje =
	 * "Podemos pasar lo que quieras";
	 * 
	 * modelo.addAttribute("nombrePasado", nombre);
	 * modelo.addAttribute("mensajePasado", mensaje);
	 * 
	 * return "pagSegunda"; }
	 */
}








