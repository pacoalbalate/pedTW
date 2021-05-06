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
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "datos_perfiles")
public class DatosPerfil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "datosfecha_id")
	private DatosFecha datosfecha;
	
	@NotNull
	@Min(0)
	@Column(nullable = false)
	private Long totalpositivos;
	
	@OneToMany(mappedBy = "datosperfil", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Dato> datos;

	/**
	 * 
	 */
	public DatosPerfil() {
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
	 * @return the datosfecha
	 */
	public DatosFecha getDatosfecha() {
		return datosfecha;
	}

	/**
	 * @param datosfecha the datosfecha to set
	 */
	public void setDatosfecha(DatosFecha datosfecha) {
		this.datosfecha = datosfecha;
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

	/**
	 * @return the totalpositivos
	 */
	public Long getTotalpositivos() {
		return totalpositivos;
	}

	/**
	 * @param totalpositivos the totalpositivos to set
	 */
	public void setTotalpositivos(Long totalpositivos) {
		this.totalpositivos = totalpositivos;
	}

}
