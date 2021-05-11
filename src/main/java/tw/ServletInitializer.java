package tw;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * Clase que inicializa los servlets
 * Configuración de Spring
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/** 
	 * Necesario para iniciar los servlets con Spring
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PedTwApplication.class);
	}

}
