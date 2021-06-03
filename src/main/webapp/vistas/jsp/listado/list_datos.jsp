<%@ include file="../comunes/includes.jsp"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="../comunes/cabecera.jsp"%>


<script type="text/javascript">

function copiaPorcentaje(chart, chart2){
	var dps = [];
	var dps2 = [];

	dps  = chart.options.data[2].dataPoints;
	dps2 = chart2.options.data[0].dataPoints;

	for(var i = 0; i < chart.data[0].dataPoints.length; i++) {
		dps2[i] = {label: chart2.data[0].dataPoints[i].label , y: chart.data[2].dataPoints[i].y};
	}
	
		chart2.options.data[0].dataPoints = dps2; 
		chart2.render();
}


function creaPorcentaje(chart){
	var dps = [];
	var yValue, yTotal = 0, yPercent = 0;
	for(var i = 0; i < chart.data[0].dataPoints.length; i++) {
		yValue1 = chart.data[0].dataPoints[i].y;
		yValue2 = chart.data[1].dataPoints[i].y;
		yPercent = Math.round(((yValue2 * 100) / yValue1)*100)/100;
		dps.push({label: chart.data[0].dataPoints[i].label, y: yPercent });
	}
	chart.addTo("data", {type:"line", name:"Porcentaje", axisYType: "secondary", 
		yValueFormatString: "0.##\"%\"", indexLabel: "{y}", markerBorderColor: "white",
		markerBorderThickness: 2, indexLabelFontColor: "#C24642", dataPoints: dps});
	chart.axisY2[0].set("maximum", 105, false )
}


function creaOrden(chart){
	var yValue, yTotal = 0, yPercent = 0;
	
		for(var x = 0; x < chart.data[0].dataPoints.length; x++) {
		for(var i = 0; i < chart.data[0].dataPoints.length-x-1; i++) {
            if(chart.data[0].dataPoints[i].y < chart.data[0].dataPoints[i+1].y){
                var tmp = chart.data[0].dataPoints[i+1].y;
                chart.data[0].dataPoints[i+1].y = chart.data[0].dataPoints[i].y;
                chart.data[0].dataPoints[i].y = tmp;
                var tmp2 = chart.data[0].dataPoints[i+1].label;
                chart.data[0].dataPoints[i+1].label = chart.data[0].dataPoints[i].label;
                chart.data[0].dataPoints[i].label = tmp2;
            }
	
		}
	}
}


function creaPareto(chart){
	var dps = [];
	var yValue, yTotal = 0, yPercent = 0;
	
	for(var i = 0; i < chart.data[0].dataPoints.length; i++)
		yTotal += chart.data[0].dataPoints[i].y;

	for(var i = 0; i < chart.data[0].dataPoints.length; i++) {
		yValue = chart.data[0].dataPoints[i].y;
		yPercent += (yValue / yTotal * 100);
		dps.push({label: chart.data[0].dataPoints[i].label, y: yPercent });
	}
	chart.addTo("data", {type:"line", axisYType: "secondary", yValueFormatString: "0.##\"%\"",
	name:"% Acumulado", markerBorderColor: "white", markerBorderThickness: 2, indexLabel: "{y}", dataPoints: dps});
	chart.axisY[0].set("maximum", yTotal, false);
	chart.axisY2[0].set("maximum", 105, false );
	chart.axisY2[0].set("interval", 25 );
}


function updateChart() {
	var boilerColor, deltaY, yVal;
	var dps = chart.options.data[0].dataPoints;
	for (var i = 0; i < dps.length; i++) {
		deltaY = Math.round(2 + Math.random() *(-2-2));
		yVal = deltaY + dps[i].y > 0 ? dps[i].y + deltaY : 0;
		
		boilerColor = yVal > 200 ? "#FF2500" : yVal >= 170 ? "#FF6000" : yVal < 170 ? "#6B8E23 " : null;
		
		dps[i] = {label: "Boiler "+(i+1) , y: yVal, color: boilerColor};
		
	}
	chart.options.data[0].dataPoints = dps; 
	chart.render();
};

</script>

<script type="text/javascript">

window.onload = function() { 

    var chartPos = new CanvasJS.Chart("grafPositivosDePruebas", {
	exportEnabled: true,
	animationEnabled: true,
	theme: "light2", // "light1", "dark1", "dark2"
	title: {
		text: "Relación de Positivos con pruebas por ${datospor}."
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
		title: "% Positivos en Pruebas",
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
		name: "Num. Pruebas",
		showInLegend: true,
		dataPoints: ${datosGraficaPruebas}
	},
	{
		type: "area",
		name: "Num. Positivos",
		markerBorderColor: "white",
		markerBorderThickness: 2,
		showInLegend: true,		
		indexLabel: "{y}",
		dataPoints: ${datosGraficaPositivos}
	}]
}   );
chartPos.render(); 
creaPorcentaje(chartPos);



    var chart2 = new CanvasJS.Chart("grafPruebasYPositivos", {
	exportEnabled: true,
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
		name: "Num. Pruebas",
		showInLegend: true,
		dataPoints: ${datosGraficaPruebas}
	},
	{
		type: "column",
		name: "Num. Positivos",
		axisYType: "secondary",
		showInLegend: true,		
		indexLabel: "{y}",
		dataPoints: ${datosGraficaPositivos}
	}]
}   );
chart2.render(); 


