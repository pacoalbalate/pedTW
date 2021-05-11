package tw.modelo.servicios;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.modelo.entidades.Usuario;



/** 
 * Interfaz de servicios de acceso Jquery a Usuarios
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso a la entidad
 *
 */
public interface IUsuarioService {
	
	public Page< Usuario> findAll(Pageable pageable);
	
	public Page <Usuario> findAllWithKeywordIN( Pageable pageable,  String  keyword, List<Long> centros);  
	
	public Page< Usuario> findAllWithKeyword(Pageable pageable, String keyword);
	
	public Usuario findByNombreusuario(String nombreusuario); 

	public Usuario findById(Long id);
	
	public void save(Usuario usuario);

	public void delete(Long id);
	
}
