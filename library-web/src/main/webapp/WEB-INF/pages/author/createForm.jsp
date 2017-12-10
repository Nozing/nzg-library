<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="insertForm">

	<div class="titleForm">
		_Author creation form
	</div>
	
	<c:url var="insertAuthorUrl" value="${createAuthorAction}" />
	<form:form action="${insertAuthorUrl}" commandName="createForm"
		method="POST" cssClass=".form-inline">
		
		<div class="formCriterias">
			<div class="form-group">
				<form:label path="name" >_name:</form:label>
				<form:input	cssClass="form-control" path="name" id="nameId"/>
			</div>
			<div class="form-group">
				<form:label path="surname" >_surname:</form:label>
				<form:input	cssClass="form-control" path="surname" id="surnameId"/>
			</div>
		</div>
		
		<div class="formActions">
			<form:button class="btn btn-default">_Clean</form:button> 
			<form:button class="btn btn-default">_Create</form:button>
		</div>
	</form:form>
</div>
<div id="result">

	<c:if test="${!empty result }">
		<p>Author successfully created!</p>
		
		<dt>_id:</dt>
		<dd><c:out value="${result.authorId }" /></dd>		
		
		<dt>_name:</dt>
		<dd><c:out value="${result.name }" /></dd>
		
		<dt>_surname:</dt>
		<dd><c:out value="${result.surname }" /></dd>
		
		<dt>_creationDate:</dt>
		<dd><c:out value="${result.creationDate }" /></dd>
		
		<dt>_modificationDate:</dt>
		<dd><c:out value="${result.modificationDate }" /></dd>
	</c:if>

</div>