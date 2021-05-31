package tw.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * Clase del modelo de negocio correspondiente a la entidad Pregunta
 * 
 * Se corresponde con las preguntas opcionales a criterio del gestor
 * que se incluyen en cada perfil de resultados
 *
 */


@Entity
@Table(name = "preguntas_perfil")
public class Pregunta implements Serializable {

	private  static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String denominacion;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipopregunta_id")
	private AuxOpciones tipopregunta; 
	
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "perfil_id")
	//private Perfil perfil;
	
	@OneToMany(mappedBy = "pregunta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Dato> datos;
	

	/**
	 * Constructor vacio
	 */
	public Pregunta() {
		// TODO Auto-generated constructor stub
		this.datos = new ArrayList<Dato>();
	}

	/**
	 * Devuelve el Identificador de pregunta
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el Identificador de pregunta
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Devuelve la Pregunta (descripción)
	 * @return denominacion
	 */
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * Establece la Pregunta (descripción)
	 * @param denominacion 
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	/**
	 * Devuelve el Tipo de la pregunta
	 * @return tipopregunta
	 */
	public AuxOpciones getTipopregunta() {
		return tipopregunta;
	}

	/**
	 * Establece el Tipo de la pregunta
	 * @param tipopregunta 
	 */
	public void setTipopregunta(AuxOpciones tipopregunta) {
		this.tipopregunta = tipopregunta;
	}

	/**
	 * Devuelve las Respuestas asociadas a la pregunta
	 * @return datos
	 */
	public List<Dato> getDatos() {
		return datos;
	}

	/**
	 * Establece las Respuestas asociadas a la pregunta
	 * @param datos 
	 */
	public void setDatos(List<Dato> datos) {
		this.datos = datos;
	}

	/**
	 * Añade una Respuesta asociada a la pregunta
	 * @param dato 
	 */
	public void addDato(Dato dato) {
		this.datos.add(dato);
	}


}
