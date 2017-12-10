<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="/WEB-INF/pages/actions.jsp" />

<div>
	<div class="col-xs-12 col-md-8">
		<ul class="navigation">
			<li>
				<c:url var="searchBookUrl" value="${searchBookAction }" />
				<a href="${searchBookUrl }"> 
					<%-- <spring:message code="menu.item.search" />--%>
					menu.item.book.search
				</a>
			</li>

			<li>
				<c:url var="createBookUrl" value="${createFormAction }" />
				<a href="${createBookUrl }"> 
					<%-- <spring:message code="menu.item.create" /> --%>
					menu.item.book.create
				</a>
			</li>
			<li>
				<c:url var="searchAuthorsUrl" value="${searchAuthor }" />
				<a href="${searchAuthorsUrl }">
					menu.item.author.search
				</a>
			</li>
			<li>
				<c:url var="createAuthorUrl" value="${createAuthorFormAction }" />
				<a href="${createAuthorUrl }">
					menu.item.author.create
				</a>
			</li>
			<li>
				<c:url var="aboutUrl" value="${aboutAction }" />
				<a href="${aboutUrl }">
					<%-- <spring:message code="menu.item.about" /> --%>
					menu.item.about
				</a>
			</li>
		</ul>
	</div>
</div>