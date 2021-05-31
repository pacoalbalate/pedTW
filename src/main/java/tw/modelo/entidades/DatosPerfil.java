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

/**
 * Clase de modelo de negocio correspondiente con la entidad Perfil 
 *  
 * Cada Prueba (DatosFecha) tendrá asociados varios perfiles de resultados
 * (DatosPerfil) y estos perfiles a su vez estaran compuestos por los 
 * (Datos) concretos de las respuestas a las (Preguntas) creadas por el gestor
 *
 */

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
	 * Constructor vacío
	 */
	public DatosPerfil() {
		// TODO Auto-generated constructor stub
		this.datos = new ArrayList<Dato>();
	}
	
	/**
	 * Devuelve el Identificador
	 * @return id
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
	 * Devuelve la Prueba a la que corresponde el perfil
	 * @return datosfecha
	 */
	public DatosFecha getDatosfecha() {
		return datosfecha;
	}

	/**
	 * Asigna el perfil a una prueba
	 * @param datosfecha 
	 */
	public void setDatosfecha(DatosFecha datosfecha) {
		this.datosfecha = datosfecha;
	}

	/**
	 * Devuelve un list con los datos de resultados 
	 * 			Datos del perfil
	 * @return datos
	 */
	public List<Dato> getDatos() {
		return datos;
	}

	/**
	 * Almacena un list con los datos de resultados
	 * 		Datos del perfil
	 * @param datos t
	 */
	public void setDatos(List<Dato> datos) {
		this.datos = datos;
	}

	/**
	 * Añade uno de los datos del perfil
	 * @param dato 
	 */
	public void addDato(Dato dato) {
		this.datos.add(dato);
	}

	/**
	 * Devuelve el total de positivos del perfil
	 * @return totalpositivos
	 */
	public Long getTotalpositivos() {
		return totalpositivos;
	}

	/**
	 * Establece el total de positivos del perfil
	 * @param totalpositivos 
	 */
	public void setTotalpositivos(Long totalpositivos) {
		this.totalpositivos = totalpositivos;
	}

}
