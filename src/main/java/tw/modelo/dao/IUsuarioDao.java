package tw.modelo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.modelo.entidades.Usuario;




public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	public Usuario findByNombreusuario(String nombreusuario);
	
	@Query ("SELECT u FROM Usuario u WHERE CONCAT(u.nombreusuario) LIKE %?1%") 
	public Page <Usuario> findAllWithKeyword( Pageable pageable,  String  keyword);  

}
