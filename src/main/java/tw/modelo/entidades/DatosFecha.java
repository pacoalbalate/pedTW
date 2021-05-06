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
	 * 
	 */
	public DatosFecha() {
		// TODO Auto-generated constructor stub
		this.datosperfiles = new ArrayList<DatosPerfil>();
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
	 * @return the totalpruebas
	 */
	public Long getTotalpruebas() {
		return totalpruebas;
	}

	/**
	 * @param totalpruebas the totalpruebas to set
	 */
	public void setTotalpruebas(Long totalpruebas) {
		this.totalpruebas = totalpruebas;
	}

	/**
	 * @return the tipoprueba
	 */
	public AuxOpciones getTipoprueba() {
		return tipoprueba;
	}

	/**
	 * @param tipoprueba the tipoprueba to set
	 */
	public void setTipoprueba(AuxOpciones tipoprueba) {
		this.tipoprueba = tipoprueba;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the datosperfiles
	 */
	public List<DatosPerfil> getDatosperfiles() {
		return datosperfiles;
	}

	/**
	 * @param datosperfiles the datosperfiles to set
	 */
	public void setDatosperfiles(List<DatosPerfil> datosperfiles) {
		this.datosperfiles = datosperfiles;
	}

	/**
	 * @param datosperfil the Datosperfil to set
	 */
	public void addDatosPerfil(DatosPerfil datosperfil) {
		this.datosperfiles.add(datosperfil);
	}

	/**
	 * @return the centro
	 */
	public Centro getCentro() {
		return centro;
	}

	/**
	 * @param centro the centro to set
	 */
	public void setCentro(Centro centro) {
		this.centro = centro;
	}

}
