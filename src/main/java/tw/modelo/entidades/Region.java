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
	 * 
	 */
	public Region() {
		// TODO Auto-generated constructor stub
		this.centros = new ArrayList<Centro>();
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
	 * @return the habitantes
	 */
	public Long getHabitantes() {
		return habitantes;
	}

	/**
	 * @param habitantes the habitantes to set
	 */
	public void setHabitantes(Long habitantes) {
		this.habitantes = habitantes;
	}

	/**
	 * @return the centros
	 */
	public List<Centro> getCentros() {
		return centros;
	}

	/**
	 * @param centros the centros to set
	 */
	public void setCentros(List<Centro> centros) {
		this.centros = centros;
	}

	/**
	 * @param centro the centros to set
	 */
	public void addCentro(Centro centro) {
		this.centros.add(centro);
	}

}
