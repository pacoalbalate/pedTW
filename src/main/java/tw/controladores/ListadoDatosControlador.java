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
 * Clase controladora. Encargada de generar los listados de perfiles, 
 * exportación, consulta de datos y graficas
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
	 * Busca el centro o región para el que el usuario tiene permisos
	 * 
	 * @return página de la vista con los datos a los que tiene acceso
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
	
	/** 
	 * Construye el listado y las graficas 
	 * con filtrado, paginación y ordenación
	 * 
	 * @param params
	 * @param regId Id de centro o región permitido
	 * @param modelo
	 * @param request
	 * @param flash
	 * @return página de la vista
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

	
		//La extracción de datos del listado Controlada para cada uno de los tres roles existentes
		Page<Object> datosperfil = null;
//		Page<DatosPerfil> datosperfil = null;
		if (request.isUserInRole("ROLE_CENTRO")) { 
			datosperfil = datosperfilService.findByIdInWithKeywordDistint(pageable, criterios.getFiltroIdsRegion(), criterios.getFiltro(), regionesIn, criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
		} else if (request.isUserInRole("ROLE_REGION")) {
			datosperfil = datosperfilService.findByIdInWithKeywordDistint(pageable, regionesIn, criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
		} else if (request.isUserInRole("ROLE_GESTOR")) {
			datosperfil = datosperfilService.findByIdInWithKeywordDistint(pageable, criterios.getFiltroIdsRegion(), criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
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
		


		//La extracción de datos de las graficas Controlada para cada uno de los tres roles existentes
		Pageable pageableG = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.valueOf(criterios.getOrderBy()),criterios.getSortBy()));
		Page<Object> datosperfilGrafica = null;
		//Page<DatosPerfil> datosperfil = null;
		if (request.isUserInRole("ROLE_CENTRO")) { 
			datosperfilGrafica = datosperfilService.findByIdInWithKeywordDistint(pageableG, criterios.getFiltroIdsRegion(), criterios.getFiltro(), regionesIn, criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
		} else if (request.isUserInRole("ROLE_REGION")) {
			datosperfilGrafica = datosperfilService.findByIdInWithKeywordDistint(pageableG, regionesIn, criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
		} else if (request.isUserInRole("ROLE_GESTOR")) {
			datosperfilGrafica = datosperfilService.findByIdInWithKeywordDistint(pageableG, criterios.getFiltroIdsRegion(), criterios.getFiltro(), criterios.getFiltroIdsCentro(), criterios.getFiltroIdsDato(), criterios.getFiltroDateDesde(), criterios.getFiltroDateHasta());
		}

		List<Object> cdataListG = datosperfilGrafica.toList();
	    List<DatosPerfil> datosperfil_listG =  new ArrayList<DatosPerfil>();
		for (Object cdata:cdataListG) {
		   Object[] obj= (Object[]) cdata;
		   datosperfil_listG.add((DatosPerfil)obj[0]);
		 }
		
		// Calculo de datos para las graficas
		String tituloGrafica= criterios.getXGrafica();
		List<String> xGrafica = new ArrayList<>(Arrays.asList("Tipo de Prueba", "Fecha", "Tipo de Centro"));
		List<String> xGraficaValor = new ArrayList<>(Arrays.asList("Tipo de Prueba", "Fecha", "Tipo de Centro"));

		if (request.isUserInRole("ROLE_CENTRO")) { 
			tituloGrafica=(tituloGrafica=="")?"Tipo de Prueba":tituloGrafica;
		} else if (request.isUserInRole("ROLE_REGION")) {
			tituloGrafica=(tituloGrafica=="")?"Centro":tituloGrafica;
			xGrafica.add(0, "Centro");
			xGraficaValor.add(0, "Centro");
		} else if (request.isUserInRole("ROLE_GESTOR")) {
			tituloGrafica=(tituloGrafica=="")?"Región":tituloGrafica;
			xGrafica.add(0, "Centro");
			xGraficaValor.add(0, "Centro");
			xGrafica.add(0, "Región");
			xGraficaValor.add(0, "Región");
		}
		criterios.setXGrafica(tituloGrafica);

		List<Pregunta> preguntas = preguntaService.findAll();

		Integer contador = 0;
		for (Pregunta pregunta: preguntas) {
			xGrafica.add(pregunta.getDenominacion());
			xGraficaValor.add(contador.toString());
			contador++;
		}

		String valorGrafica= xGraficaValor.get(xGrafica.indexOf(tituloGrafica));
		String datosGraficaPruebas="";
		String datosGraficaPositivos=""; 

		datosGraficaPruebas=estadisticasService.obtenerDatosGrafica(datosperfil_listG, "pruebas", valorGrafica);
		datosGraficaPositivos=estadisticasService.obtenerDatosGrafica(datosperfil_listG, "positivos", valorGrafica);

		modelo.addAttribute("datosGraficaPruebas", datosGraficaPruebas);
		modelo.addAttribute("datosGraficaPositivos", datosGraficaPositivos);
		modelo.addAttribute("xgrafica", xGrafica);
		modelo.addAttribute("datospor", tituloGrafica);  


		
		List<String> colcampos = new ArrayList<>(Arrays.asList("Id", "Región", "Centro", "Tipo Centro", "Pruebas", "Fecha", "Tipo Prueba", "Positivos Perfil"));	
		//List<String> bddcampos = new ArrayList<>(Arrays.asList("id", "", "", "", "", "", "", "totalpositivos"));
		List<String> bddcampos = new ArrayList<>(Arrays.asList("id", "r.denominacion", "c.denominacion", "tc.opcion", "df.totalpruebas", "df.fecha", "tp.opcion", "totalpositivos"));

		//List<Pregunta> preguntas = preguntaService.findAll();
		//Usamos el mismo que hemos sacado para las graficas 

		for (Pregunta pregunta: preguntas) {
			colcampos.add(pregunta.getDenominacion());
			bddcampos.add("");
		}
		criterios.setNombreColCampos(colcampos);
		criterios.setNombreBddCampos(bddcampos);
		
		
		modelo.addAttribute("titulo", "Listado de <b>Datos</b>");
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
		List<AuxOpciones> datosselfiltro = auxopcionesService.findByTipoContainingOrderById("OPC_");
		modelo.addAttribute("datosselfiltro", datosselfiltro);

		return "listado/list_datos"; 
	}

	/**
	 * Realiza el filtrado por regiones
	 * 
	 * @param regionesId
	 * @param regId Id de centro o región permitido
	 * @param modelo
	 * @param flash
	 * @return página de la vista
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
	 * @param regId Id de centro o región permitido
	 * @param modelo
	 * @param flash
	 * @return página de la vista
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
	 * @param regId Id de centro o región permitido
	 * @param modelo
	 * @param flash
	 * @return página de la vista
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
	 * Realiza el filtrado por los datos seleccionables del perfil 
	 * 
	 * @param datosId
	 * @param regId Id de centro o región permitido
	 * @param modelo
	 * @param flash
	 * @return página de la vista
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
	 * Realiza la selección del eje x del grafico
	 * 
	 * @param datosgrafx
	 * @param regId Id de centro o región permitido
	 * @param modelo
	 * @param flash
	 * @return página de la vista
	 */
	@PostMapping("listado/{regId}/datos/view/grafx")
	public String ver_datos_graficaX(
			@RequestParam(value = "datosgrafx", required = false) String datosgrafx,
			@PathVariable("regId") Long regId,
			Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR", "ROLE_CENTRO")!=regId) {
			return "/login/error_permisos";
		}

		PaginaCriterios criterios = null;
		if ((datosgrafx== null) || (datosgrafx== "")) {
			flash.addFlashAttribute("danger", "El grafico no se puede generar. El dato solicitado no es correcto");
		} else if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			criterios.setXGrafica(datosgrafx);
			flash.addFlashAttribute("info", "Modificado el dato del eje X de los graficos");
		}

		return "redirect:/listado/"+regId+"/datos/view";
	}
	
    
	
    /**
     * Método encargado de generar un fichero para 
     * exportar el listado a CSV y escribirlo
     * 
     * @param response
	 * @param regId Id de centro o región permitido
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
	 * Comprueba la autenticación del usuario
	 * 
	 * @return El indice del centro o región autorizadas en su rol
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


	
	
	
	
	/**
	 * Obtiene los datos para el apartado exclusivo de gráficos en las consultas
	 * 
	 * @param modelo
	 * @param flash
	 * @return página de la vista
	 */
	@GetMapping("admin/grafica/datos")
	public String obtenerGraficas(Model modelo, RedirectAttributes flash) {
		
		String diagramaBarras=estadisticasService.obtenerDiagramaBarrasPorCentroyTotalPositivos();
		String diagramaSectores=estadisticasService.obtenerDiagramaSectoresPorRegionyCentros();
		
		modelo.addAttribute("diagramaBarras", diagramaBarras);
		modelo.addAttribute("diagramaSectores", diagramaSectores);


		return "/grafica/list";
	}

}








