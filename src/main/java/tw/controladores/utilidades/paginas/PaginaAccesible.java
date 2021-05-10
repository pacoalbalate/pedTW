
package tw.controladores.utilidades.paginas;

/**
 * Clase de acceso a las páginas del listado. 
 * Para paginar por los listados del sistema
 *
 */
public class PaginaAccesible {
	private int numeroPagina;
	private boolean actual;

	/**
	 * Constructor de una pagina 
	 * @param numeroPagina El numero de la pagina
	 * @param actual (si es la que se esta viendo o no)
	 */
	public PaginaAccesible(int numeroPagina, boolean actual) {
		this.numeroPagina = numeroPagina;
		this.actual = actual;
	}

	/**
	 * Obtiene el numero de la página
	 * 
	 * @return  numeroPagina
	 */
	public int getNumeroPagina() {
		return numeroPagina;
	}

	/**
	 * Numera la pagina
	 * @param numero 
	 */
	public void setNumero(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	/**
	 * Devuelve si la pagina es la que se esta visualizando 
	 * @return  actual
	 */
	public boolean isActual() {
		return actual;
	}

	/**
	 * Hace que la pagina sea la actual (la que se visualiza)
	 * @param actual 
	 */
	public void setActual(boolean actual) {
		this.actual = actual;
	}
	
	

}
