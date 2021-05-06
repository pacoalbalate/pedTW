<%@ include file="../comunes/includes.jsp"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="../comunes/cabecera.jsp"%>


</head>
<body>

<c:import url="../comunes/menu.jsp"></c:import>


	<div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">

				<jsp:include page='../comunes/barra_titulo.jsp' />


<div>
	<div class="card border-primary">
	<div class="card-header text-center">ACCESO DENEGADO</div>
	<div class="card-body">
<h5 class="card-title"> Su usuario no dispone de permisos para acceder a la zona solicitada</h5>


</div>

</div>
</div>
<hr/>



 

			</div>
		</div>
	</div>

<%@ include file="../comunes/pie.jsp"%>


</body>
</html>