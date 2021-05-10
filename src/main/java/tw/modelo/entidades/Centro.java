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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Clase de modelo de negocio correspondiente con la entidad Centro
 * 
 *
 */

@Entity
@Table(name = "centros")
public class Centro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@NotBlank
	@Column(nullable = false)
	private String denominacion;
	@NotNull
	//@Pattern(regexp="[0-9]", message="Solo se permiten valores numericos")
	//@PositiveOrZero
	@Min(0)
	@Column(nullable = false)
	private Long pacientes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	private Region region;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipocentro_id")
	private AuxOpciones tipocentro; 

	@OneToMany(mappedBy = "centro", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	//{CascadeType.PERSIST, CascadeType.MERGE}
	private List<DatosFecha> datosfecha;

	//@ElementCollection(fetch = FetchType.LAZY)
	//@CollectionTable(name = "TABLA_COMENTARIOS")
	//private ArrayList comentarios; 

	/**
	 * Constructor
	 */
	public Centro() {
		// TODO Auto-generated constructor stub
		this.datosfecha = new ArrayList<DatosFecha>();
	}

	
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
	 * Nombre del centro
	 * @return denominacion
	 */
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * Nombre del centro
	 * @param denominacion
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	/**
	 * Pacientes del centro
	 * @return pacientes
	 */
	public Long getPacientes() {
		return pacientes;
	}

	/**
	 * Pacientes del centro
	 * @param pacientes
	 */
	public void setPacientes(Long pacientes) {
		this.pacientes = pacientes;
	}

	/**
	 * Región a la que está asociada el centro
	 * @return region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Región a la que está asociada el centro
	 * @param region 
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * Tipo de centro (Hospital/Centro de salud/...)
	 * @return tipocentro
	 */
	public AuxOpciones getTipocentro() {
		return tipocentro;
	}

	/**
	 * Tipo de centro (Hospital/Centro de salud/...)
	 * @param tipocentro
	 */
	public void setTipocentro(AuxOpciones tipocentro) {
		this.tipocentro = tipocentro;
	}

	/**
	 * Añade una prueba (datosfechas) al centro
	 * @param datosfecha 
	 */
	public void addDatoFecha(DatosFecha datosfecha) {
		this.datosfecha.add(datosfecha);
	}


	/**
	 * Devuelve lista de las pruebas del centro 
	 * @return datosfecha
	 */
	public List<DatosFecha> getDatosfecha() {
		return datosfecha;
	}


	/**
	 * añadir lista de pruebas (no usado)
	 * @param datosfecha 
	 */
	public void setDatosfecha(List<DatosFecha> datosfecha) {
		this.datosfecha = datosfecha;
	}


}
