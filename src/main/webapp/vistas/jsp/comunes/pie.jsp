<%@ include file="../comunes/includes.jsp" %>

<footer class="container">
<hr/>
<sec:authorize access="isAuthenticated()">
<p>
	<span><b>Usuario: </b> <sec:authentication property="principal.username"/> <b>Roles: </b><sec:authentication property="principal.authorities"/> </span> 
</p>
</sec:authorize>  
<p>
	<span><b>Realizado por:</b>  Francisco Cobos - Cecilia Crespo - Francisco J. Albalate</span> 
</p>
	<p align="right">
	<span><img src="${pageContext.request.contextPath}/img/logo_uned.gif" alt="Uned logo" />
		&copy;<b>UNED 2021</b></span> 
		</p>

</footer>
