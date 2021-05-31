package tw.modelo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.modelo.entidades.Usuario;

/**
  * DAO
 * Interfaz del modelo de datos para la entidad Usuario
 *
 */


public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	/** 
	 * Busca un usuario por el nombre completo
	 * @param nombreusuario Nombre del usuario a buscar
	 * @return Usuario
	 */
	public Usuario findByNombreusuario(String nombreusuario);
	
	/**
	 * Busca usuarios de un list de centros con criterios de filtrado del listado en las regiones
	 * asignados a una lista de centros determinados
	 * y los devuelve en un opbjeto de paginación
	 * @param pageable
	 * @param keyword Criterios de selección
	 * @param centros List de los centros
	 * @return Page<Usuario>
	 */
	@Query ("SELECT DISTINCT u FROM Usuario u JOIN u.roles r WHERE CONCAT(u.nombreusuario) LIKE %?1% AND r.rol = 'ROLE_CENTRO' AND r.centro_region IN ?2") 
	public Page <Usuario> findAllWithKeywordIN( Pageable pageable,  String  keyword, List<Long> centros);  
	
	/**
	 * Devuelve todos los usuarios según criterios de filtrado para el listado
	 * en un objeto de paginación
	 * @param pageable
	 * @param keyword Criterios de selección
	 * @return Page<Usuario> 
	 */
	@Query ("SELECT u FROM Usuario u WHERE CONCAT(u.nombreusuario) LIKE %?1%") 
	public Page <Usuario> findAllWithKeyword( Pageable pageable,  String  keyword);  

}
