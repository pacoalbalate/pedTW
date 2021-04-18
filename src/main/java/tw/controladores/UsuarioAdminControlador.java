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

	@GetMapping("admin/usuario/list")
	public String listado(@RequestParam Map<String, Object> params, 
				Model modelo, RedirectAttributes flash) {
		String Pag_actual = "admin/usuario/list";
		
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
		
		Page<Usuario> usuarios = usuarioService.findAllWithKeyword(pageable, criterios.getFiltro());
		PagNavegador<Usuario> pageSelect = new PagNavegador<>(Pag_actual, usuarios);

		modelo.addAttribute("titulo", "Gestión de <b>Usuarios</b>");
		modelo.addAttribute("criterios", criterios);
		modelo.addAttribute("pagina", pageSelect);

		return "usuario/list";
	}

	@GetMapping("admin/usuario/new")
	public String formulario_new(Model modelo) {
		Usuario usuario = new Usuario();
		usuario.setId(0L);
		usuario.setActivo(true);
		List<AuxOpciones> tiporoles = auxopcionesService.findByTipoOrderById("TIPO_ROL");
		
		modelo.addAttribute("titulo", "Formulario de <b>Edición de Usuario</b>");
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("tiporoles", tiporoles);
		modelo.addAttribute("tipo_rol", " ");
		modelo.addAttribute("ctoreg_rol", 0);

		return "usuario/form";
	}

	@GetMapping("admin/usuario/edit/{regId}/")
	public String formulario_edita(@PathVariable("regId") Long Id,
				Model modelo, RedirectAttributes flash) {
		Usuario usuario = (Usuario) usuarioService.findById(Id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "redirect:/admin/usuario/list";
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
			centros = centroService.findAll();
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

		return "usuario/form";
	}
	
	
	@PostMapping({"admin/usuario/save"})
	public String guarda(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult resultado,
				@RequestParam(value = "tipo_rol") String tipo_rol,  
				Map<String, Object> modelo, RedirectAttributes flash) {
			//, SessionStatus status) {
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
			} else {
				rol.setRol(tipo_rol);
				rol.setCentro_region(ctoreg_rol);
				flash.addFlashAttribute("info", "Modificado el Rol del usuario con éxito");
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
					centros = centroService.findAll();
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
		}
		rolService.save(rol);
		
		modelo.remove("usuario");
		//status.setComplete();
		flash.addFlashAttribute("success", "El usuario se ha almacenado con éxito");
		return "redirect:/admin/usuario/edit/"+usuario.getId()+"/";
		//return "redirect:/admin/usuario/list";
	}


	@PostMapping("admin/usuario/edit/{regId}/asocrol")
	public String asocia_rol(@PathVariable("regId") Long Id,
				@RequestParam(value = "userrol", required = true) String userRol,
				Model modelo, RedirectAttributes flash) {
		Usuario usuario = usuarioService.findById(Id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "redirect:/admin/usuario/list";
		} 
		
		List<Rol> roles = rolService.findAllByNameUser(usuario.getNombreusuario());
		if ((roles!=null) && (roles.size()>0)) {
			Rol rol = roles.get(0);
			rol.setRol(userRol);
			rolService.save(rol);
			flash.addFlashAttribute("success", "Modificado el tipo de Rol con éxito");
		}
		
		return "redirect:/admin/usuario/edit/"+Id+"/";
	} 


	
	@PostMapping("admin/usuario/edit/{regId}/asoc")
	public String asocia_centro(@PathVariable("regId") Long Id,
				@RequestParam(value = "centro", required = true) Long centroId,
				Model modelo, RedirectAttributes flash) {
		Usuario usuario = usuarioService.findById(Id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "redirect:/admin/usuario/list";
		} 
		
		List<Rol> roles = rolService.findAllByNameUser(usuario.getNombreusuario());
		if ((roles!=null) && (roles.size()>0)) {
			Rol rol = roles.get(0);
			rol.setCentro_region(centroId);
			rolService.save(rol);
			flash.addFlashAttribute("success", "Modificados los permisos de acceso del Rol con éxito");
		}
		
		return "redirect:/admin/usuario/edit/"+Id+"/";
	} 


	
	@PostMapping("admin/usuario/del")
	public String borra(@RequestParam("regId") Long Id,
				RedirectAttributes flash) {
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
		return "redirect:/admin/usuario/list";
	}
	
}








