package tw.controladores.utilidades.paginas;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.domain.Page;

/**
 * Clase para crear el navegador de las paginas en los listados del sistema
 *
 */
public class PagNavegador<T> {

	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	private int elementosPorPagina;
	private int paginaActual;
	private List<PaginaAccesible> paginas;
	
	/**
	 * Constructor del navegador de paginas
	 * 
	 * @param url del listado 
	 * @param page página actual visible en el listado con sus datos
	 */
	public PagNavegador(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.pageInit(url, page);
	}


	/**
	 * Con los datos de la página actual del listado 
	 * crea la accesibilidd de la/s pagina/s por si no caben todas
	 * en el espacio asignado de la pantalla para dar su acceso en el listado
	 * 
	 * @param url
	 * @param page
	 */
	private void pageInit(String url, Page<T> page) {
		this.elementosPorPagina = page.getSize();
		this.totalPaginas = page.getTotalPages();
		this.paginaActual = page.getNumber() +1;
		this.paginas = new ArrayList<PaginaAccesible>();
		
		int desde, hasta;
		desde = 1;
		hasta = elementosPorPagina;
		
		if(totalPaginas <= elementosPorPagina) {
			// Caben todas las paginas, la ultima es la final
			hasta = totalPaginas;
		} else {
			// No caben todas las paginas
			if(paginaActual<= elementosPorPagina) {
				// Pagina actual inicial
				desde = 1;
			} else if(paginaActual >= (totalPaginas - elementosPorPagina/2)) {
				// Pagina actual final
				desde = totalPaginas - elementosPorPagina +1;
			} else {
				// Pagina intermedia
				desde = paginaActual - elementosPorPagina/2;
			}
		}
		
		for(int i=0; i<hasta; i++) {
			paginas.add(new PaginaAccesible(desde+i, (paginaActual==(desde+i))));
		}
	}

	/**
	 * Devuelve la url del listado
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Devuelve la página actual del listado con sus datos
	 * 
	 * @return page
	 */
	public Page<T> getPage() {
		return page;
	}

	/**
	 * Obtiene el total de paginas que ocupa el listado
	 * 
	 * @return totalPaginas
	 */
	public int getTotalPaginas() {
		return totalPaginas;
	}

	/**
	 * Devuelve el numero de elementos por pagina
	 * 
	 * @return  elementosPorPagina
	 */
	public int getElementosPorPagina() {
		return elementosPorPagina;
	}

	/**
	 * Devuelve el numero de la pagina actual 
	 * 
	 * @return  paginaActual
	 */
	public int getPaginaActual() {
		return paginaActual;
	}

	/**
	 * Devuelve todos los numeros de pagina con su accesibilidad
	 * 
	 * @return  paginas
	 */
	public List<PaginaAccesible> getPaginas() {
		return paginas;
	}

	
	
	
}
