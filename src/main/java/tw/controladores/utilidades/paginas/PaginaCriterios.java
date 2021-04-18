/**
 * 
 */
package tw.controladores.utilidades.paginas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;



/**
 * @author Portatil
 *
 */
public class PaginaCriterios  implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Integer pageInit = 0;
	private static final Integer sizeInit = 10;
	private static final String sortInit = "id";
	private static final String orderInit = "ASC";
	private static final String filterInit = "";
	private static final String filterDateDesde = "2000-01-01";
	private static final String filterDateHasta = "2999-12-31";

	private List<String> nombreColCampos;
	private List<String> nombreBddCampos;
	private List<Long> filtroIdsRegion;
	private List<Long> filtroIdsCentro;
	private List<String> filtroIdsDato;
	private String filtroDateDesde;
	private String filtroDateHasta;
	private Integer pageNo;
	private Integer pageSize;
	private String sortBy;
	private String orderBy;
	private String filtro;
	private String pagActual;

	
	/**
	 * @param parametros
	 */
	public PaginaCriterios() {
		this.pageNo = pageInit;
		this.pageSize = sizeInit;
		this.sortBy = sortInit;
		this.orderBy = orderInit;
		this.filtro = filterInit;
		this.filtroDateDesde = filterDateDesde;
		this.filtroDateHasta = filterDateHasta;
		this.pagActual = "";
	}
	
	/**
	 * @param parametros
	 */
	public PaginaCriterios(Map<String, Object> parametros) {
		this();
		this.setParametros(parametros);
	}


	/**
	 * @return the pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}
	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}
	/**
	 * @return the filtro
	 */
	public String getFiltro() {
		return filtro;
	}

	/**
	 * @return is orderBy is ASC
	 */
	public boolean isAsc() {
		return (this.orderBy.equals("ASC"));
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(Map<String, Object> params) {
		//this.orderBy = "ASC";
		
		if (params.get("pageNo") != null) {
			this.pageNo = (Integer.valueOf(params.get("pageNo").toString()));
		}
		if (params.get("pageSize") != null) {
			this.pageSize = (Integer.valueOf(params.get("pageSize").toString()));
		}
		if (params.get("sortBy") != null) {
			this.pageNo = pageInit;
			this.sortBy = params.get("sortBy").toString();
			this.orderBy = "ASC";
		}
		if (params.get("orderBy") != null) {
			this.orderBy = params.get("orderBy").toString();
		}
		if (params.get("filtro") != null) {
			this.filtro = params.get("filtro").toString();
			if (filtro!="") {
				this.pageNo = pageInit;
			}
		}
		//@RequestParam(defaultValue = "DESC") String orderBy,
	}

	/**
	 * @return the filtroIdsCentro
	 */
	public List<Long> getFiltroIdsCentro() {
		return filtroIdsCentro;
	}

	/**
	 * @param filtroIdsCentro the filtroIdsCentro to set
	 */
	public void setFiltroIdsCentro(List<Long> filtroIdsCentro) {
		this.filtroIdsCentro = filtroIdsCentro;
	}


	/**
	 * @return the filtroIdsRegion
	 */
	public List<Long> getFiltroIdsRegion() {
		return filtroIdsRegion;
	}

	/**
	 * @param filtroIdsRegion the filtroIdsRegion to set
	 */
	public void setFiltroIdsRegion(List<Long> filtroIdsRegion) {
		this.filtroIdsRegion = filtroIdsRegion;
	}

	/**
	 * @return the pagActual
	 */
	public String getPagActual() {
		return pagActual;
	}

	/**
	 * @param pagActual the pagActual to set
	 */
	public void setPagActual(String pagActual) {
		this.pagActual = pagActual;
	}


	/**
	 * @return the nombreColCampos
	 */
	public List<String> getNombreColCampos() {
		return nombreColCampos;
	}


	/**
	 * @param nombreColCampos the nombreColCampos to set
	 */
	public void setNombreColCampos(List<String> nombreColCampos) {
		this.nombreColCampos = nombreColCampos;
	}


	/**
	 * @return the nombreBddCampos
	 */
	public List<String> getNombreBddCampos() {
		return nombreBddCampos;
	}


	/**
	 * @param nombreBddCampos the nombreBddCampos to set
	 */
	public void setNombreBddCampos(List<String> nombreBddCampos) {
		this.nombreBddCampos = nombreBddCampos;
	}


	/**
	 * @param nombreColCampo the nombreColCampos to search
	 * @return the nombreBddCampo
	 */
	public String getNombreBddCampo(String nombreColCampo) {
		return this.nombreBddCampos.get(this.nombreColCampos.indexOf(nombreColCampo));
	}

	/**
	 * @param nombreBddCampo the nombreBddCampos to search
	 * @return the nombreColCampo
	 */
	public String getNombreColCampo(String nombreBddCampo) {
		return this.nombreColCampos.get(this.nombreBddCampos.indexOf(nombreBddCampo));
	}

	
	/**
	 * @return the filtroIdsDato
	 */
	public List<String> getFiltroIdsDato() {
		return filtroIdsDato;
	}


	/**
	 * @param filtroIdsDato the filtroIdsDato to set
	 */
	public void setFiltroIdsDato(List<String> filtroIdsDato) {
		this.filtroIdsDato = filtroIdsDato;
	}

	/**
	 * @return the filtroDateDesdeString
	 *
	 */
	public String getFiltroDateDesdeString() {
		return this.filtroDateDesde;
	}


	/**
	 * @return the filtroDateDesde
	 *
	 */
	public Date getFiltroDateDesde() {
	    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	    Date desde2=null;
		try {
			desde2 = formato.parse(this.filtroDateDesde);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return desde2;
	}

	
	/**
	 * @param filtroDateDesde the filtroDateDesde to set
	 */
	public void setFiltroDateDesde(String filtroDateDesde) {
		this.filtroDateDesde = filtroDateDesde;
	}

	
	/**
	 * @return the filtroDateHastaString
	 *
	 */
	public String getFiltroDateHastaString() {
		return this.filtroDateHasta;
	}

	
	/**
	 * @return the filtroDateHasta
	 *
	 */
	public Date getFiltroDateHasta() {
	    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	    Date hasta2= null;
		try {
			hasta2 = formato.parse(this.filtroDateHasta);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hasta2;
	}

	/**
	 * @param filtroDateHasta the filtroDateHasta to set
	 */
	public void setFiltroDateHasta(String filtroDateHasta) {
		this.filtroDateHasta = filtroDateHasta;
	}
	


	/**
	 * @return the isFiltroFechaIsActive
	 */
	public boolean isFiltroFechaActivo() {

		boolean resultado = false;
		
		if (!(filtroDateDesde.equals(filterDateDesde)) || !(filtroDateHasta.equals(filterDateHasta))) {
			resultado = true;
		} 

		return resultado;
	}
	


	/**
	 * @return the isFiltroIdsIsActive
	 */
	public boolean isFiltroIdActivo() {

		boolean resultado = false;
		
		if ((filtroIdsDato != null) && (filtroIdsDato.size() > 0)) {
			resultado = true;
		} 
		if ((filtroIdsRegion != null) && (filtroIdsRegion.size() > 0)) {
			resultado = true;
		} 
		if ((filtroIdsCentro != null) && (filtroIdsCentro.size() > 0)) {
			resultado = true;
		} 
		if (!(filtroDateDesde.equals(filterDateDesde)) || !(filtroDateHasta.equals(filterDateHasta))) {
			resultado = true;
		} 

		return resultado;
	}
	

}
