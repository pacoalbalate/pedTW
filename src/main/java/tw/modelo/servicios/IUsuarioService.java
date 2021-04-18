/**
 * 
 */
package tw.modelo.servicios;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.modelo.entidades.Usuario;



/** 
 * @author Portatil
 *
 */
public interface IUsuarioService {
	
	public Page< Usuario> findAll(Pageable pageable);
	
	public Page< Usuario> findAllWithKeyword(Pageable pageable, String keyword);
	
	public Usuario findByNombreusuario(String nombreusuario); 

	public Usuario findById(Long id);
	
	public void save(Usuario usuario);

	public void delete(Long id);
	
}
