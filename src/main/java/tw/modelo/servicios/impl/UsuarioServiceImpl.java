package tw.modelo.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.modelo.dao.IUsuarioDao;
import tw.modelo.entidades.Usuario;
import tw.modelo.entidades.Rol;
import tw.modelo.servicios.IUsuarioService;
/**
 * Implementa el interfaz Façade - Usuarios
 * 
 * Redirige las peticiones a los métodos del DAO
 * 
 */
@Service("UsuarioServiceImpl")
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;

	/**
	 * Almacena un usuario en la BD
	 * @param usuario el usuario a almacenar
	 */
	@Override
	@Transactional
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.save(usuario);
	}

	/** 
	 * Busca un usuario por identificador
	 * @param id Identificador del usuario
	 * @return Usuario o null si no existe
	 */
	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	/**
	 * Borra un usuario de la bd
	 * @param id El id del usuario a borrar
	 * 
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(id);
	}
	
	/**
	 * Devuelve todos los usuarios en un objeto de paginación
	 * @param pageable
	 * @return Page <Usuario>
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return usuarioDao.findAll(pageable);
	}

	/**
	 * Devuelve todos los usuarios según criterios de filtrado para el listado
	 * en un objeto de paginación
	 * @param pageable
	 * @param keyword Criterios de selección
	 * @return Page <Usuario> 
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAllWithKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return usuarioDao.findAllWithKeyword(pageable, keyword);
	}
	

	/** 
	 * Busca un usuario por el nombre completo
	 * @param nombreusuario Nombre del usuario a buscar
	 * @return Usuario
	 */
	@Override
	public Usuario findByNombreusuario(String nombreusuario) {
		// TODO Auto-generated method stub
		return usuarioDao.findByNombreusuario(nombreusuario);
	}


	/**
	 * Busca usuarios de un list de centros con criterios de filtrado del listado en las regiones
	 * asignados a una lista de centros determinados
	 * y los devuelve en un opbjeto de paginación
	 * @param pageable
	 * @param keyword Criterios de selección
	 * @param centros List de los centros
	 * @return Page <Usuario>
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<Usuario> findAllWithKeywordIN(Pageable pageable, String keyword, List<Long> centros) {
		// TODO Auto-generated method stub
		return usuarioDao.findAllWithKeywordIN(pageable, keyword, centros);
	}
	
	
	
	/**
	 * Devuelve los detalles de un usuario (Nombre, password y si está o no activo) 
	 * siempre que el usuario existe y tenga algún rol asignado
	 * Necesario para autenticación de usuarios en un Objeto User
	 * @param username Nombre del usuario
	 * @return Detalles de usuario (Nombre, Password, Activo)	
	 */
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        Usuario usuario = usuarioDao.findByNombreusuario(username);
        
        if(usuario == null) {
        	throw new UsernameNotFoundException("*** Error en login, el usuario " + username + " no existe en el sistema");
        }
        
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        
        for(Rol rol: usuario.getRoles()) {
        	roles.add(new SimpleGrantedAuthority(rol.getRol()));
        }
        
        if(roles.isEmpty()) {
        	throw new UsernameNotFoundException("*** Error en login de usuario '" + username + "' no existe ningun rol asignado");
        }
        
		return new User(usuario.getNombreusuario(), usuario.getClave(), usuario.getActivo(), true, true, true, roles);
	}


}
