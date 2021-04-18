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
	 * 
	 */
	public Pregunta() {
		// TODO Auto-generated constructor stub
		this.datos = new ArrayList<Dato>();
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
	 * @return the denominacion
	 */
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * @param denominacion the denominacion to set
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	/**
	 * @return the tipopregunta
	 */
	public AuxOpciones getTipopregunta() {
		return tipopregunta;
	}

	/**
	 * @param tipopregunta the pregunta to set
	 */
	public void setTipopregunta(AuxOpciones tipopregunta) {
		this.tipopregunta = tipopregunta;
	}

	/**
	 * @return the datos
	 */
	public List<Dato> getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(List<Dato> datos) {
		this.datos = datos;
	}

	/**
	 * @param dato the datos to set
	 */
	public void addDato(Dato dato) {
		this.datos.add(dato);
	}


}
