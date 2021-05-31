package tw.modelo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import tw.modelo.entidades.Rol;


/**
 * 
 * DAO
 * Interfaz del modelo de datos para la entidad Perfil
 * 
 */


public interface IRolDao extends JpaRepository<Rol, Long>{

	
/**
 * Busca en la BD los roles de un usuario por id del usuario
 * y los devuelve en un List
 * @param id Identificador de usuario
 */

@Query ("SELECT r FROM Rol r JOIN r.usuario u WHERE u.id = ?1") 
public List<Rol> findAllByIdUser(Long id);  

/**
 * Busca en la BD los roles de un usuario por nombre del usuario
 * y los devuelve en un List
 * @param nombreusuario nombre del usuario
 */
@Query ("SELECT r FROM Rol r JOIN r.usuario u WHERE u.nombreusuario = ?1") 
public List<Rol> findAllByNameUser(String nombreusuario);  

}
