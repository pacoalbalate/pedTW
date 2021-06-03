<%@ include file="../../comunes/includes.jsp"%>
<nav>
	<div class="clearfix">
		<div class="hint-text">
			Mostrando <b>${pagina.paginaActual}</b> de <b>${pagina.totalPaginas}</b>
			páginas
		</div>
		<ul class="pagination">
			<li
				class="page-item ${pagina.page.first ? 'page-item disabled' : 'page-item'}">
				<c:if test="${pagina.page.first}">
					<a class="page-link"><i class="fa fa-angle-double-left"></i></a>
				</c:if> <c:if test="${not pagina.page.first}">
					<a class="page-link"
						href="${pageContext.request.contextPath}/${pagina.url}?pageNo=0"><i
						class="fa fa-angle-double-left"></i></a>
				</c:if>
			</li>
			<li
				class="page-item ${not pagina.getPage().hasPrevious() ? 'page-item disabled' : 'page-item'}">
				<c:if test="${not pagina.getPage().hasPrevious()}">
					<a class="page-link"><i class="fa fa-angle-left"></i></a>
				</c:if> <c:if test="${pagina.getPage().hasPrevious()}">
					<a class="page-link"
						href="${pageContext.request.contextPath}/${pagina.url}?pageNo=${pagina.paginaActual-2}"><i
						class="fa fa-angle-left"></i></a>
				</c:if>
			</li>

			<c:forEach var="item" items="${pagina.paginas}">
				<li
					class="page-item ${item.actual ? 'page-item active' : 'page-item'}">
					<c:if test="${item.actual}">
						<a class="page-link">${item.numeroPagina}</a>
					</c:if> <c:if test="${not item.actual}">
						<a class="page-link"
							href="${pageContext.request.contextPath}/${pagina.url}?pageNo=${item.numeroPagina-1}">${item.numeroPagina}</a>
					</c:if>
				</li>
			</c:forEach>

			<li
				class="page-item ${not pagina.getPage().hasNext() ? 'page-item disabled' : 'page-item'}">
				<c:if test="${not pagina.getPage().hasNext()}">
					<a class="page-link"><i class="fa fa-angle-right"></i></a>
				</c:if> <c:if test="${pagina.getPage().hasNext()}">
					<a class="page-link"
						href="${pageContext.request.contextPath}/${pagina.url}?pageNo=${pagina.paginaActual}"><i
						class="fa fa-angle-right"></i></a>
				</c:if>
			</li>
			<li
				class="page-item ${pagina.page.last ? 'page-item disabled' : 'page-item'}">
				<c:if test="${pagina.page.last}">
					<a class="page-link"><i class="fa fa-angle-double-right"></i></a>
				</c:if> <c:if test="${not pagina.page.last}">
					<a class="page-link"
						href="${pageContext.request.contextPath}/${pagina.url}?pageNo=${pagina.totalPaginas-1} "><i
						class="fa fa-angle-double-right"></i></a>
				</c:if>
			</li>
		</ul>
	</div>
</nav>

