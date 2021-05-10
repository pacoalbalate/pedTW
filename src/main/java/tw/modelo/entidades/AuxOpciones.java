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

/**
 * Clase del modelo de negocio correspondiente a la entidad Opciones
 * Opciones parametrizables (roles, tramos de edades, tiposde prueba, etc)
 * Clase auxiliar
 *
 */

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
	 * Identificador
	 * @return  id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * v
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Tipo de opción. 
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Tipo de opción
	 * @param tipo 
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Descripcion
	 * @return  opcion
	 */
	public String getOpcion() {
		return opcion;
	}

	/**
	 * Descripcion
	 * @param opcion 
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	/**
	 * Si el tipo es una opcion
	 * @return opcionestabla
	 */
	public String getOpcionestabla() {
		return opcionestabla;
	}

	/**
	 * Si el tipo es una opcion
	 * @param opcionestabla
	 */
	public void setOpcionestabla(String opcionestabla) {
		this.opcionestabla = opcionestabla;
	}



}
