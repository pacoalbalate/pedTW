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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * Clase de modelo de negocio asociada a la Entidad Region
 * 
 * 
 */


@Entity
@Table(name = "regiones")
public class Region implements Serializable {

	private  static final long serialVersionUID = 1L;

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
	private Long habitantes;
	
	@OneToMany(mappedBy = "region", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	//{CascadeType.PERSIST, CascadeType.MERGE}
	private List<Centro> centros;

	/**
	 * constructor
	 */
	public Region() {
		// TODO Auto-generated constructor stub
		this.centros = new ArrayList<Centro>();
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
	 * Nombre de la region
	 * @return denominacion
	 */
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * Nombre de la region
	 * @param denominacion
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	/**
	 * Nunmero de habitantes de la region
	 * @return habitantes
	 */
	public Long getHabitantes() {
		return habitantes;
	}

	/**
	 * Nunmero de habitantes de la region
	 * @param habitantes 
	 */
	public void setHabitantes(Long habitantes) {
		this.habitantes = habitantes;
	}

	/**
	 * Lista de centros asociados a la region
	 * @return centros
	 */
	public List<Centro> getCentros() {
		return centros;
	}

	/**
	 * Lista de centros asociados a la region
	 * @param centros 
	 */
	public void setCentros(List<Centro> centros) {
		this.centros = centros;
	}

	/**
	 * Asocia un centro a la region
	 * @param centro 
	 */
	public void addCentro(Centro centro) {
		this.centros.add(centro);
	}

}
