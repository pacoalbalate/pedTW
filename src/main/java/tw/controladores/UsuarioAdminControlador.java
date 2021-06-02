package tw.controladores;


 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import tw.modelo.entidades.Usuario;
import tw.modelo.entidades.AuxOpciones;
import tw.modelo.entidades.Centro;
import tw.modelo.entidades.Region;
import tw.modelo.entidades.Rol;
import tw.modelo.servicios.IAuxOpcionesService;
import tw.modelo.servicios.ICentroService;
import tw.modelo.servicios.IRegionService;
import tw.modelo.servicios.IRolService;
import tw.modelo.servicios.IUsuarioService;
/**
 * Clase del controlador
 * Encargada de la gestión de usuarios por perfiles:
 * 		Gestor --> Todas las Regiones y Centros
 *      Región --> Región->(Varios Centros)
 *      Centro --> Centro
 */
@Controller 
@SessionAttributes({"usuario", "criterios"})
@RequestMapping("/")
public class UsuarioAdminControlador {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ICentroService centroService;
	
	@Autowired
	private IRegionService regionService;
	
	@Autowired
	private IRolService rolService;
	
	@Autowired
	private IAuxOpcionesService auxopcionesService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	/**
	 * Busca el centro o región para el que el usuario tiene permisos
	 * 
	 * @return página de la vista con los datos a los que tiene acceso
	 */
	@GetMapping("usuario/")
	public String busca_centro() {
		Long regId = getIdInRole("ROLE_REGION", "ROLE_GESTOR");

		if (regId<0) {
			return "/login/error_permisos";
		} else {
			return "redirect:/usuario/"+regId+"/admin/list";
		}
	}

	/**
	 * Presenta los usuarios existentes para su mantenimiento y edición
	 * con filtrado, paginación y ordenación
	 * 
	 * @param params
	 * @param regId Id región permitida
	 * @param modelo
	 * @param request
	 * @param flash
	 * @return página de la vista
	 */
	@GetMapping("usuario/{regId}/admin/list")
	public String listado(@RequestParam Map<String, Object> params, 
				@PathVariable("regId") Long regId,
				Model modelo, HttpServletRequest request, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR")!=regId) {
			return "/login/error_permisos";
		}

		String Pag_actual = "usuario/"+regId+"/admin/list";
		
