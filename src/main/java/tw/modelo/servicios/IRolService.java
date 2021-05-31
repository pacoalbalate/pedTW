package tw.modelo.servicios;

import java.util.List;


import tw.modelo.entidades.Rol;



/** 
 * Interfaz de servicios de acceso Jquery a ROL
 * 
 * Patrón Façade de acceso a datos
 * 
 * Define los métodos de acceso a la entidad
 *
 */
public interface IRolService {
	
	/**
	 * Guarda el rol de un usuario en la BD
	 * @param rol El rol a guardar
	 * 
	 */
	void save(Rol rol);

	/**
	 * Recupera un Rol por identificador del rol
	 * @param id Identificador del rol
	 * @return Rol
	 */
	Rol findById(Long id);

	/**
	 * Borra de la bd el rol de un usuario
	 * @param id El ide del rol
	 * 
	 */
	void delete(Long id);

	/**
	 * Busca en la BD los roles de un usuario por id del usuario
	 * y los devuelve en un List
	 * @param id Identificador de usuario
	 */
	List<Rol> findAllByIdUser(Long id);

	/**
	 * Busca en la BD los roles de un usuario por nombre del usuario
	 * y los devuelve en un List
	 * @param nombreusuario nombre del usuario
	 */
	List<Rol> findAllByNameUser(String nombreusuario);
	
}
