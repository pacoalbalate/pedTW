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
					<jsp:param name="barra_tipo" value="new" />
					<jsp:param name="barra_buscar" value="si" />
				</jsp:include>


				<table class="table table-striped table-hover ">
				<c:import url="../comunes/grid/cabeceras_filas.jsp"></c:import>

					<tbody>
						<c:forEach var="reg" items="${pagina.page.toList()}">
							<c:url var="linkSave" value="edit/${reg.id}/"></c:url>
							<tr>
								<td><c:out value="${reg.id}"></c:out></td>
								<td><c:out value="${reg.denominacion}"></c:out></td>
								<td><c:out value="${reg.tipopregunta.opcion}"></c:out></td>
								<td>
									<a href="${linkSave}" class="edit"><i class="material-icons" data-toggle="tooltip" title="Editar">&#xE254;</i></a>
									<a href="#borrarModal" class="delete" data-id="${reg.id}"
									data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Borrar">&#xE872;</i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<c:import url="../comunes/grid/navegador_paginas.jsp"></c:import>
			</div>
		</div>
	</div>

<%@ include file="../comunes/grid//vborrar_modal.jsp"%>
<%@ include file="../comunes/pie.jsp"%>


</body>
</html>