		//Mantener los datos de navegacion existentes
		PaginaCriterios criterios;
		if (modelo.containsAttribute("criterios")) {
			criterios = (PaginaCriterios) modelo.getAttribute("criterios");
			if (criterios.getPagActual().equals(Pag_actual)) {
				criterios.setParametros(params);
			} else {
				criterios = new PaginaCriterios(params);
				criterios.setPagActual(Pag_actual);
				criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Usuario", "Acciones")));
				criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "nombreusuario", "")));
			}
		} else {
			criterios = new PaginaCriterios(params);
			criterios.setPagActual(Pag_actual);
			criterios.setNombreColCampos(new ArrayList<>(Arrays.asList("Id", "Usuario", "Acciones")));
			criterios.setNombreBddCampos(new ArrayList<>(Arrays.asList("id", "nombreusuario", "")));
		}
		Pageable pageable = PageRequest.of(criterios.getPageNo(), criterios.getPageSize(), Sort.by(Sort.Direction.valueOf(criterios.getOrderBy()),criterios.getSortBy()));

		Page<Usuario> usuarios = null;
		if (request.isUserInRole("ROLE_GESTOR")) { 
			usuarios = usuarioService.findAllWithKeyword(pageable, criterios.getFiltro());
		} else if (request.isUserInRole("ROLE_REGION")) {
			List<Long> regionesIn = new ArrayList<Long>();
			regionesIn.add(regId);
			List<Centro> centrosIn = centroService.findAllJoinDatosInRegionesId(regionesIn);
			List<Long> centroIdIn = new ArrayList<Long>();
			for (Centro centro : centrosIn) {
				centroIdIn.add(centro.getId());
			}
			usuarios = usuarioService.findAllWithKeywordIN(pageable, criterios.getFiltro(), centroIdIn);
		}
		PagNavegador<Usuario> pageSelect = new PagNavegador<>(Pag_actual, usuarios);

		modelo.addAttribute("titulo", "Gestión de <b>Usuarios</b>");
		modelo.addAttribute("criterios", criterios);
		modelo.addAttribute("pagina", pageSelect);

		return "usuario/list";
	}

	/** 
	 * Creación de nuevo usuario
	 * 
	 * @param regId Id región permitida
	 * @param modelo
	 * @param flash
	 * @return página de la vista
	 */
	@GetMapping("usuario/{regId}/admin/new")
	public String formulario_new(
		@PathVariable("regId") Long regId,
		Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR")!=regId) {
			return "/login/error_permisos";
		}

		Usuario usuario = new Usuario();
		usuario.setId(0L);
		usuario.setActivo(true);
		List<AuxOpciones> tiporoles = auxopcionesService.findByTipoOrderById("TIPO_ROL");
		
		modelo.addAttribute("titulo", "Formulario de <b>Edición de Usuario</b>");
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("tiporoles", tiporoles);
		modelo.addAttribute("tipo_rol", " ");
		modelo.addAttribute("ctoreg_rol", 0);
		modelo.addAttribute("regId", regId);

		return "usuario/form";
	}

	/**
	 * Modificación de datos de usuario
	 * 
	 * @param regId Id región permitida
	 * @param Id
	 * @param modelo
	 * @param request
	 * @param flash
	 * @return página de la vista
	 */
	@GetMapping("usuario/{regId}/admin/edit/{userId}/")
	public String formulario_edita(
			@PathVariable("regId") Long regId,
			@PathVariable("userId") Long Id,
			Model modelo, HttpServletRequest request, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR")!=regId) {
			return "/login/error_permisos";
		}

		Usuario usuario = (Usuario) usuarioService.findById(Id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "redirect:/usuario/"+regId+"/admin/list";
		}
		
		
		List<Rol> roles = rolService.findAllByIdUser(Id);
		Rol rol = null; Long ctoreg_rol = 0L; String tipo_rol = ""; 
		if (roles.size()>0) {
			rol = roles.get(0);
			//if (rol.getRol().equals(tipo_rol)) {
				ctoreg_rol = rol.getCentro_region();
				tipo_rol = rol.getRol();
			//} else {
				//rol.setRol(tipo_rol);
				//rol.setCentro_region(ctoreg_rol);
				//flash.addFlashAttribute("info", "Modificado el Rol del usuario con éxito");
			//}
		}
		List<Region> regiones= null; 	List<Centro> centros = null;
		String ctoreg_nombre = "";
		if (tipo_rol.equals("ROLE_REGION")) {
			if(ctoreg_rol>0) {
				Region region = regionService.findById(ctoreg_rol);
				ctoreg_nombre = "Región: " + region.getId() + " "+ region.getDenominacion();
			}
			regiones = regionService.findAll();
			
		} else if (tipo_rol.equals("ROLE_CENTRO")) {
			if(ctoreg_rol>0) {
				Centro centro = centroService.findById(ctoreg_rol);
				ctoreg_nombre = "Centro: " + centro.getId() + " "+ centro.getDenominacion();
			}
			if (request.isUserInRole("ROLE_GESTOR")) { 
				centros = centroService.findAll();
			} else if (request.isUserInRole("ROLE_REGION")) {
				List<Long> regionesIn = new ArrayList<Long>();
				regionesIn.add(regId);
				centros = centroService.findAllJoinDatosInRegionesId(regionesIn);
				//COMPROBAMOS QUE EL USUARIO AL QUE ACCEDE LA REGION ES DE SU REGION
				if(ctoreg_rol>0) {
					boolean acceso_nok = true;
					for (Centro centro : centros) {
						if (centro.getId()==ctoreg_rol) acceso_nok = false;
					}
					if (acceso_nok) {
						return "/login/error_permisos";
					}
				}
			}
		}
		List<AuxOpciones> tiporoles = auxopcionesService.findByTipoOrderById("TIPO_ROL");

		modelo.addAttribute("titulo", "Formulario de <b>Edición de Usuario</b>");
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("tiporoles", tiporoles);
		modelo.addAttribute("tipo_rol", tipo_rol);
		modelo.addAttribute("ctoreg_rol", ctoreg_rol);
		modelo.addAttribute("ctoreg_nombre", ctoreg_nombre);
		modelo.addAttribute("centros", centros);
		modelo.addAttribute("regiones", regiones);
		modelo.addAttribute("regId", regId);

		return "usuario/form";
	}
	
	/**
	 * Grabación en BD del usuario creado o editado
	 * 
	 * @param usuario
	 * @param resultado
	 * @param tipo_rol
	 * @param regId Id región permitida
	 * @param modelo
	 * @param request
	 * @param flash
	 * @return página de la vista
	 */
	@PostMapping({"usuario/{regId}/admin/save"})
	public String guarda(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult resultado,
				@RequestParam(value = "tipo_rol") String tipo_rol,  
				@PathVariable("regId") Long regId,
				Map<String, Object> modelo, HttpServletRequest request, RedirectAttributes flash) {
			//, SessionStatus status) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR")!=regId) {
			return "/login/error_permisos";
		}

		Usuario usuario_antes = null;
		usuario_antes = usuarioService.findByNombreusuario(usuario.getNombreusuario());
		if ((usuario.getId()<1) && (usuario_antes!=null)) {
			resultado.rejectValue("nombreusuario", "error.user", "El nombre debe ser unico y ya existe un usuario con este nombre.");
		} else {
			usuario_antes = usuarioService.findById(usuario.getId());;
		}
		
		List<Rol> roles = rolService.findAllByIdUser(usuario.getId());
		Rol rol = null; Long ctoreg_rol = 0L; //String tipo_rol = ""; 
		if (roles.size()>0) {
			rol = roles.get(0);
			if (rol.getRol().equals(tipo_rol)) {
				ctoreg_rol = rol.getCentro_region();
				//tipo_rol = rol.getRol();
			} else {  // ha modificado el tipo de rol del usuario
				rol.setRol(tipo_rol);
				rol.setCentro_region(ctoreg_rol);
				flash.addFlashAttribute("info", "Modificado el Rol del usuario con éxito");
				//Ponemos el rol con el primer centro o region disponible hasta que lo cambie
				List<Region> regiones= null; 	List<Centro> centros = null;
				if (tipo_rol.equals("ROLE_REGION")) {
					regiones = regionService.findAll();
					if (regiones!=null) rol.setCentro_region(regiones.get(0).getId());
				} else if (tipo_rol.equals("ROLE_CENTRO")) {
					if (request.isUserInRole("ROLE_GESTOR")) { 
						centros = centroService.findAll();
						if (centros!=null) rol.setCentro_region(centros.get(0).getId());
					} else if (request.isUserInRole("ROLE_REGION")) {
						//Si es Region ponemos el primero de sus centros para que el usuario le aparezca despues como suyo
						List<Long> regionesIn = new ArrayList<Long>();
						regionesIn.add(regId);
						centros = centroService.findAllJoinDatosInRegionesId(regionesIn);
						if (centros!=null) rol.setCentro_region(centros.get(0).getId());
					}
				}
			}
		}
		if(resultado.hasErrors()) {
			if (usuario.getId()>0) {
				List<Region> regiones= null; 	List<Centro> centros = null;
				String ctoreg_nombre = "";
				if (tipo_rol.equals("ROLE_REGION")) {
					if(ctoreg_rol>0) {
						Region region = regionService.findById(ctoreg_rol);
						ctoreg_nombre = "Región: " + region.getId() + " "+ region.getDenominacion();
					}
					regiones = regionService.findAll();
					
				} else if (tipo_rol.equals("ROLE_CENTRO")) {
					if(ctoreg_rol>0) {
						Centro centro = centroService.findById(ctoreg_rol);
						ctoreg_nombre = "Centro: " + centro.getId() + " "+ centro.getDenominacion();
					}
					if (request.isUserInRole("ROLE_GESTOR")) { 
						centros = centroService.findAll();
					} else if (request.isUserInRole("ROLE_REGION")) {
						List<Long> regionesIn = new ArrayList<Long>();
						regionesIn.add(regId);
						centros = centroService.findAllJoinDatosInRegionesId(regionesIn);
					}
				}
				modelo.put("ctoreg_nombre", ctoreg_nombre);
				modelo.put("centros", centros);
				modelo.put("regiones", regiones);
			}
			List<AuxOpciones> tiporoles = auxopcionesService.findByTipoOrderById("TIPO_ROL");

			modelo.put("titulo", "Formulario de <b>Edición de Usuario</b>");
			modelo.put("usuario", usuario);
			modelo.put("tiporoles", tiporoles);
			modelo.put("tipo_rol", tipo_rol);
			modelo.put("ctoreg_rol", ctoreg_rol);
			modelo.put("regId", regId);
			modelo.put("danger", "La datos no se han almacenado con éxito");
			return "usuario/form";
		}
		

		//Si es usuario es nuevo o ha cambiado la clave encriptamos el password
		if ((usuario.getId()<1) || (!usuario.getClave().equals(usuario_antes.getClave()))) {
			//System.out.println("*************** encriptamos");
			String clavecodificada = passwordEncoder.encode(usuario.getClave());
			usuario.setClave(clavecodificada);
			flash.addFlashAttribute("warning", "Encriptada la nueva clave del usuario con éxito");
		}
		
		usuarioService.save(usuario);
		if (rol==null) {
			usuario = usuarioService.findByNombreusuario(usuario.getNombreusuario());
			rol = new Rol();
			rol.setUsuario(usuario);
			rol.setCentro_region(0L);
			rol.setRol(tipo_rol);
			//Ponemos el rol con el primer centro o region disponible hasta que lo cambie
			List<Region> regiones= null; 	List<Centro> centros = null;
			if (tipo_rol.equals("ROLE_REGION")) {
				regiones = regionService.findAll();
				if (regiones!=null) rol.setCentro_region(regiones.get(0).getId());
			} else if (tipo_rol.equals("ROLE_CENTRO")) {
				if (request.isUserInRole("ROLE_GESTOR")) { 
					centros = centroService.findAll();
					if (centros!=null) rol.setCentro_region(centros.get(0).getId());
				} else if (request.isUserInRole("ROLE_REGION")) {
					//Si es Region ponemos el primero de sus centros para que el usuario le aparezca despues como suyo
					List<Long> regionesIn = new ArrayList<Long>();
					regionesIn.add(regId);
					centros = centroService.findAllJoinDatosInRegionesId(regionesIn);
					if (centros!=null) rol.setCentro_region(centros.get(0).getId());
				}
			}
		}
		rolService.save(rol);
		
		modelo.remove("usuario");
		//status.setComplete();
		flash.addFlashAttribute("success", "El usuario se ha almacenado con éxito");
		return "redirect:/usuario/"+regId+"/admin/edit/"+usuario.getId()+"/";
		//return "redirect:/usuario/{regId}/admin/list";
	}

	/**
	 * Asociación de un rol a un usuario
	 * 
	 * @param userRol
	 * @param regId Id región permitida
	 * @param Id
	 * @param modelo
	 * @param flash
	 * @return página de la vista
	 */
	@PostMapping("usuario/{regId}/admin/edit/{userId}/asocrol")
	public String asocia_rol(
			@RequestParam(value = "userrol", required = true) String userRol,
			@PathVariable("regId") Long regId,
			@PathVariable("userId") Long Id,
			Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR")!=regId) {
			return "/login/error_permisos";
		}

		Usuario usuario = usuarioService.findById(Id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "redirect:/usuario/"+regId+"/admin/list";
		} 
		
		List<Rol> roles = rolService.findAllByNameUser(usuario.getNombreusuario());
		if ((roles!=null) && (roles.size()>0)) {
			Rol rol = roles.get(0);
			rol.setRol(userRol);
			rolService.save(rol);
			flash.addFlashAttribute("success", "Modificado el tipo de Rol con éxito");
		}
		
		return "redirect:/usuario/"+regId+"/admin/edit/"+Id+"/";
	} 


	/**
	 * Asociación de un centro o región en concreto a un usuario de perfil centro o región
	 * 
	 * @param centroId
	 * @param regId Id región permitida
	 * @param Id
	 * @param modelo
	 * @param flash
	 * @return página de la vista
	 */
	@PostMapping("usuario/{regId}/admin/edit/{userId}/asoc")
	public String asocia_centro(
				@RequestParam(value = "centro", required = true) Long centroId,
				@PathVariable("regId") Long regId,
				@PathVariable("userId") Long Id,
				Model modelo, RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR")!=regId) {
			return "/login/error_permisos";
		}

		Usuario usuario = usuarioService.findById(Id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "redirect:/usuario/"+regId+"/admin/list";
		} 
		
		List<Rol> roles = rolService.findAllByNameUser(usuario.getNombreusuario());
		if ((roles!=null) && (roles.size()>0)) {
			Rol rol = roles.get(0);
			rol.setCentro_region(centroId);
			rolService.save(rol);
			flash.addFlashAttribute("success", "Modificados los permisos de acceso del Rol con éxito");
		}
		
		return "redirect:/usuario/"+regId+"/admin/edit/"+Id+"/";
	} 


	/**
	 * Borrado de un usuario
	 * 
	 * @param Id
	 * @param regId
	 * @param flash
	 * @return página de la vista
	 */
	@PostMapping("usuario/{regId}/admin/del")
	public String borra(@RequestParam("regId") Long Id,
				@PathVariable("regId") Long regId,
				RedirectAttributes flash) {

		if (getIdInRole("ROLE_REGION", "ROLE_GESTOR")!=regId) {
			return "/login/error_permisos";
		}

		if(Id>0) {
			Usuario usuariodel = usuarioService.findById(Id);
			List<Rol> roles = usuariodel.getRoles();
			int contadorroles = 0;
			for (Rol rol : roles) {
				rolService.delete(rol.getId());
				contadorroles = contadorroles +1;
			}
			usuarioService.delete(Id);
			flash.addFlashAttribute("info", "Se han eliminado "+ contadorroles +" roles asociados.");
			flash.addFlashAttribute("success", "El usuario se ha eliminado con éxito");
		} else {
			flash.addFlashAttribute("error", "El usuario no éxiste en la base de datos");
		}
		return "redirect:/usuario/"+regId+"/admin/list";
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

	
	
	
}








