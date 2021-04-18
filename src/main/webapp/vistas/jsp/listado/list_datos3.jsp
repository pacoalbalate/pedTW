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
													Label="Región ${cto.id} ${cto.denominacion} habitantes ${cto.habitantes}"
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
													Label="Dato: ${cto.opcion}"
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

				<table class="table table-striped table-hover table-responsive">
				<c:import url="../comunes/grid/cabeceras_filas.jsp"></c:import>

					<tbody>
						<c:forEach var="reg" items="${datos_ver}">
							<tr>
								<td><c:out value="${reg[0].id}"></c:out></td>
								<td><c:out value="${reg[1]}"></c:out></td>
								<td><c:out value="${reg[2]}"></c:out></td>
								<td><c:out value="${reg[3]}"></c:out></td>
								<td class="text-right"><fmt:formatNumber type = "number" value="${reg[4]}" /></td>
								<td><fmt:formatDate type = "date" value="${reg[5]}" pattern="yyyy-MM-dd"/></td>
								<td><c:out value="${reg[6]}"></c:out></td>
								<td class="text-right"><fmt:formatNumber type = "number" value="${reg[7]}" /></td>
						<c:forEach var="d" items="${reg[0].datos}">
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
							</tr>
						</c:forEach>
					</tbody>
					
				</table>

				<c:import url="../comunes/grid/navegador_paginas.jsp"></c:import>
			</div>
		</div>
	</div>

<%@ include file="../comunes/pie.jsp"%>


</body>
</html>