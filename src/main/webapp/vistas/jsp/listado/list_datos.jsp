<%@ include file="../comunes/includes.jsp"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="../comunes/cabecera.jsp"%> 
<%
String grafDataPru= (String)request.getAttribute("datosGraficaPruebas");
String grafDataPos= (String)request.getAttribute("datosGraficaPositivos");
String grafDataPor= (String)request.getAttribute("datosGraficaPorcentaje");
%>

<script type="text/javascript">
window.onload = function() { 
    var chart = new CanvasJS.Chart("grafPositivosDePruebas", {
	animationEnabled: true,
	theme: "light2", // "light1", "dark1", "dark2"
	title: {
		text: "Positivos de pruebas por ${datospor}."
	},
	subtitles: [{
		text: "",
		fontSize: 16
	}],
	axisY: {
		title: "Cantidad",
		lineColor: "#4F81BC",
		tickColor: "#4F81BC",
		labelFontColor: "#4F81BC",
		scaleBreaks: {
			type: "wavy",
			autoCalculate: true
		}
	},	
	axisY2: {
		title: "Porcentaje",
		suffix: "%",
		gridThickness: 0,
		lineColor: "#C0504E",
		tickColor: "#C0504E",
		labelFontColor: "#C0504E"
	},
	toolTip: {
		shared: true
	},
	
	data: [
	{
		type: "column",
		indexLabel: "{y}",
		name: "Pruebas",
		showInLegend: true,
		dataPoints: <% out.print(grafDataPru);%>
	},
	{
		type: "area",
		name: "Positivos",
		markerBorderColor: "white",
		markerBorderThickness: 2,
		showInLegend: true,		
		indexLabel: "{y}",
		dataPoints: <% out.print(grafDataPos);%>
	}]
}   );
chart.render(); 
creaPorcentaje();

function creaPorcentaje(){
	var dps = [];
	var yValue, yTotal = 0, yPercent = 0;

	for(var i = 0; i < chart.data[0].dataPoints.length; i++) {
		yValue1 = chart.data[0].dataPoints[i].y;
		yValue2 = chart.data[1].dataPoints[i].y;
		yPercent = ((yValue2 * 100) / yValue1);
		dps.push({label: chart.data[0].dataPoints[i].label, y: yPercent });
	}
	chart.addTo("data", {type:"line", name:"Porcentaje", axisYType: "secondary", 
		yValueFormatString: "0.##\"%\"", indexLabel: "{y}", markerBorderColor: "white",
		markerBorderThickness: 2, indexLabelFontColor: "#C24642", dataPoints: dps});
	chart.axisY2[0].set("maximum", 105, false )
}



    var chart2 = new CanvasJS.Chart("grafPruebasYPositivos", {
	animationEnabled: true,
	theme: "light2", // "light1", "dark1", "dark2"
	title: {
		text: "Pruebas y positivos por ${datospor}."
	},
	axisY: {
		title: "Pruebas",
		lineColor: "#4F81BC",
		tickColor: "#4F81BC",
		labelFontColor: "#4F81BC",
		scaleBreaks: {
			type: "wavy",
			autoCalculate: true
		}
	},	
	axisY2: {
		title: "Positivos",
		gridThickness: 0,
		scaleBreaks: {
			type: "wavy",
			autoCalculate: true
		}
	},	
	toolTip: {
		shared: true
	},
	
	data: [
	{
		type: "column",
		indexLabel: "{y}",
		name: "Pruebas",
		showInLegend: true,
		dataPoints: <% out.print(grafDataPru);%>
	},
	{
		type: "column",
		name: "Positivos",
		axisYType: "secondary",
		showInLegend: true,		
		indexLabel: "{y}",
		dataPoints: <% out.print(grafDataPos);%>
	}]
}   );
chart2.render(); 


}
</script>

</head>
<body>

<c:import url="../comunes/menu.jsp"></c:import>


	<div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">

				<jsp:include page='../comunes/barra_titulo.jsp'>
					<jsp:param name="barra_tipo" value="export" />
					<jsp:param name="barra_buscar" value="si" />
				</jsp:include>

