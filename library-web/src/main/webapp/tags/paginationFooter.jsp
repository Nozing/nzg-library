<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- 
searchResult.initial > param.initial
searchResult.pageSize > param.pageSize
searchResult.total > param.total
-->

<div class="fixed-table-pagination" style="display: block;">
	<div class="pull-left pagination-detail">
		<span class="pagination-info"> 
			<c:set var="lowLimit" value="${(searchResult.initial - 1) * searchResult.pageSize}" /> 
			<c:set var="maxLimit" value="${lowLimit + searchResult.pageSize}" /> 
			<c:if test="${maxLimit gt searchResult.total }">
				<c:set var="maxLimit" value="${searchResult.total}" />
			</c:if> 
			<spring:message code="pagination.resume">
				<spring:argument value="${lowLimit + 1}" />
				<spring:argument value="${maxLimit}" />
				<spring:argument value="${searchResult.total}" />
			</spring:message>
		</span> <span class="page-list"> <span class="btn-group dropup">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span class="page-size"><c:out
							value="${searchForm.pageSize }" /></span> <span class="caret" />
				</button>
				<ul class="dropdown-menu">

					<c:forEach items="${searchForm.pagesSize }" var="step">
						<c:choose>
							<c:when test="${searchForm.pageSize == step}">
								<li class="active"><a href="<c:out value="${step}"/>"><c:out
											value="${step}" /></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="<c:out value="${step}"/>"><c:out
											value="${step}" /></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
		</span> <spring:message code="pagination.rowsPerPage" /></span>
	</div>
	<div class="pull-right pagination">
		<ul class="pagination">

			<c:set var="numberOfPages"
				value="${searchResult.total / searchForm.pageSize}" />
			<fmt:formatNumber var="numberOfPages"
				value="${numberOfPages + (numberOfPages % 1 == 0 ? 0 : 0.5)}"
				type="number" pattern="#" />

			<c:set var="isDisabled" value="" />
			<c:if test="${searchForm.initial == 1 }">
				<c:set var="isDisabled" value="disabled" />
			</c:if>
			<li class="page-first ${isDisabled }"><a href="1">&lt;&lt;</a></li>

			<c:set var="isDisabled" value="" />
			<c:if test="${searchForm.initial - 1 <= 0 }">
				<c:set var="isDisabled" value="disabled" />
			</c:if>
			<li class="page-prev ${isDisabled }"><a
				href="${searchForm.initial - 1}">&lt;</a></li>

			<c:if test="${searchForm.initial > 2 }">
				<li class="page-last-separator disabled"><a href="#">...</a></li>
			</c:if>

			<c:if test="${searchForm.initial - 1 > 0 }">
				<li class="page-number"><a href="${searchForm.initial - 1}">${searchForm.initial
						- 1}</a></li>
			</c:if>

			<li class="page-number active"><a href="${searchForm.initial }">${searchForm.initial
					}</a></li>

			<c:if test="${searchForm.initial + 1 <= numberOfPages }">
				<li class="page-number"><a href="${searchForm.initial + 1}">${searchForm.initial
						+ 1}</a></li>
			</c:if>

			<c:if test="${searchForm.initial + 1 < numberOfPages }">
				<li class="page-last-separator disabled"><a href="#">...</a></li>
			</c:if>

			<c:set var="isDisabled" value="" />
			<c:if test="${searchForm.initial + 1 > numberOfPages }">
				<c:set var="isDisabled" value="disabled" />
			</c:if>
			<li class="page-next ${isDisabled }"><a
				href="${searchForm.initial + 1}">&gt;</a></li>

			<c:set var="isDisabled" value="" />
			<c:if test="${searchForm.initial == numberOfPages }">
				<c:set var="isDisabled" value="disabled" />
			</c:if>
			<li class="page-last ${isDisabled }"><a href="${numberOfPages }">&gt;&gt;</a>
			</li>
		</ul>
	</div>
</div>