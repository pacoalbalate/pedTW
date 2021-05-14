<%@ include file="../comunes/includes.jsp"%>
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/index"><img src="${pageContext.request.contextPath}/img/elipse.gif" alt="ContagPLUS logo" />
		 </a>
			<button class="navbar-toggler" type="button"
				data-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation"> 
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<sec:authorize access="hasRole('ROLE_CENTRO')">  
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/centro/">Centro</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/listado/">Listado</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_REGION')">  
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/usuario/">Region</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/listado/">Listado</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_GESTOR')">  
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-expanded="false">
							Administración </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/region/list">
									Regiones</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/centro/list">
									Centros</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/pregunta/list">
									Perfil</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/usuario/list">
									Usuarios</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-expanded="false">
							Datos </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/listado/">
									Listado</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/grafica/datos">Datos
									Graficos</a></li>
						</ul></li>
				</sec:authorize>
					
				</ul>
				<ul class="navbar-nav navbar-right d-flex">
					<sec:authorize access="!isAuthenticated()">
						<li><a class="btn btn-outline-info" href="${pageContext.request.contextPath}/login">Acceder</a>
						</li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li class="dropdown"><a
							class="btn btn-outline-success dropdown-toggle" href="#"
							role="button" id="dropdownMenuLink" data-toggle="dropdown"
							aria-expanded="false"> <span><sec:authentication
										property="principal.username" /></span>
						</a>

							<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/login/change">
									Cambiar Contraseña</a>
							<hr class="dropdown-divider">
								<form id="lgoutForm" action="${pageContext.request.contextPath}/logout" method="POST">
									<button class="dropdown-item" type="submit"
										onclick="document.getElementById('logoutForm')"> Desconectar <i class="material-icons">power_settings_new</i> </button>
									<sec:csrfInput />
								</form>
							</div>
						</li>
					</sec:authorize>
				
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/BorradorMemoria.pdf">Memoria</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/doc/index.html">Javadoc</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<c:if test="${danger != null}">
		<div class="alert alert-danger">
			<c:out value="${danger}" />
		</div>
	</c:if>
	<c:if test="${warning != null}">
		<div class="alert alert-warning">
			<c:out value="${warning}" />
		</div>
	</c:if>
	<c:if test="${info != null}">
		<div class="alert alert-info">
			<c:out value="${info}" />
		</div>
	</c:if>
	<c:if test="${success != null}">
		<div class="alert alert-success">
			<c:out value="${success}" />
		</div>
	</c:if>

</header>

