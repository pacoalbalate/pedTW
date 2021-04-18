package tw.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "aux_opciones")
public class AuxOpciones implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String opcion;

	@NotBlank
	@Column(nullable = false)
	private String tipo;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "varchar(25) default ''")
	private String opcionestabla;

	
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the opcion
	 */
	public String getOpcion() {
		return opcion;
	}

	/**
	 * @param opcion the opcion to set
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	/**
	 * @return the opcionestabla
	 */
	public String getOpcionestabla() {
		return opcionestabla;
	}

	/**
	 * @param opcionestabla the opcionestabla to set
	 */
	public void setOpcionestabla(String opcionestabla) {
		this.opcionestabla = opcionestabla;
	}



}