<div class="container">
  <button type="button" class="btn ${criterios.filtroIdActivo ? 'btn-danger' : 'btn-info'}" data-toggle="collapse" data-target="#divfiltros">Filtros ${criterios.filtroIdActivo ? 'Activados ' : 'Desactivados '}</button>
  <div id="divfiltros" class="collapse ${criterios.filtroIdActivo ? 'show' : ''}">

				<sec:authorize access="hasRole('ROLE_GESTOR')">  
				<div>
					<table class="table table-striped table-hover ">
						<tbody>
							<form:form action="${pageContext.request.contextPath}/${criterios.pagActual}/filter/region" method="POST"  >

								<tr>
									<th></th>
									<th><label class="col col-form-label">Seleccione las regiones que desea mostrar:</label></th>
									<th><c:if test="${regionesselfiltro.isEmpty()}">
											<div class="alert alert-info my-4">No existen
												regiones...</div>
										</c:if> <select name="regionesfiltrar" id="regionesfiltrar"  class="form-control" multiple="multiple">
											<c:forEach var="cto" items="${regionesselfiltro}">
												<option value="${cto.id}"
													Label="Regi�n ${cto.id} ${cto.denominacion} habitantes ${cto.habitantes}"
													${criterios.getFiltroIdsRegion().contains(cto.id) ? 'selected' : ''} />
											</c:forEach>
									</select></th>
									<th><button class="btn btn-outline-success" type="submit">Seleccionar</button></th>
								</tr>
							</form:form>

						</tbody>
					</table>
				</div>
				</sec:authorize>

				<sec:authorize access="hasAnyRole('ROLE_GESTOR', 'ROLE_REGION')">
				<div>
					<table class="table table-striped table-hover ">
						<tbody>
							<form:form action="${pageContext.request.contextPath}/${criterios.pagActual}/filter/centro" method="POST"  >

								<tr>
									<th></th>
									<th><label class="col col-form-label">Seleccione los centros que desea mostrar:</label></th>
									<th><c:if test="${centrosselfiltro.isEmpty()}">
											<div class="alert alert-info my-4">No existen
												centros...</div>
										</c:if> <select name="centrosfiltrar" id="centrosfiltrar"  class="form-control" multiple="multiple">
											<c:forEach var="cto" items="${centrosselfiltro}">
												<option value="${cto.id}"
													Label="Centro ${cto.id} ${cto.denominacion} pacientes ${cto.pacientes}"
													${criterios.getFiltroIdsCentro().contains(cto.id) ? 'selected' : ''} />
											</c:forEach>
									</select></th>
									<th><button class="btn btn-outline-success" type="submit">Seleccionar</button></th>
								</tr>
							</form:form>

						</tbody>
					</table>
				</div>
				</sec:authorize>

				<div>
					<table class="table table-striped table-hover ">
						<tbody>
							<form:form action="${pageContext.request.contextPath}/${criterios.pagActual}/filter/date" method="POST"  >

								<tr>
									<th></th>
									<th><label class="col col-form-label">Seleccione las fechas entre las que desea mostrar:</label></th>
									<th>Desde: <input type="date"  name="datedesde" id="datedesde" class="form-control dat1" value="${criterios.getFiltroDateDesdeString()}"   />
									</th>
									<th>Hasta: <input type="date" name="datehasta" id="datehasta"  class="form-control dat2" value="${criterios.getFiltroDateHastaString()}"   />
									</th>
									<th>Desactivar: <input type="checkbox" class="form-control habilit"  ${criterios.filtroFechaActivo ? '' : 'checked="checked"'}  />
									</th>
									<th><button class="btn btn-outline-success" type="submit" id="boton-fecha">Seleccionar</button></th>
								</tr>
							</form:form>

						</tbody>
					</table>
				</div>
