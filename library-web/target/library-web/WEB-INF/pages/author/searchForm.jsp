<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row col-md-12" id="searchForm">

	<h3>_Author search form</h3>
	
	<c:url var="searchAuthorUrl" value="${searchAuthorAction}" />
	<form:form action="${searchAuthorUrl}" commandName="searchForm"
		method="POST" cssClass=".form-inline">
		
		<form:hidden path="total"/>
		<form:hidden path="initial"/>
		<form:hidden path="pageSize"/>
		
		<div class="formCriterias">
			<div class="col-sm-6 form-group">
				<form:label path="name" >_name:</form:label>
				<form:input	cssClass="form-control" path="name" id="nameId"/>
			</div>
			<div class="col-sm-6 form-group">
				<form:label path="surname" >_surname:</form:label>
				<form:input	cssClass="form-control" path="surname" id="surnameId"/>
			</div>
		</div>
		
		<div class="formActions">
			<form:button class="btn btn-default clean">_Clean</form:button> 
			<form:button class="btn btn-primary search">_Search</form:button>
		</div>
	</form:form>
</div>

<div class="row col-md-12 searchResults">

	<table class="table table-condensed">
		<thead>
			<tr>
				<th>_name</th>
				<th>_surname</th>
				<th>_actions</th>
			<tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty searchResult.result}">
				
					_Showing results from ${searchResult.initial } to ${searchResult.initial + searchResult.pageSize} of ${searchResult.total }
				
					<c:forEach items="${searchResult.result }" var="author">
						<tr>
							<td>${author.name}</td>
							<td>${author.surname }</td>
							<td>
								<ul>
									<li>
										<c:url var="viewAuthorDetailUrl" value="${viewAuthorDetailAction }">
											<c:param name="id">${author.authorId }</c:param>
										</c:url>
										<a href="${viewAuthorDetailUrl }">
											<span class="sr-only">_Detail</span>
											<i class="glyphicon glyphicon-eye-open">_Detail</i>
										</a>
									</li>
									
									<li>
										<c:url var="removeAuthorlUrl" value="${removeAuthorAction }">
											<c:param name="id">${author.authorId }</c:param>
										</c:url>
										<a href="${removeAuthorlUrl }">
											<span class="sr-only">_Remove</span>
											<i class="glyphicon glyphicon-remove"></i>
										</a>
									</li>
								</ul>
							</td>
						<tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3">_No results</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>