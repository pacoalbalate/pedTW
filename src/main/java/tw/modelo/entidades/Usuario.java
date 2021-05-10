package tw.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Clase del modelo de negocio asociada a la entidad Usuario
 * Usuarios de la aplicación
 * 
 *
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min=5, max=50)
	@Column(length = 50, unique = true)
	private String nombreusuario;

	@NotBlank
	@Size(min=5, max=100)
	@Column(length = 100)
	private String clave;

	@NotNull
	private Boolean activo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private List<Rol> roles;

	/**
	 * Identificador
	 * @return  id
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
	 * Nombre
	 * @return nombreusuario
	 */
	public String getNombreusuario() {
		return nombreusuario;
	}

	/**
	 * Nombre
	 * @param nombreusuario 
	 */
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	/**
	 * Password
	 * @return clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Password
	 * @param clave 
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Usuario activo (true)
	 * @return activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Usuaroi activo (true)
	 * @param activo 
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Roles que posee el usuario
	 * @return roles
	 */
	public List<Rol> getRoles() {
		return roles;
	}

	/**
	 * Asigna roles al usuario
	 * @param roles 
	 */
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}


}