<script type="text/javascript">
$(".habilit").on('click',function() {
    var $dat1 = $(".dat1");
    var $dat2 = $(".dat2");
    var d1 = (new Date).getFullYear()+"-01-01";
    var d2 = (new Date).getFullYear()+"-12-31";

    if ($dat1.val()=='2000-01-01') {
        $dat1.val(d1);
        $dat2.val(d2);
    } else {
        $dat1.val('2000-01-01');
        $dat2.val('2999-12-31');
        $("#boton-fecha").click();
    }
});
</script>

				<div>
					<table class="table table-striped table-hover ">
						<tbody>
							<form:form action="${pageContext.request.contextPath}/${criterios.pagActual}/filter/dato" method="POST"  >

								<tr>
									<th></th>
									<th><label class="col col-form-label">Seleccione los datos de perfil que desea mostrar:</label></th>
									<th><c:if test="${centrosselfiltro.isEmpty()}">
											<div class="alert alert-info my-4">No existen
												datos...</div>
										</c:if> <select name="datosfiltrar" id="datosfiltrar"  class="form-control" multiple="multiple">
											<c:forEach var="cto" items="${datosselfiltro}">
												<option value="${cto.opcion}"
													Label="Dato Perfil: ${cto.opcion}"
													${criterios.getFiltroIdsDato().contains(cto.opcion) ? 'selected' : ''} />
											</c:forEach>
									</select></th>
									<th><button class="btn btn-outline-success" type="submit">Seleccionar</button></th>
								</tr>
							</form:form>

						</tbody>
					</table>
				</div>
  </div>
</div>

<hr>
<div class="container">
  <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#divgraficos">Graficos </button>
  <div id="divgraficos" class="collapse show">


  <div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">

				<div class="table-title">
					<div class="row">
						<div class="col-sm-6">
							<h2>
								Datos Acumulados de la Selecci�n
							</h2>
						</div>
					</div>
				</div>
				<div id="grafPositivosDePruebas" style="height: 370px; width: 100%;"></div>
				<br/>
				<div id="grafPruebasYPositivos" style="height: 370px; width: 100%;"></div>
				<br/>
			</div>
		</div>
	</div>

  </div>
</div>

				<table class="table table-striped table-hover table-responsive">
				<c:import url="../comunes/grid/cabeceras_filas.jsp"></c:import>

					<tbody>
						<c:forEach var="reg" items="${datosperfil_list}">
							<tr>
								<td><c:out value="${reg.id}"></c:out></td>
								<td><c:out value="${reg.datosfecha.centro.region.denominacion}"></c:out></td>
								<td><c:out value="${reg.datosfecha.centro.denominacion}"></c:out></td>
								<td><c:out value="${reg.datosfecha.centro.tipocentro.opcion}"></c:out></td>
								<td class="text-right"><fmt:formatNumber type = "number" value="${reg.datosfecha.totalpruebas}" /></td>
								<td><fmt:formatDate type = "date" value="${reg.datosfecha.fecha}" pattern="yyyy-MM-dd"/></td>
								<td><c:out value="${reg.datosfecha.tipoprueba.opcion}"></c:out></td>
								<td class="text-right"><fmt:formatNumber type = "number" value="${reg.totalpositivos}" /></td>
						<c:forEach var="d" items="${reg.datos}">
								<c:if test="${d.pregunta.tipopregunta.opcion =='Texto'}">
									<td><c:out value="${d.dato}"></c:out></td>
								</c:if>
								<c:if test="${d.pregunta.tipopregunta.opcion =='N�mero'}">
									<td class="text-right"><fmt:formatNumber type = "number" value="${d.dato}" /></td>
								</c:if>
								<c:if test="${d.pregunta.tipopregunta.opcion =='Fecha'}">
									<td><fmt:parseDate value="${d.dato}" pattern="yyyy-MM-dd" var="date"/>
									<fmt:formatDate type = "date" value="${date}" pattern="yyyy-MM-dd" /></td>
								</c:if>
								<c:if test="${d.pregunta.tipopregunta.opcionestabla !=''}">
								<% /*	<td><c:out value="${srvaux.findById(d.dato).opcion}"></c:out></td> */ %>
										<td><c:out value="${d.dato}"></c:out></td> 
								</c:if>
						</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
					
				</table>

				<c:import url="../comunes/grid/navegador_paginas.jsp"></c:import>
			</div>
		</div>
	</div>

<%@ include file="../comunes/pie.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/canvas/canvasjs.min.js"></script>
</body>
</html>