var chartPPorc2 = new CanvasJS.Chart("grafPiePruebas", {
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "Pruebas por ${datospor}."
	},
	data: [{
		type: "pie",
		startAngle: 25,
		indexLabel: "{label} - #percent%",
		toolTipContent: "<b>{label}:</b> {y} (#percent%)",
		showInLegend: "true",
		legendText: "{label}",
		indexLabelFontSize: 16,
		dataPoints: ${datosGraficaPruebas}
	}]
});
chartPPorc2.render();

var chartPPorc3 = new CanvasJS.Chart("grafPiePositivos", {
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "Positivos por ${datospor}."
	},
	data: [{
		type: "pie",
		startAngle: 25,
		indexLabel: "{label} - #percent%",
		toolTipContent: "<b>{label}:</b> {y} (#percent%)",
		showInLegend: "true",
		legendText: "{label}",
		indexLabelFontSize: 16,
		dataPoints: ${datosGraficaPositivos}
	}]
});
chartPPorc3.render();

var chartPPorc = new CanvasJS.Chart("grafPiePorcentajes", {
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "Porcentaje de Positivos por ${datospor}."
	},
	data: [{
		type: "pie",
		startAngle: 25,
		indexLabel: "{label} - #percent%",
		toolTipContent: "<b>{label}:</b> {y}% (#percent%)",
		showInLegend: "true",
		legendText: "{label}",
		indexLabelFontSize: 16,
		dataPoints: ${datosGraficaPruebas}
	}]
});
chartPPorc.render();
copiaPorcentaje(chartPos, chartPPorc)


