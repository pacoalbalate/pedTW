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
	
	/**
	 * Almacena un usuario en la BD
	 * @param usuario el usuario a almacenar
	 */
	void save(Usuario usuario);

	/** 
	 * Busca un usuario por identificador
	 * @param id Identificador del usuario
	 * @return Usuario o null si no existe
	 */
	Usuario findById(Long id);

	/**
	 * Borra un usuario de la bd
	 * @param id El id del usuario a borrar
	 * 
	 */
	void delete(Long id);

	/**
	 * Devuelve todos los usuarios en un objeto de paginación
	 * @param pageable
	 * @return Page<Usuario>
	 * 
	 */
	Page<Usuario> findAll(Pageable pageable);

	/**
	 * Devuelve todos los usuarios según criterios de filtrado para el listado
	 * en un objeto de paginación
	 * @param pageable
	 * @param keyword Criterios de selección
	 * @return Page<Usuario> 
	 */
	Page<Usuario> findAllWithKeyword(Pageable pageable, String keyword);

	/** 
	 * Busca un usuario por el nombre completo
	 * @param nombreusuario Nombre del usuario a buscar
	 * @return Usuario
	 */
	Usuario findByNombreusuario(String nombreusuario);

	/**
	 * Busca usuarios de un list de centros con criterios de filtrado del listado en las regiones
	 * asignados a una lista de centros determinados
	 * y los devuelve en un opbjeto de paginación
	 * @param pageable
	 * @param keyword Criterios de selección
	 * @param centros List de los centros
	 * @return Page<Usuario>
	 */
	Page<Usuario> findAllWithKeywordIN(Pageable pageable, String keyword, List<Long> centros);
	
}
