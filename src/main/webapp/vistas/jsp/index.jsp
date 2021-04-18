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
	<div class="card-header text-center">BDDCoronavirus</div>
	<div class="card-body">
	<br><h1 class="text-center">BddCoronavirus</h1><br><br>
<h5 class="card-title text-center"> Bienvenido a la aplicación para gestión de la evolución de los contagios por coronavirus</h5><br><br>
<h6 class="text-center">Grado en Tecnologías de la Información de la Uned</h6><br>
<h6 class="text-center">Práctica de la Asignatura Tecnologías Web</h6><br>
<h6 class="text-center">Curso 2020-2021</h6><br>

<div>
<a href="/admin/region/list">listar regiones</a>
<a href="/admin/region/datos">datos regiones</a>
<a href="/admin/centro/list">listar centros</a>
<a href="/region/1/datos/view">ver region</a>
<a href="/centro/1/datos/view">ver centro</a>
</div>

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