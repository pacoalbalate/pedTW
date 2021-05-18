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

				<jsp:include page='../comunes/barra_titulo.jsp'>
					<jsp:param name="barra_volver" value="si" />
				</jsp:include>



<div>

<form:form action="${pageContext.request.contextPath}/admin/centro/save" modelAttribute="centro" method="POST">

<div class="form-group row">
<label class="col-sm-2 col-form-label">Denominación:</label>
<div class="col-sm-6">
<spring:bind path="denominacion">
<form:input type="text" path="denominacion" class="form-control ${status.error ? 'alert-danger' : ''}"/>
<form:errors path="denominacion" class="form-text text-danger"/>
</spring:bind>
</div>
</div>


<div class="form-group row">
<label class="col-sm-2 col-form-label">Pacientes:</label>
<div class="col-sm-6">
<spring:bind path="pacientes">
<form:input type="number" path="pacientes" class="form-control ${status.error ? 'alert-danger' : ''}"/>
<form:errors path="pacientes" class="form-text text-danger"/>
</spring:bind>
</div>
</div>


<div class="form-group row">
<label class="col-sm-2 col-form-label">Tipos:</label>
<div class="col-sm-6 row">
<spring:bind path="tipocentro">
<form:radiobuttons items="${tipocentros}" path="tipocentro" itemValue="id" itemLabel="opcion" class="form-control" />
<% /*<form:select path="tipocentro" items="${tipocentros}" itemValue="id" itemLabel="opcion" /> */%>
<form:errors path="tipocentro" class="form-text text-danger"/>
</spring:bind>
</div>
</div>

<div class="form-group">
<div class="col-sm-6">
<input type="submit" value="Guardar" class="btn btn-primary"/>
</div>
</div>


<div class="form-group row">
<label class="col-sm-2 col-form-label">Pertenece a Región:</label>
<div class="col-sm-6 row">
<c:if test="${centro.region !=null}">
<label class="form-control">${centro.region.denominacion}</label>
</c:if>
<c:if test="${centro.region ==null}">
<label class="form-control">Pendiente. Sin región asociada...</label>
</c:if>
<label class="col-form-label text-right">Perfiles de Contagios Asociados al Centro: ${numdatoscentro} </label>
</div>
</div>




</form:form>
</div>
<hr/>





			</div>
		</div>
	</div>

<%@ include file="../comunes/pie.jsp"%>


</body>
</html>