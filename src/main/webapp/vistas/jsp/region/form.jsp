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

					<form:form
						action="${pageContext.request.contextPath}/admin/region/save"
						modelAttribute="region" method="POST">

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Denominación:</label>
							<div class="col-sm-6">
								<spring:bind path="denominacion">
									<form:input type="text" path="denominacion"
										class="form-control ${status.error ? 'alert-danger' : ''}" />
									<form:errors path="denominacion" class="form-text text-danger" />
								</spring:bind>
							</div>
						</div>


						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Habitantes:</label>
							<div class="col-sm-6">
								<spring:bind path="habitantes">
									<form:input type="number" path="habitantes"
										class="form-control ${status.error ? 'alert-danger' : ''}" />
									<form:errors path="habitantes" class="form-text text-danger" />
								</spring:bind>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-6">
								<input type="submit" value="Guardar" class="btn btn-primary" />
							</div>
						</div>

					</form:form>
				</div>
				<hr />

				<c:if test="${region.id>0}">

					<form:form action="asoc" method="POST">

						<table class="table table-striped table-hover table-responsive">
							<tbody>

								<tr>
									<td>Seleccione los Centros de la Regón:</td>
									<td></td>
									<td><c:if test="${centroslibres.isEmpty()}">
											<div class="alert alert-info my-4">No existen centros
												sin asociar...</div>
										</c:if> <c:if test="${!centroslibres.isEmpty()}">
											<select name="centro" id="centro" class="form-control">
												<c:forEach var="cto" items="${centroslibres}">
													<option value="${cto.id}"
														Label="Centro ${cto.id} ${cto.denominacion} ${cto.tipocentro.opcion} Pacientes ${cto.pacientes}" />
												</c:forEach>
											</select>
										</c:if></td>
									<td></td>
									<td><c:if test="${!centroslibres.isEmpty()}">
											<input type="submit" value="Asociar" class="btn btn-primary" />
										</c:if></td>
								</tr>


							</tbody>
						</table>

					</form:form>




					<div class="table-wrapper-scroll-y my-custom-scrollbar">
						<table class="table table-striped table-hover">


							<thead>
								<tr>
									<th>Id</th>
									<th>Denominacion</th>
									<th>Pacientes</th>
									<th>Tipo Centro</th>
									<th>Acciones</th>
								</tr>
							</thead>




							<tbody>

								<c:if test="${region.getCentros().isEmpty()}">
									<div class="alert alert-info my-4">No tiene centros
										asociados...</div>
								</c:if>

								<c:forEach var="reg" items="${region.centros}">
									<c:url var="linkVer"
										value="${pageContext.request.contextPath}/region/${reg.id}/"></c:url>
									<c:url var="linkSave" value="/admin/centro/edit/${reg.id}/"></c:url>
									<c:url var="linkDes" value="desa/${reg.id}/"></c:url>

									<tr>
										<td><c:out value="${reg.id}"></c:out></td>
										<td><c:out value="${reg.denominacion}"></c:out></td>
										<td><c:out value="${reg.pacientes}"></c:out></td>
										<td><c:out value="${reg.tipocentro.opcion}"></c:out></td>
										<td><a href="${linkSave}" class="edit"><i
												class="material-icons" data-toggle="tooltip" title="Editar">&#xE254;</i></a>
											<a href="${linkDes}" class="delete" data-id="${reg.id}">
												<i class="material-icons" data-toggle="tooltip"
												title="Desasociar">drive_file_move</i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>




			</div>
		</div>
	</div>

	<%@ include file="../comunes/pie.jsp"%>


</body>
</html>