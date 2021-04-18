package tw.controladores.utilidades.paginas;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.domain.Page;

/**
 * @author Portatil
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
	 * @param url
	 * @param page
	 */
	public PagNavegador(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.pageInit(url, page);
	}


	/**
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the page
	 */
	public Page<T> getPage() {
		return page;
	}

	/**
	 * @return the totalPaginas
	 */
	public int getTotalPaginas() {
		return totalPaginas;
	}

	/**
	 * @return the elementosPorPagina
	 */
	public int getElementosPorPagina() {
		return elementosPorPagina;
	}

	/**
	 * @return the paginaActual
	 */
	public int getPaginaActual() {
		return paginaActual;
	}

	/**
	 * @return the paginas
	 */
	public List<PaginaAccesible> getPaginas() {
		return paginas;
	}

	
	
	
}
