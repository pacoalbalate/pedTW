
package tw.controladores.utilidades.paginas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;



/**
 * Control de los filtros en los listados a la hora de presentarlos 
 * por pantalla.
 * También se encarga de los criterios de la ordenación.
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
	private String xGrafica;

	
	/**
	 * constructor con los criterios establecidos por defecto
	 * (sin parametrizar)
	 * 
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
		this.xGrafica = "";
	}
	
	/**
	 * Constructor parametrizado, 
	 * modificando criterios por defecto
	 */
	public PaginaCriterios(Map<String, Object> parametros) {
		this();
		this.setParametros(parametros);
	}


	/**
	 * Devuelve el número de página actual
	 * 
	 * @return 
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * Tamaño de la página
	 * 
	 * @return pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * Devuelve el campo por el que está ordenado el listado
	 * 
	 * @return  sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}
	/**
	 * Devuelve el criterio de ordenación
	 * 
	 * @return orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}
	/**
	 * Devuelve el criterio por el que se filtra
	 * 
	 * @return filtro
	 */
	public String getFiltro() {
		return filtro;
	}
	/**
	 * Devuelve el tipo de dato X de la grafica
	 * 
	 * @return xGrafica
	 */
	public String getXGrafica() {
		return xGrafica;
	}

	/**
	 * Comprueba si criterio de ordenación actual es ascendente
	 * true si es ascendente 
	 * false si es descendente
	 * 
	 * @return orderBy
	 */
	public boolean isAsc() {
		return (this.orderBy.equals("ASC"));
	}

	/**
	 * Modificación conjunta de los criterios de ordenación y filtado 
	 * con los parametros indicados
	 * 
	 * @param mapa con los criterios a modificar
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
	 * Devuelve el filtro por centros activo
	 * 
	 * @return lista de los Id actualmente mostrados
	 */
	public List<Long> getFiltroIdsCentro() {
		return filtroIdsCentro;
	}

	/**
	 * Activa el filtro por centros
	 * 
	 * @param filtroIdsCentro Lista de Ids de los centros
	 */
	public void setFiltroIdsCentro(List<Long> filtroIdsCentro) {
		this.filtroIdsCentro = filtroIdsCentro;
	}


	/**
	 * Devuelve el filtro por Region actualmente activo
	 * 
	 * @return lista de los Id actualmente mostrados
	 */
	public List<Long> getFiltroIdsRegion() {
		return filtroIdsRegion;
	}

	/**
	 * Activa el filtrado por regiones
	 * 
	 * @param filtroIdsRegion Lista de Ids de las regiones
	 */
	public void setFiltroIdsRegion(List<Long> filtroIdsRegion) {
		this.filtroIdsRegion = filtroIdsRegion;
	}

	/**
	 * Devuelve la pagina actual 
	 * 
	 * @return 
	 */
	public String getPagActual() {
		return pagActual;
	}

	/**
	 * Actualiza cual es la pagina a mostrar
	 * 
	 * @param 
	 */
	public void setPagActual(String pagActual) {
		this.pagActual = pagActual;
	}

	/**
	 * Actualiza cual es el tipo de dato X de la grafica
	 * 
	 * @param
	 */
	public void setXGrafica(String xGrafica) {
		this.xGrafica = xGrafica;
	}


	/**
	 * Devuelve los nombres de las columnas del listado
	 * 
	 * @return 
	 */
	public List<String> getNombreColCampos() {
		return nombreColCampos;
	}


	/**
	 * Actualiza los nombres de las columnas del listado
	 * 
	 * @param nombreColCampos 
	 */
	public void setNombreColCampos(List<String> nombreColCampos) {
		this.nombreColCampos = nombreColCampos;
	}


	/**
	 * Devuelve los nombres de campos de BBDD que se están mostrando
	 * 
	 * @return 
	 */
	public List<String> getNombreBddCampos() {
		return nombreBddCampos;
	}


	/**
	 * Actualiza los nombres de los campos de BBDD que se muestran en el listado
	 * 
	 * @param nombreBddCampos 
	 */
	public void setNombreBddCampos(List<String> nombreBddCampos) {
		this.nombreBddCampos = nombreBddCampos;
	}


	/**
	 * Devuelve el nombre de campo de base de datos que 
	 * corresponde a una columna por el nombre de columna
	 * 
	 * @param nombreColCampo 
	 * @return nombreBddCampo
	 */
	public String getNombreBddCampo(String nombreColCampo) {
		return this.nombreBddCampos.get(this.nombreColCampos.indexOf(nombreColCampo));
	}

	/**
	 * Devuelve el nombre de columna del listado que corresponde 
	 * a un campo de la bbdd del listado
	 * 
	 * @param nombreBddCampo 
	 * @return nombreColCampo
	 */
	public String getNombreColCampo(String nombreBddCampo) {
		return this.nombreColCampos.get(this.nombreBddCampos.indexOf(nombreBddCampo));
	}

	
	/**
	 * Devuelve los datos de perfil por los que se ha filtrado
	 * 
	 * @return filtroIdsDato
	 */
	public List<String> getFiltroIdsDato() {
		return filtroIdsDato;
	}


	/**
	 * Activa el filtrado por los diferentes datos del perfil posibles
	 * 
	 * @param filtroIdsDato 
	 */
	public void setFiltroIdsDato(List<String> filtroIdsDato) {
		this.filtroIdsDato = filtroIdsDato;
	}

	/**
	 * Devuelve la fecha desde por la que se filtra
	 * en formato String
	 * 
	 * @return filtroDateDesdeString
	 *
	 */
	public String getFiltroDateDesdeString() {
		return this.filtroDateDesde;
	}


	/**
	 *  Devuelve la fecha desde por la que se filtra
	 * en formato Date
	 * 
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
	 * Activa el filtrado por fecha desde
	 * 
	 * @param filtroDateDesde 
	 */
	public void setFiltroDateDesde(String filtroDateDesde) {
		this.filtroDateDesde = filtroDateDesde;
	}

	
	/**
	 * Devuelve la fecha hasta por la que se filtra
	 * en formato String
	 * 
	 * @return filtroDateHastaString
	 *
	 */
	public String getFiltroDateHastaString() {
		return this.filtroDateHasta;
	}

	
	/**
	 * Devuelve la fecha hasta por la que se filtra
	 * en formato Date
	 * 
	 * @return  filtroDateHasta
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
	 *  Activa el filtrado por fecha hasta
	 *  
	 * @param filtroDateHasta
	 */
	public void setFiltroDateHasta(String filtroDateHasta) {
		this.filtroDateHasta = filtroDateHasta;
	}
	


	/**
	 * Devuelve true si esta activo el filtro por fechas
	 * 
	 * @return isFiltroFechaIsActive
	 */
	public boolean isFiltroFechaActivo() {

		boolean resultado = false;
		
		if (!(filtroDateDesde.equals(filterDateDesde)) || !(filtroDateHasta.equals(filterDateHasta))) {
			resultado = true;
		} 

		return resultado;
	}
	

	
	/**
	 * Devuelve la fecha desde por la que se 
	 * inicia el filtro de fecha en formato String
	 * 
	 * @return inicioDateDesdeString
	 *
	 */
	public String getInicioDateDesdeString() {
		return filterDateDesde;
	}

	
	/**
	 * Devuelve la fecha hasta por la que se 
	 * inicia el filtro de fecha en formato String
	 * 
	 * @return inicioDateHastaString
	 *
	 */
	public String getInicioDateHastaString() {
		return filterDateHasta;
	}


	/**
	 * Devuelve true si el filtrado de los datos está activado por algun criterio
	 * 
	 * @return isFiltroIsActive
	 */
	public boolean isFiltroIsActivo() {

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
