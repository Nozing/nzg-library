<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<div id="searchForm">

	<h3>_Book details</h3>
										
	<form:form action="" commandName="bookDetailsForm" 
		method="POST"
		cssClass=".form-inline" id="detailsFormId">
	
		<form:hidden path="id" id="bookId"/>
		
		<div class="formCriterias">
		
			<div class="form-group col-sm-12">
				<form:label path="title">_title:</form:label> 
				<form:input	cssClass="form-control" path="title" id="titleId"/>
			</div>
			
			<div class="form-group col-sm-12">
				<form:label path="authors">_authors:</form:label>
				<c:forEach items="${bookDetailsForm.authors }" var="author" varStatus="status">
					<input type="hidden" name="authors[${status.index }].authorId"
						value="${author.authorId }" />
					<div class="col-sm-12">
						<div class="col-sm-5 form-group">
							<label for="author.name" >_name</label>
							<input type="text" class="form-control" value="${author.name}"
								name="authors[${status.index }].name" /> 
						</div>
						<div class="col-sm-5 form-group">
							<label for="author.surname" >_surname</label>
							<input type="text" class="form-control" value="${author.surname}"
								name="authors[${status.index }].surname" />
						</div>
						<div class="col-sm-1">
							<a href="${author.authorId }"><span class="glyphicon glyphicon-trash"></span></a>
						</div>
					</div>
				</c:forEach>
			</div>
				<div class="col-sm-6">
					<form:label path="category">_category:</form:label>
					<form:input cssClass="form-control" path="category" id="categoryId" />
				</div>
				<div class="col-sm-6">
					<form:label path="location">_location:</form:label>
					<form:input cssClass="form-control" path="location" id="locationId" />	
				</div>
			
			<div class="form-group col-sm-12">
				<form:label path="note">_note:</form:label>
				<form:input cssClass="form-control" path="note" id="noteId" />
			</div>
			<dl class="form-group col-sm-12">
				<dt>_creation date:</dt>
				<dd><c:out value="${bookDetailsForm.creationDate }" /></dd>
			</dl>
		</div>
		
		<div class="formActions">
			<form:button class="btn btn-primary updateBtn"><span class="glyphicon glyphicon-refresh"></span> _Update</form:button>
			<form:button class="btn btn-primary deleteBtn"><span class="glyphicon glyphicon-trash"></span> _Delete</form:button>
		</div>
	</form:form>
</div>
<script type="text/javascript">

$(document).ready(function(){

	var formEl = $('#detailsFormId');
	if (formEl) {
		
		formEl.find('.deleteBtn').click(function (evt) {
	
			if (evt) evt.preventDefault();
			
			<c:url var="deleteAction" value="${removeBookAjaxAction}" >
				<c:param name="id" value="${bookDetailsForm.id}" />
			</c:url>
			AjaxUtil.DELETE({
				url : '<c:out value="${deleteAction}" />',
				success : function (data, textStatus, response) {
					
					var popup = LibWebPopUpBuilder.accept(
						{ 
							'message' : data.message 
						}, { 
							onClick : function (evt) {
								
								if (evt) evt.preventDefault(); 
								
								<c:url var="searchBookForm" value="${searchBookAction}" />
								document.location.href = '<c:out value="${searchBookForm}" />';
							}
						});

					popup.show();
				}
			});			
		});
		
		formEl.find('.updateBtn').click(function (evt) {
			
			if (evt) evt.preventDefault();
			
			<c:url var="updateAction" value="${updateBookAjaxAction}" />
			AjaxUtil.POST({
				url : '<c:out value="${updateAction}"/>',
				data : formEl.serialize(),
				success : function (data, textStatus, response) {
					
					var popup = LibWebPopUpBuilder.accept(
						{ 
							'message' : data.message 
						}, { 
							onClick : function (evt) {
								
								if (evt) evt.preventDefault(); 
								
								location.reload();
							}
						});

					popup.show();
				}
			});
		});
	}
});
</script>