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
						<div class="card-header text-center">Por favor identifiquese</div>
						<div class="card-body">

							<form action="${pageContext.request.contextPath}/login"
								method="POST" class="form-signin">

								<div class="form-group row">
									<label class="col-sm-2 col-form-label">Usuario:</label>
									<div class="col-sm-6">
										<input type="text" name="username" placeholder="Usuario"
											autofocus required class="form-control"> <span
											class="form-text text-danger"> ${error_txt} </span>
									</div>
								</div>


								<div class="form-group row">
									<label class="col-sm-2 col-form-label">Contraseña:</label>
									<div class="col-sm-6">
										<div class="input-group">
											<input type="password" name="password"
												placeholder="Contraseña" required
												class="form-control pwd ${danger != null ? 'alert-danger' : ''}">
											<span class="input-group-btn"><button
													class="btn btn-outline-secondary reveal" type="button">
													<i class="fa fa-eye"></i>
												</button> </span>
										</div>
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-6">
										<input type="submit" value="Identificarse"
											class="btn btn-primary" />
										<sec:csrfInput />

									</div>
								</div>

							</form>
						</div>

					</div>
				</div>
				<hr />





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