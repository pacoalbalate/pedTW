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
						<div class="card-header text-center">Cambio de Contraseña
							del Usuario Identificado</div>
						<div class="card-body">

							<form action="${pageContext.request.contextPath}/login/change"
								method="POST" class="form-signin">

								<div class="form-group row">
									<label class="col-sm-2 col-form-label">Contraseña
										actual:</label>
									<div class="col-sm-6">
										<div class="input-group">
											<input type="password" name="password_old"
												placeholder="Contraseña actual" autofocus required
												class="form-control pwd1 ${error_ant_txt != null ? 'alert-danger' : ''}">
											<span class="input-group-btn"><button
													class="btn btn-outline-secondary reveal1" type="button">
													<i class="fa fa-eye"></i>
												</button> </span>
										</div>
										<span class="form-text text-danger"> ${error_ant_txt} </span>
									</div>
								</div>


								<div class="form-group row">
									<label class="col-sm-2 col-form-label">Nueva
										Contraseña:</label>
									<div class="col-sm-6">
										<div class="input-group">
											<input type="password" name="password_new1"
												placeholder="Nueva Contraseña" required
												class="form-control pwd2 ${error_new_txt != null ? 'alert-danger' : ''}">
											<span class="input-group-btn"><button
													class="btn btn-outline-secondary reveal2" type="button">
													<i class="fa fa-eye"></i>
												</button> </span>
										</div>
										<span class="form-text text-danger"> ${error_new_txt} </span>
									</div>
								</div>


								<div class="form-group row">
									<label class="col-sm-2 col-form-label">Nueva
										Contraseña:</label>
									<div class="col-sm-6">
										<div class="input-group">
											<input type="password" name="password_new2"
												placeholder="Repita la Nueva Contraseña" required
												class="form-control pwd3 ${error_new_txt != null ? 'alert-danger' : ''}">
											<span class="input-group-btn"><button
													class="btn btn-outline-secondary reveal3" type="button">
													<i class="fa fa-eye"></i>
												</button> </span>
										</div>
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-6">
										<input type="submit" value="Modificar" class="btn btn-primary" />
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
$(".reveal1").on('click',function() {
    var $pwd = $(".pwd1");
    if ($pwd.attr('type') === 'password') {
        $pwd.attr('type', 'text');
    } else {
        $pwd.attr('type', 'password');
    }
});
$(".reveal2").on('click',function() {
    var $pwd = $(".pwd2");
    if ($pwd.attr('type') === 'password') {
        $pwd.attr('type', 'text');
    } else {
        $pwd.attr('type', 'password');
    }
});
$(".reveal3").on('click',function() {
    var $pwd = $(".pwd3");
    if ($pwd.attr('type') === 'password') {
        $pwd.attr('type', 'text');
    } else {
        $pwd.attr('type', 'password');
    }
});
</script>

</body>
</html>