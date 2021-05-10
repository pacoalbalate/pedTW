package tw.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
/**
 * Clase de modelo de negocio asociado a la entidad ROL
 * Representa los roles de los usuarios 
 *  (CENTRO, REGION, GESTOR)
 *  Cada usuario solo puede tener un rol
 *
 */

@Entity
@Table(name = "roles", uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id", "rol"})})
public class Rol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario; 

	@NotBlank
	@Column(nullable = false, length = 30)
	private String rol;
	
	//@Column(nullable = false, columnDefinition = "bigint default 0")
	private Long centro_region;

	/**
	 * identificador
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Identificador
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Nomnbre del rol
	 * @return rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Nombre del rol
	 * @param rol 
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * Centro o region a la que está asigando el usuario
	 *  (0 si es rol gestor)
	 * @return centro_region
	 */
	public Long getCentro_region() {
		return centro_region;
	}

	/**
	 * Centro o region a la que está asigando el usuario
	 *  (0 si es rol gestor)
	 * @param centro_region 
	 */
	public void setCentro_region(Long centro_region) {
		this.centro_region = centro_region;
	}

	/**
	 * usuario asignado
	 * @return usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Usuario asigando
	 * @param usuario 
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



}
