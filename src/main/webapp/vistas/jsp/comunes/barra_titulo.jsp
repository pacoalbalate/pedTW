<%@ include file="../comunes/includes.jsp"%>

<div class="table-title">
	<div class="row">
		<div class="col-sm-6">
			<h2>${titulo}</h2>
		</div>
		<div class="col-sm-6">
			<c:if test="${param.barra_volver=='si'}">
				<a class="btn btn-info"
					href="${pageContext.request.contextPath}/${criterios.pagActual}"
					title="Volver"> <i class="material-icons" title="Volver">exit_to_app</i>
					<span>Volver</span></a>
			</c:if>
			<c:if test="${param.barra_tipo=='export'}">
				<a href="export/csv" class="btn btn-success"><i
					class="material-icons">ios_share</i> <span>CSV</span></a>
			</c:if>
			<c:if test="${param.barra_tipo=='new'}">
				<a href="new" class="btn btn-success" title="Añadir Nuevo"><i
					class="material-icons" title="Añadir Nuevo">&#xE147;</i> <span>Añadir
						Nuevo</span></a>
			</c:if>
			<c:if test="${param.barra_buscar=='si'}">
				<form:form class="d-flex"
					action="${pageContext.request.contextPath}/${criterios.pagActual}"
					method="GET">
					<input
						class="form-control me-2 ${criterios.filtro!='' ? 'bg-light text-danger font-weight-bold' : ''}"
						type="search" placeholder="Buscar&hellip;" aria-label="Search"
						name="filtro" id="filtro" value="${criterios.filtro}">
					<button class="btn btn-info" style="font-size: 24px" type="submit"
						title="Buscar">
						<i class="fa fa-search" title="Buscar"></i>
					</button>
				</form:form>
			</c:if>
		</div>
	</div>
</div>
