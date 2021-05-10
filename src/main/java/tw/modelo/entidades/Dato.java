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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * Clase de modelo de negocio correspondiente con la entidad Dato 
 * (Datos concretos de las Preguntas) 
 * Elemento configurable del perfil
 * 
 * Cada perfil contendrá un list con un objeto de esta clase por 
 * cada pregunta que exista 
 * 
 *
 */


@Entity
@Table(name = "datos")
public class Dato implements Serializable {

	private  static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "datosperfil_id")
	private DatosPerfil datosperfil; 
	
	@NotNull 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pregunta_id")
	private Pregunta pregunta; 

	//@NotBlank
	@Column(nullable = false)
	private String dato;

	/**
	 * Constructor vacío
	 */
	public Dato() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor parametrizado
	 * @param datosperfil
	 * @param pregunta
	 * @param dato
	 */
	public Dato(@NotNull DatosPerfil datosperfil, @NotNull Pregunta pregunta, @NotBlank String dato) {
		this.datosperfil = datosperfil;
		this.pregunta = pregunta;
		this.dato = dato;
	}

	/**
	 * Identificador
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
	 * perfil al que corresponde este dato
	 * @return datosperfil
	 */
	public DatosPerfil getDatosperfil() {
		return datosperfil;
	}

	/**
	 * perfil al que corresponde este dato
	 * @param datosperfil
	 */
	public void setDatosPerfil(DatosPerfil datosperfil) {
		this.datosperfil = datosperfil;
	}

	/**
	 * Pregunta concreta de este dato
	 * @return Pregunta
	 */
	public Pregunta getPregunta() {
		return pregunta;
	}

	/**
	 * Pregunta concreta de este dato
	 * @param Pregunta
	 */
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * Devuelve el dato almacenado
	 * @return dato
	 */
	public String getDato() {
		return dato;
	}

	/**
	 * Guarda el dato a almacenar
	 * @param dato 
	 */
	public void setDato(String dato) {
		this.dato = dato;
	}

}
