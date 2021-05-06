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
	 * 
	 */
	public Centro() {
		// TODO Auto-generated constructor stub
		this.datosfecha = new ArrayList<DatosFecha>();
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
	 * @return the pacientes
	 */
	public Long getPacientes() {
		return pacientes;
	}

	/**
	 * @param pacientes the pacientes to set
	 */
	public void setPacientes(Long pacientes) {
		this.pacientes = pacientes;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the tipocentro
	 */
	public AuxOpciones getTipocentro() {
		return tipocentro;
	}

	/**
	 * @param tipocentro the tipocentro to set
	 */
	public void setTipocentro(AuxOpciones tipocentro) {
		this.tipocentro = tipocentro;
	}

	/**
	 * @param datosfecha the datosfechas to set
	 */
	public void addDatoFecha(DatosFecha datosfecha) {
		this.datosfecha.add(datosfecha);
	}


	/**
	 * @return the datosfecha
	 */
	public List<DatosFecha> getDatosfecha() {
		return datosfecha;
	}


	/**
	 * @param datosfecha the datosfecha to set
	 */
	public void setDatosfecha(List<DatosFecha> datosfecha) {
		this.datosfecha = datosfecha;
	}


}
