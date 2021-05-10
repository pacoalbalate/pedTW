package tw.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * Clase de modelo de negocio correspondiente con la entidad Prueba 
 * (Datos de las pruebas a una fecha concreta)
 * Contiene perfiles de resultados
 * 
 *
 */


@Entity
@Table(name = "datos_fecha")
public class DatosFecha implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(0)
	@Column(nullable = false)
	private Long totalpruebas;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoprueba_id")
	private AuxOpciones tipoprueba; 
	
	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centro_id")
	private Centro centro;
	
	@OneToMany(mappedBy = "datosfecha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DatosPerfil> datosperfiles;

	/**
	 * Constructor vacío
	 */
	public DatosFecha() {
		// TODO Auto-generated constructor stub
		this.datosperfiles = new ArrayList<DatosPerfil>();
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
	 * Total de pruebas realizadas en la fecha
	 * @return totalpruebas
	 */
	public Long getTotalpruebas() {
		return totalpruebas;
	}

	/**
	 * Total de pruebas realizadas en la fecha
	 * @param totalpruebas
	 */
	public void setTotalpruebas(Long totalpruebas) {
		this.totalpruebas = totalpruebas;
	}

	/**
	 * Tipo de la prueba (Vírica o Antígenos)
	 * @return tipoprueba
	 */
	public AuxOpciones getTipoprueba() {
		return tipoprueba;
	}

	/**
	 * Tipo de la prueba (Vírica o Antígenos)
	 * @param tipoprueba 
	 */
	public void setTipoprueba(AuxOpciones tipoprueba) {
		this.tipoprueba = tipoprueba;
	}

	/**
	 * Fecha en la que se realiza la prueba
	 * @return fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Fecha en la que se realiza la prueba
	 * @param fecha 
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve el list con los perfiles (resultados) que tenga la prueba
	 * @return datosperfiles
	 */
	public List<DatosPerfil> getDatosperfiles() {
		return datosperfiles;
	}

	/**
	 * Establece un list de resultados
	 * (lo lógico es grabarlos de uno en uno)
	 * @param datosperfiles 
	 */
	public void setDatosperfiles(List<DatosPerfil> datosperfiles) {
		this.datosperfiles = datosperfiles;
	}

	/**
	 * Añade un perfil de resultados a la prueba
	 * @param datosperfil 
	 */
	public void addDatosPerfil(DatosPerfil datosperfil) {
		this.datosperfiles.add(datosperfil);
	}

	/**
	 * Centro en el que se realiza la prueba
	 * @return centro
	 */
	public Centro getCentro() {
		return centro;
	}

	/**
	 * Centro en el que se realiza la prueba
	 * @param centro 
	 */
	public void setCentro(Centro centro) {
		this.centro = centro;
	}

}
