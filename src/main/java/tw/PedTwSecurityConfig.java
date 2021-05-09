package tw;
/**
 * Clase con la configuración de seguridad de la aplicación
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import tw.modelo.servicios.impl.UsuarioServiceImpl;




@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class PedTwSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private UsuarioServiceImpl userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    /**
     * Encriptador de password
     *
     * @return the b encrypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    /**
     * Configura los accesos permitidos en función del rol del usuario
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        		.antMatchers("/admin/**").hasRole("GESTOR")
        		.antMatchers("/region/**").hasRole("REGION")
        		.antMatchers("/centro/**").hasRole("CENTRO")
        		.antMatchers("/usuario/**").hasAnyRole("GESTOR", "REGION")
        		.antMatchers("/listado/**").hasAnyRole("GESTOR", "REGION", "CENTRO")
                .antMatchers("/*", "/index").permitAll()
                .antMatchers("/login/**", "/login").permitAll()
                .antMatchers("/css/**", "/doc/**", "/img/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                
                .and()
                .formLogin()
                	.successHandler(successHandler)
                    .loginPage("/login")
                	.permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/acceso_denegado");
    }

    /**
     * Configuración global.
     *
     * @param build 
     * @throws Exception Excepción de seguridad
     */
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);

	}

    
    
    
    

    
    
    /**
     * Clase para retornar el acceso con éxito
     * 
     */
	
	@Component
    public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    	@Override
    	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    			Authentication authentication) throws IOException, ServletException {
    		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
    		FlashMap flashMap = new FlashMap();
    		flashMap.put("success", "Iniciada sesión del usuario " +authentication.getName()+ " con éxito!");
    		flashMapManager.saveOutputFlashMap(flashMap, request, response);
    		
    		super.onAuthenticationSuccess(request, response, authentication);
    	}
    }

    
    /**
     * Clase para retornar el acceso denegado
     * 	configura pagina de retorno de acceso denegado.
     */
    
    
    @Configuration
    public class PedTwWebMvcConfig implements WebMvcConfigurer {
       public void addViewControllers(ViewControllerRegistry registro) {
        	registro.addViewController("/acceso_denegado").setViewName("/login/error_permisos");
         }
    }   
    

}