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
	 * Devuelve el Identificador
	 * @return  id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el Identificador
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Devuelve el Nombre
	 * @return nombreusuario
	 */
	public String getNombreusuario() {
		return nombreusuario;
	}

	/**
	 * Establece el Nombre
	 * @param nombreusuario 
	 */
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	/**
	 * Devuelve la Password
	 * @return clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Establece la Password
	 * @param clave 
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Devuelve si es Usuario activo (true)
	 * @return activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Establece si es Usuario activo (true)
	 * @param activo 
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Devuelve los Roles que posee el usuario
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
