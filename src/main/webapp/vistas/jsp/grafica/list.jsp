<%@ include file="../comunes/includes.jsp"%>


<!DOCTYPE html>
<html>
<head>

<%@ include file="../comunes/cabecera.jsp"%>

<%
String diagramData= (String)request.getAttribute("diagramaBarras");
String sectorData= (String)request.getAttribute("diagramaSectores");

 
%>
<script type="text/javascript">
window.onload = function() { 
    var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	theme: "light2", // "light1", "dark1", "dark2"
	title: {
		text: "Nº de positivos por centro."
	},
	subtitles: [{
		text: "",
		fontSize: 16
	}],
	axisY: {
		scaleBreaks: {
			type: "wavy",
			autoCalculate: true
		}
	},
	data: [{
		type: "column",
		indexLabel: "{y}",
		dataPoints: <% out.print(diagramData);%>
	}]
}   );
chart.render();

var chart1 = new CanvasJS.Chart("chartContainer1", {
	theme: "light2", // "light1", "dark1", "dark2"
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "% de contagios por región"
	},
	data: [{
		type: "pie",
		toolTipContent: "<b>{label}</b>: {y}%",
		indexLabelFontSize: 16,
		indexLabel: "{label} - {y}%",
		dataPoints: <%out.print(sectorData);%>
	}]
});
chart1.render();
 
}
</script>
</head>
<body>

	<c:import url="../comunes/menu.jsp"></c:import>

	<div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">

				<jsp:include page='../comunes/barra_titulo.jsp'></jsp:include>
				<div id="chartContainer" style="height: 370px; width: 100%;"></div>
				<br />
				<div id="chartContainer1" style="height: 370px; width: 100%;"></div>
			</div>
		</div>
	</div>

	<%@ include file="../comunes/grid//vborrar_modal.jsp"%>
	<%@ include file="../comunes/pie.jsp"%>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/canvas/canvasjs.min.js"></script>

</body>
</html>