<%@ include file="../../comunes/includes.jsp"%>
<thead>
	<tr>
		<c:forEach var="cpo" items="${criterios.nombreColCampos}">
			<th><c:if test="${criterios.getNombreBddCampo(cpo)==''}">
					<a>${cpo}</a>
				</c:if> <c:if test="${criterios.getNombreBddCampo(cpo)!=''}">
					<c:if test="${criterios.sortBy==criterios.getNombreBddCampo(cpo)}">
						<c:if test="${criterios.isAsc()}">
							<a
								href="${pageContext.request.contextPath}/${criterios.pagActual}?sortBy=${criterios.getNombreBddCampo(cpo)}&orderBy=DESC">${cpo}
								<i class="fa fa-sort-asc"></i>
							</a>
						</c:if>
						<c:if test="${not criterios.isAsc()}">
							<a
								href="${pageContext.request.contextPath}/${criterios.pagActual}?sortBy=${criterios.getNombreBddCampo(cpo)}&orderBy=ASC">${cpo}
								<i class="fa fa-sort-desc"></i>
							</a>
						</c:if>

					</c:if>
					<c:if test="${criterios.sortBy!=criterios.getNombreBddCampo(cpo)}">
						<a
							href="${pageContext.request.contextPath}/${criterios.pagActual}?sortBy=${criterios.getNombreBddCampo(cpo)}">${cpo}</a>
					</c:if>
				</c:if></th>
		</c:forEach>
	</tr>
</thead>
