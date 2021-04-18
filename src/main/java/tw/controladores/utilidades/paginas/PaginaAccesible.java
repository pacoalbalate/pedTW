/**
 * 
 */
package tw.controladores.utilidades.paginas;

/**
 * @author Portatil
 *
 */
public class PaginaAccesible {
	private int numeroPagina;
	private boolean actual;

	/**
	 * @param numeroPagina
	 * @param actual
	 */
	public PaginaAccesible(int numeroPagina, boolean actual) {
		this.numeroPagina = numeroPagina;
		this.actual = actual;
	}

	/**
	 * @return the numeroPagina
	 */
	public int getNumeroPagina() {
		return numeroPagina;
	}

	/**
	 * @param numero the numeroPagina to set
	 */
	public void setNumero(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	/**
	 * @return the actual
	 */
	public boolean isActual() {
		return actual;
	}

	/**
	 * @param actual the actual to set
	 */
	public void setActual(boolean actual) {
		this.actual = actual;
	}
	
	

}
