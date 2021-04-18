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

@Service("UsuarioServiceImpl")
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;

	
	@Override
	@Transactional
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return usuarioDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAllWithKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return usuarioDao.findAllWithKeyword(pageable, keyword);
	}
	

	@Override
	public Usuario findByNombreusuario(String nombreusuario) {
		// TODO Auto-generated method stub
		return usuarioDao.findByNombreusuario(nombreusuario);
	}

	
	
	
	
	
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