var chart3 = new CanvasJS.Chart("grafParetoPruebas", {
	exportEnabled: true,
	animationEnabled: true,
	theme: "light2", // "light1", "dark1", "dark2"
	title: {
		text: "Pareto de Pruebas Realizadas por ${datospor}."
	},
	subtitles: [{
		text: "",
		fontSize: 16
	}],
	axisY: {
		title: "Pruebas",
		lineColor: "#4F81BC",
		tickColor: "#4F81BC",
		labelFontColor: "#4F81BC",
		gridThickness: 0
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
	data: [{
		type: "column",
		indexLabel: "{y}",
		name: "Num. Pruebas",
		showInLegend: true,
		dataPoints: ${datosGraficaPruebas}
	}]
});
chart3.render();
creaOrden(chart3);
creaPareto(chart3);


var chart4 = new CanvasJS.Chart("grafParetoPositivos", {
	exportEnabled: true,
	animationEnabled: true,
	theme: "light2", // "light1", "dark1", "dark2"
	title: {
		text: "Pareto de Positivos por ${datospor}."
	},
	subtitles: [{
		text: "",
		fontSize: 16
	}],
	axisY: {
		title: "Positivos",
		lineColor: "#4F81BC",
		tickColor: "#4F81BC",
		labelFontColor: "#4F81BC",
		gridThickness: 0
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
	
	data: [{
		type: "column",
		indexLabel: "{y}",
		name: "Num. Positivos",
		showInLegend: true,
		dataPoints: ${datosGraficaPositivos}
	}]
});
chart4.render();
creaOrden(chart4);
creaPareto(chart4);

var chartParPor = new CanvasJS.Chart("grafParetoPorcentajes", {
	exportEnabled: true,
	animationEnabled: true,
	theme: "light2", // "light1", "dark1", "dark2"
	title: {
		text: "Pareto de Porcentaje de Positivos por ${datospor}."
	},
	subtitles: [{
		text: "",
		fontSize: 16
	}],
	axisY: {
		title: "Positivos en Pruebas",
		suffix: "%",
		lineColor: "#4F81BC",
		tickColor: "#4F81BC",
		labelFontColor: "#4F81BC",
		gridThickness: 0
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
	
	data: [{
		type: "column",
		indexLabel: "{y}%",
		name: "Positivos en Pruebas",
		showInLegend: true,
		dataPoints: ${datosGraficaPruebas}
	}]
});
chartParPor.render();
copiaPorcentaje(chartPos, chartParPor)
creaOrden(chartParPor);
creaPareto(chartParPor);


$("#btn-grafPareto").click();
$("#btn-grafCircular").click();

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
					<button type="button"
						class="btn ${criterios.filtroIsActivo ? 'btn-danger' : 'btn-info'}"
						data-toggle="collapse" data-target="#divfiltros">Filtros
						${criterios.filtroIsActivo ? 'Activados ' : 'Desactivados '}</button>
					<div id="divfiltros"
						class="collapse ${criterios.filtroIsActivo ? 'show' : ''}">

						<sec:authorize access="hasRole('ROLE_GESTOR')">
							<div>
								<table class="table table-striped table-hover ">
									<tbody>
										<form:form
											action="${pageContext.request.contextPath}/${criterios.pagActual}/filter/region"
											method="POST">

											<tr>
												<th></th>
												<th><label class="col col-form-label">Seleccione
														las regiones que desea mostrar:</label></th>
												<th><c:if test="${regionesselfiltro.isEmpty()}">
														<div class="alert alert-info my-4">No existen
															regiones...</div>
													</c:if> <select name="regionesfiltrar" id="regionesfiltrar"
													class="form-control" multiple="multiple">
														<c:forEach var="cto" items="${regionesselfiltro}">
															<option value="${cto.id}"
																Label="Región ${cto.id} ${cto.denominacion} habitantes ${cto.habitantes}"
																${criterios.getFiltroIdsRegion().contains(cto.id) ? 'selected' : ''} />
														</c:forEach>
												</select></th>
												<th><button class="btn btn-outline-success"
														type="submit">Seleccionar</button></th>
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
										<form:form
											action="${pageContext.request.contextPath}/${criterios.pagActual}/filter/centro"
											method="POST">

											<tr>
												<th></th>
												<th><label class="col col-form-label">Seleccione
														los centros que desea mostrar:</label></th>
												<th><c:if test="${centrosselfiltro.isEmpty()}">
														<div class="alert alert-info my-4">No existen
															centros...</div>
													</c:if> <select name="centrosfiltrar" id="centrosfiltrar"
													class="form-control" multiple="multiple">
														<c:forEach var="cto" items="${centrosselfiltro}">
															<option value="${cto.id}"
																Label="Centro ${cto.id} ${cto.denominacion} pacientes ${cto.pacientes}"
																${criterios.getFiltroIdsCentro().contains(cto.id) ? 'selected' : ''} />
														</c:forEach>
												</select></th>
												<th><button class="btn btn-outline-success"
														type="submit">Seleccionar</button></th>
											</tr>
										</form:form>

									</tbody>
								</table>
							</div>
						</sec:authorize>

						<div>
							<table class="table table-striped table-hover ">
								<tbody>
									<form:form
										action="${pageContext.request.contextPath}/${criterios.pagActual}/filter/date"
										method="POST">

										<tr>
											<th></th>
											<th><label class="col col-form-label">Seleccione
													las fechas entre las que desea mostrar:</label></th>
											<th>Desde: <input type="date" name="datedesde"
												id="datedesde" class="form-control dat1"
												value="${criterios.getFiltroDateDesdeString()}" />
											</th>
											<th>Hasta: <input type="date" name="datehasta"
												id="datehasta" class="form-control dat2"
												value="${criterios.getFiltroDateHastaString()}" />
											</th>
											<th>Desactivar: <input type="checkbox"
												class="form-control habilit"
												${criterios.filtroFechaActivo ? '' : 'checked="checked"'} />
											</th>
											<th><button class="btn btn-outline-success"
													type="submit" id="boton-fecha">Seleccionar</button></th>
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

    if (($dat1.val()=='${criterios.inicioDateDesdeString}') && ($dat2.val()=='${criterios.inicioDateHastaString}')) {
        $dat1.val(d1);
        $dat2.val(d2);
    } else {
        $dat1.val('${criterios.inicioDateDesdeString}');
        $dat2.val('${criterios.inicioDateHastaString}');
        $("#boton-fecha").click();
    }
});
</script>

						<div>
							<table class="table table-striped table-hover ">
								<tbody>
									<form:form
										action="${pageContext.request.contextPath}/${criterios.pagActual}/filter/dato"
										method="POST">

										<tr>
											<th></th>
											<th><label class="col col-form-label">Seleccione
													los datos de perfil que desea mostrar:</label></th>
											<th><c:if test="${centrosselfiltro.isEmpty()}">
													<div class="alert alert-info my-4">No existen
														datos...</div>
												</c:if> <select name="datosfiltrar" id="datosfiltrar"
												class="form-control" multiple="multiple">
													<c:forEach var="cto" items="${datosselfiltro}">
														<option value="${cto.opcion}"
															Label="Dato Perfil: ${cto.opcion}"
															${criterios.getFiltroIdsDato().contains(cto.opcion) ? 'selected' : ''} />
													</c:forEach>
											</select></th>
											<th><button class="btn btn-outline-success"
													type="submit">Seleccionar</button></th>
										</tr>
									</form:form>

								</tbody>
							</table>
						</div>
					</div>
				</div>

				<hr>
				<div class="container">
					<button type="button" id="btn-graficos" class="btn btn-info"
						data-toggle="collapse" data-target="#divgraficos">Gráficos
					</button>
					<div id="divgraficos" class="collapse show">

						<div>
							<table class="table table-striped table-hover ">
								<tbody>
									<form:form
										action="${pageContext.request.contextPath}/${criterios.pagActual}/grafx"
										method="POST">

										<tr>
											<th></th>
											<th><label class="col col-form-label">Seleccione
													los datos del grafico que desea mostrar:</label></th>
											<th><c:if test="${xgrafica.isEmpty()}">
													<div class="alert alert-info my-4">No existen
														datos...</div>
												</c:if> <select name="datosgrafx" id="datosgrafx"
												class="form-control">
													<c:forEach var="cto" items="${xgrafica}">
														<option value="${cto}" Label="${cto}"
															${criterios.getXGrafica()==cto ? 'selected' : ''} />
													</c:forEach>
											</select></th>
											<th><button class="btn btn-outline-success"
													type="submit">Seleccionar</button></th>
										</tr>
									</form:form>

								</tbody>
							</table>
						</div>

						<div class="container-xl">
							<div class="table-responsive">
								<div class="table-wrapper">

									<div class="table-title">
										<div class="row">
											<div class="col-sm-6">
												<h2>Datos Acumulados de la Selección</h2>
											</div>
										</div>
									</div>
									<div id="grafPositivosDePruebas"
										style="height: 370px; width: 100%;"></div>
									<br />
									<div id="grafPruebasYPositivos"
										style="height: 370px; width: 100%;"></div>
									<br />

									<div class="container">
										<button type="button" id="btn-grafCircular"
											class="btn btn-info" data-toggle="collapse"
											data-target="#divgrafCircular">Gráficos Circulares</button>
										<div id="divgrafCircular" class="collapse show">

											<div id="grafPiePruebas" style="height: 370px; width: 100%;"></div>
											<br />
											<div id="grafPiePositivos"
												style="height: 370px; width: 100%;"></div>
											<br />
											<div id="grafPiePorcentajes"
												style="height: 370px; width: 100%;"></div>
											<br />
										</div>
									</div>
									<br />
									<div class="container">
										<button type="button" id="btn-grafPareto" class="btn btn-info"
											data-toggle="collapse" data-target="#divgrafPareto">Gráficos
											de Pareto</button>
										<div id="divgrafPareto" class="collapse show">
											<div id="grafParetoPruebas"
												style="height: 370px; width: 100%;"></div>
											<br />
											<div id="grafParetoPositivos"
												style="height: 370px; width: 100%;"></div>
											<br />
											<div id="grafParetoPorcentajes"
												style="height: 370px; width: 100%;"></div>
											<br />
										</div>
									</div>
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
								<td><c:out
										value="${reg.datosfecha.centro.region.denominacion}"></c:out></td>
								<td><c:out value="${reg.datosfecha.centro.denominacion}"></c:out></td>
								<td><c:out
										value="${reg.datosfecha.centro.tipocentro.opcion}"></c:out></td>
								<td class="text-right"><fmt:formatNumber type="number"
										value="${reg.datosfecha.totalpruebas}" /></td>
								<td><fmt:formatDate type="date"
										value="${reg.datosfecha.fecha}" pattern="yyyy-MM-dd" /></td>
								<td><c:out value="${reg.datosfecha.tipoprueba.opcion}"></c:out></td>
								<td class="text-right"><fmt:formatNumber type="number"
										value="${reg.totalpositivos}" /></td>
								<c:forEach var="d" items="${reg.datos}">
									<c:if test="${d.pregunta.tipopregunta.opcion =='Texto'}">
										<td><c:out value="${d.dato}"></c:out></td>
									</c:if>
									<c:if test="${d.pregunta.tipopregunta.opcion =='Número'}">
										<td class="text-right"><fmt:formatNumber type="number"
												value="${d.dato}" /></td>
									</c:if>
									<c:if test="${d.pregunta.tipopregunta.opcion =='Fecha'}">
										<td><fmt:parseDate value="${d.dato}" pattern="yyyy-MM-dd"
												var="date" /> <fmt:formatDate type="date" value="${date}"
												pattern="yyyy-MM-dd" /></td>
									</c:if>
									<c:if test="${d.pregunta.tipopregunta.opcionestabla !=''}">
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/canvas/canvasjs.min.js"></script>
</body>
</html>