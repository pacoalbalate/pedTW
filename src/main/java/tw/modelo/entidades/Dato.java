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
	 * 
	 */
	public Dato() {
		// TODO Auto-generated constructor stub
	}

	/**
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
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the datosperfil
	 */
	public DatosPerfil getDatosperfil() {
		return datosperfil;
	}

	/**
	 * @param datosperfil the datosperfil to set
	 */
	public void setDatosPerfil(DatosPerfil datosperfil) {
		this.datosperfil = datosperfil;
	}

	/**
	 * @return the Pregunta
	 */
	public Pregunta getPregunta() {
		return pregunta;
	}

	/**
	 * @param Pregunta the Pregunta to set
	 */
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * @return the dato
	 */
	public String getDato() {
		return dato;
	}

	/**
	 * @param dato the dato to set
	 */
	public void setDato(String dato) {
		this.dato = dato;
	}

}
