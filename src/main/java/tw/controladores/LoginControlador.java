package tw.controladores;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tw.modelo.entidades.Usuario;
import tw.modelo.servicios.IUsuarioService;



@Controller
public class LoginControlador {

	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	
	@RequestMapping({"/", "index", ""})
	public String paginaIndex() {
		return "index";
	}

	
	@GetMapping("/login")
	public String acceso_desconexion(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Principal principal, Model modelo, RedirectAttributes flash) {

	
		if (principal != null) {
			flash.addFlashAttribute("info", "Usted ya ha iniciado una sesión.");
			return "redirect:/";
		}

		if (error != null) {
			modelo.addAttribute("danger", "La identificación no se ha realizado correctamente.");
			modelo.addAttribute("error_txt", "El usuario o contraseña introducidos son incorrectos.");
		}
		if (logout != null) {
			modelo.addAttribute("success", "Se ha cerrado la sesión.");
		}


		return "login/login";
	}

	
	@RequestMapping({"/login/change"})
	public String solicitud_cambio_clave() {
		return "login/change";
	}

	@PostMapping("/login/change")
	public String cambio_clave(@RequestParam(value = "password_old", required = true) String password_old,
			@RequestParam(value = "password_new1", required = true) String password_new1,
			@RequestParam(value = "password_new2", required = true) String password_new2,
			Model modelo, Principal principal, RedirectAttributes flash) {


		Usuario usuario = null;
		usuario = usuarioService.findByNombreusuario(principal.getName());
		//request.getUserPrincipal()
		if (usuario==null) {
			modelo.addAttribute("danger", "No ha sido posible el cambio de contraseña con éxito");
			modelo.addAttribute("error_ant_txt", "El usuario no existe en la bdd");
			return "login/change";
		}

		if (!passwordEncoder.matches(password_old, usuario.getClave())) {
			modelo.addAttribute("danger", "No ha sido posible el cambio de contraseña con éxito");
			modelo.addAttribute("error_ant_txt", "La contraseña actual no es correcta");
			return "login/change";
		}

		if (!password_new1.equals(password_new2)) {
			modelo.addAttribute("danger", "No ha sido posible el cambio de contraseña con éxito");
			modelo.addAttribute("error_new_txt", "Las contraseñas introducidas no son iguales");
			return "login/change";
		}

		if (password_new1.isBlank() || password_new1.isEmpty() || (password_new1.length()<5) || (password_new1.length()>100) ) {
			modelo.addAttribute("danger", "No ha sido posible el cambio de contraseña con éxito");
			modelo.addAttribute("error_new_txt", "La nueva contraseña tiene que tener entre 5 y 100 caracteres");
			return "login/change";
		}
		
		String clavecodificada = passwordEncoder.encode(password_new1);
		usuario.setClave(clavecodificada);
		usuarioService.save(usuario);
		modelo.addAttribute("warning", "La nueva contraseña no sera efectiva hasta el reinicio de la sesión");
		modelo.addAttribute("success", "La nueva contraseña se ha almacenado con éxito");


		return "index";
	}
	
	
}
