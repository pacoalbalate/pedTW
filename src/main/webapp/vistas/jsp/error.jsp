<%@ include file="comunes/includes.jsp"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="comunes/cabecera.jsp"%>


<title>${titulo}</title>
</head>
<body>

<c:import url="comunes/menu.jsp"></c:import>


	<div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">

				<jsp:include page='comunes/barra_titulo.jsp' />


<div>
	<div class="card border-primary">
	<div class="card-header text-center">ContagPLUS</div>
	<div class="card-body">
	<br><h1 class="text-center">OOPPS, parece que la página a la que intentas acceder no esta disponible</h1><br><br>
<h5 class="card-title text-center"> Es posible que no exista o no tengas permisos para acceder</h5><br><br>
<h6 class="text-center">Usa el menu de la parte superior para acceder a las funciones de tu usuario</h6><br>


</div>

</div>
</div>
<hr/>






			</div>
		</div>
	</div>

<%@ include file="comunes/pie.jsp"%>


</body>
</html>