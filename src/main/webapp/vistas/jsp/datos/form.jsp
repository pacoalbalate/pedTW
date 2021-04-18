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

<form:form action="${pageContext.request.contextPath}/centro/${datosfecha.centro.id}/datos/save" modelAttribute="datosfecha" method="POST">


<div class="form-group row">
<label class="col-sm-2 col-form-label">Centro:</label>
<div class="col-sm-6">
<label class="form-control">${datosfecha.centro.denominacion}</label>
</div>
</div>

<div class="form-group row">
<label class="col-sm-2 col-form-label">Fecha:</label>
<div class="col-sm-6">
<spring:bind path="fecha">
<form:input type="date" path="fecha" class="form-control ${status.error ? 'alert-danger' : ''}"/>
<form:errors path="fecha" class="form-text text-danger"/>
</spring:bind>
</div>
</div>


<div class="form-group row">
<label class="col-sm-2 col-form-label">Tipo de prueba:</label>
<div class="col-sm-6 row">
<spring:bind path="tipoprueba">
<form:radiobuttons items="${tipopruebas}" path="tipoprueba" itemValue="id" itemLabel="opcion" class="form-control" />
<% /*<form:select path="tipocentro" items="${tipocentros}" itemValue="id" itemLabel="opcion" /> */%>
<form:errors path="tipoprueba" class="form-text text-danger"/>
</spring:bind>
</div>
</div>


<div class="form-group row">
<label class="col-sm-2 col-form-label">Pruebas realizadas:</label>
<div class="col-sm-6">
<spring:bind path="totalpruebas">
<form:input type="number" path="totalpruebas" class="form-control ${status.error ? 'alert-danger' : ''}"/>
<form:errors path="totalpruebas" class="form-text text-danger"/>
</spring:bind>
</div>
</div>



<div class="form-group">
<div class="col-sm-6">
<input type="submit" value="Guardar" class="btn btn-primary"/>
</div>
</div>

</form:form>
</div>
<hr/>



<c:if test="${datosfecha.id >0}">

<div class="container">
  <button type="button" class="btn ${(datosperfil.id>0) ? 'btn-success ' : 'btn-info '}" data-toggle="collapse" data-target="#divfiltros"> ${(datosperfil.id>0) ? 'Editar ' : 'Nuevo '} Perfil </button>
<label class="col-sm-10 col-form-label text-right">Total Positivos: ${sumapositivos} </label>

  <div id="divfiltros" class="collapse ${((datosperfil.id>0) || (danger != null)) ? 'show ' : ''}">

<div>


<form:form action="${pageContext.request.contextPath}/centro/${datosfecha.centro.id}/datos/${datosfecha.id}/perfil/save" modelAttribute="datosperfil" method="POST">

<div class="form-group row">
<label class="col-sm-2 col-form-label">Positivos:</label>
<div class="col-sm-6">
<spring:bind path="totalpositivos">
<form:input type="number" path="totalpositivos" class="form-control ${status.error ? 'alert-danger' : ''}"/>
<form:errors path="totalpositivos" class="form-text text-danger"/>
</spring:bind>
</div>
</div>

						<c:forEach var="d" items="${datosperfil.datos}">
<c:choose>
<c:when test = "${d.pregunta.tipopregunta.opcion =='Texto'}">
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">${d.pregunta.denominacion}:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" value="${d.dato}"  name="${d.pregunta.denominacion}" id="${d.pregunta.denominacion}" />
		</div>
	</div>
</c:when>
<c:when test = "${d.pregunta.tipopregunta.opcion =='Número'}">
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">${d.pregunta.denominacion}:</label>
		<div class="col-sm-6">
			<input type="number" class="form-control" value="${d.dato}"  name="${d.pregunta.denominacion}" id="${d.pregunta.denominacion}" />
		</div>
	</div>
</c:when>
<c:when test = "${d.pregunta.tipopregunta.opcion =='Fecha'}">
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">${d.pregunta.denominacion}:</label>
		<div class="col-sm-6">
			<input type="date" class="form-control" value="${d.dato}"   name="${d.pregunta.denominacion}" id="${d.pregunta.denominacion}" />
		</div>
	</div>
</c:when>
<c:otherwise>
		<c:if test="${d.pregunta.tipopregunta.opcionestabla !=''}">
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">${d.pregunta.denominacion}:</label>
			<div class="col-sm-6 row">
				<select class="form-control"  name="${d.pregunta.denominacion}" id="${d.pregunta.denominacion}" >
						<c:forEach var="opc" items="${opcionesdatos}">
						<c:if test="${d.pregunta.tipopregunta.opcionestabla == opc.tipo}">
							<option value="${opc.opcion}" ${d.dato==opc.opcion ? 'selected="selected"' : ''}>${opc.opcion}</option>
						</c:if>
						</c:forEach>
				</select>
 			</div>
		</div>
		</c:if>
</c:otherwise>
</c:choose>
						</c:forEach>



<div class="form-group">
<div class="col-sm-6">
<input type="submit" value="Guardar" class="btn btn-primary"/>
</div>
</div>

</form:form>
</div>
<hr/>

 </div>
</div>
</c:if>



<c:if test="${datosfecha.getDatosperfiles().isEmpty()}">
<div class="alert alert-info my-4">No tiene perfiles de datos asociados...</div>
</c:if>


<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table table-striped table-hover table-responsive">


					<thead>
						<tr>
<c:if test="${preguntas.size()>8}">
	<th>Acciones</th>
</c:if>
<th>Id </th>
<th>Positivos </th>
<c:forEach var="cab" items="${preguntas}">
	<th> ${cab.denominacion} </th>
</c:forEach>
	<th>Acciones</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="reg" items="${datosfecha.datosperfiles}">
							<c:url var="linkVer" value="${pageContext.request.contextPath}/centro/${reg.id}/"></c:url>
							<c:url var="linkSave" value="?perfil=${reg.id}"></c:url>

							<tr>
							<c:if test="${preguntas.size()>8}">
								<td>
									<a href="${linkSave}" class="edit"><i class="material-icons" data-toggle="tooltip" title="Editar">&#xE254;</i></a>
									<a href="#borrarModal" class="delete" data-id="${reg.id}"
									data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Borrar">&#xE872;</i></a>
								</td>
							</c:if>
								<td><c:out value="${reg.id}"></c:out></td>
								<td class="text-right"><fmt:formatNumber type = "number" value="${reg.totalpositivos}" /></td>
						<c:forEach var="d" items="${reg.datos}">
								<c:if test="${d.pregunta.tipopregunta.opcion =='Texto'}">
									<td><c:out value="${d.dato}"></c:out></td>
								</c:if>
								<c:if test="${d.pregunta.tipopregunta.opcion =='Número'}">
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
								<td>
									<a href="${linkSave}" class="edit"><i class="material-icons" data-toggle="tooltip" title="Editar">&#xE254;</i></a>
									<a href="#borrarModal" class="delete" data-id="${reg.id}" data-toggle="modal">
												<i class="material-icons" data-toggle="tooltip" title="Borrar">&#xE872;</i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					
				</table>
</div>







			</div>
		</div>
	</div>





<%@ include file="../comunes/grid//vborrar_modal.jsp"%>
<%@ include file="../comunes/pie.jsp"%>


</body>
</html>