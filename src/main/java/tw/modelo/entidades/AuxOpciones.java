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
 * Clase del modelo de negocio correspondiente a la entidad de Opciones 
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
	 * Devuelve el Identificador
	 * @return  id
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
	 * Devuelve El Tipo de opción. 
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Establece El Tipo de opción
	 * @param tipo 
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Devuelve La Descripcion
	 * @return  opcion
	 */
	public String getOpcion() {
		return opcion;
	}

	/**
	 * Establece La Descripcion
	 * @param opcion 
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	/**
	 * Devuelve El tipo de sus opciones en la tabla si la opción es compuesta
	 * @return opcionestabla
	 */
	public String getOpcionestabla() {
		return opcionestabla;
	}

	/**
	 * Establece El tipo de sus opciones en la tabla si la opción es compuesta
	 * @param opcionestabla
	 */
	public void setOpcionestabla(String opcionestabla) {
		this.opcionestabla = opcionestabla;
	}



}
