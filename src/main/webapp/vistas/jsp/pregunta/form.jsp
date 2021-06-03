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
						action="${pageContext.request.contextPath}/admin/pregunta/save"
						modelAttribute="pregunta" method="POST">

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
							<label class="col-sm-2 col-form-label">Tipo de pregunta:</label>
							<div class="col-sm-6">
								<spring:bind path="tipopregunta">
									<% /*<form:radiobuttons items="${tipopreguntas}" path="tipopregunta" itemValue="id" itemLabel="opcion" class="form-control" /> */%>
									<form:select path="tipopregunta" items="${tipopreguntas}"
										itemValue="id" itemLabel="opcion" class="form-control" />
									<form:errors path="tipopregunta" class="form-text text-danger" />
								</spring:bind>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-6">
								<div class="container">
									<input type="submit" value="Guardar" class="btn btn-primary" />
									<label class="col-sm-10 col-form-label text-right">Perfiles
										de Contagios Asociados a la Pregunta: ${numdatospregunta} </label>
								</div>
							</div>
						</div>


					</form:form>
				</div>
				<hr />





			</div>
		</div>
	</div>

	<%@ include file="../comunes/pie.jsp"%>


</body>
</html>