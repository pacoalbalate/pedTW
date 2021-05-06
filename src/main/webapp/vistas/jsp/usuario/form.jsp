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

<form:form action="${pageContext.request.contextPath}/usuario/${regId}/admin/save" modelAttribute="usuario" method="POST">

<div class="form-group row">
<label class="col-sm-2 col-form-label">Usuario:</label>
<div class="col-sm-6">
<spring:bind path="nombreusuario">
<form:input type="text" path="nombreusuario" class="form-control ${status.error ? 'alert-danger' : ''}"/>
<form:errors path="nombreusuario" class="form-text text-danger"/>
</spring:bind>
</div>
</div>


<div class="form-group row">
<label class="col-sm-2 col-form-label">Clave:</label>
<div class="col-sm-6">
<spring:bind path="clave">
       <div class="input-group">
<form:input type="password" path="clave" class="form-control pwd ${status.error ? 'alert-danger' : ''}" />
<span class="input-group-btn"><button class="btn btn-outline-secondary reveal" type="button"><i class="fa fa-eye"></i></button>
</span>
</div>
<form:errors path="clave" class="form-text text-danger"/>
</spring:bind>
</div>
</div>


<div class="form-group row">
<label class="col-sm-2 col-form-label">Activo:</label>
<div class="col-sm-6">
<spring:bind path="activo">
<form:checkbox path="activo"  class="form-control  ${status.error ? 'alert-danger' : ''}"  />
<form:errors path="activo" class="form-text text-danger"/>
</spring:bind>
</div>
</div>



<div class="form-group row">
<sec:authorize access="hasRole('ROLE_GESTOR')">  
<label class="col-sm-2 col-form-label">Rol:</label>
</sec:authorize>
<div class="col-sm-6 row">
<sec:authorize access="hasRole('ROLE_GESTOR')">  
          <select name="tipo_rol" id="tipo_rol" class="form-control" >
<c:forEach var="cto" items="${tiporoles}">
              <option value="${cto.opcionestabla}" Label="${cto.opcion}" ${cto.opcionestabla==tipo_rol ? 'selected' : ''}/>
</c:forEach>
          </select>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_REGION')">  
	<input type="hidden" name="tipo_rol" id="tipo_rol" class="form-control" value="ROLE_CENTRO" />
</sec:authorize>
</div>
</div>


<div class="form-group">
<div class="col-sm-6">
<input type="submit" value="Guardar" class="btn btn-primary"/>
</div>
</div>


<c:if test="${ctoreg_rol>0}">
<div class="form-group row">
<label class="col-sm-2 col-form-label">Acceso permitido a:</label>
<div class="col-sm-6">
<c:if test="${ctoreg_rol >0}">
<label class="form-control">${ctoreg_nombre}</label>
</c:if>
<c:if test="${ctoreg_rol<1}">
<label class="form-control">Pendiente. Sin autorización asociada...</label>
</c:if>
</div>
</div>
</c:if>

</form:form>
</div>
<hr/>



<c:if test="${(tipo_rol=='ROLE_CENTRO') or (tipo_rol=='ROLE_REGION')}">
<form:form action="asoc"  method="POST">

				<table class="table table-striped table-hover table-responsive">
<tbody>

<tr>
     <td>Seleccione ${(tipo_rol=='ROLE_CENTRO') ? 'el centro al' : 'la región a la'} que desea permitir el acceso:</td>
<td></td>
      <td>
<c:if test="${(tipo_rol=='ROLE_CENTRO')}">
          <select name="centro" id="centro" class="form-control">
<c:forEach var="cto" items="${centros}">
              <option value="${cto.id}" Label="Centro: ${cto.id} ${cto.denominacion}" ${cto.id==ctoreg_rol ? 'selected' : ''}/>
</c:forEach>
          </select>
</c:if>
<c:if test="${(tipo_rol=='ROLE_REGION')}">
          <select name="centro" id="centro" class="form-control">
<c:forEach var="cto" items="${regiones}">
              <option value="${cto.id}" Label="Region: ${cto.id} ${cto.denominacion}" ${cto.id==ctoreg_rol ? 'selected' : ''}/>
</c:forEach>
          </select>
</c:if>
      </td>
<td></td>
<td>
<input type="submit" value="Asociar" class="btn btn-primary"/>
</td>
</tr>

</tbody>
</table>

</form:form>



</c:if>


			</div>
		</div>
	</div>

<%@ include file="../comunes/pie.jsp"%>

<script type="text/javascript">
$(".reveal").on('click',function() {
    var $pwd = $(".pwd");
    if ($pwd.attr('type') === 'password') {
        $pwd.attr('type', 'text');
    } else {
        $pwd.attr('type', 'password');
    }
});
</script>

</body>
</html>