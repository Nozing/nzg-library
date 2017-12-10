<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="insertForm">

	<h3><spring:message code="book.create.title" /></h3>
	
	<c:url var="insertBookUrl" value="${createBookAction}" />
	<form:form action="${insertBookUrl}" commandName="insertForm"
		method="POST" cssClass=".form-inline">
		
		<div class="formCriterias">
			<div class="col-sm-12 form-group">
				<form:label path="title" ><spring:message code="book.create.form.title" /></form:label>
				<form:input	cssClass="form-control" path="title" id="titleId"/>
			</div>
			
			<div class="form-group col-sm-12">
				<form:label path="authors"><spring:message code="book.create.form.authors" htmlEscape="false"/></form:label>	
				<div class="col-sm-12 authorContainer">
					<input type="hidden" name="index" value="0" />
					<form:hidden cssClass="authorId" path="authors[0].id" />
					<div class="col-sm-5 form-group">	
						<label for="name" ><spring:message code="book.create.form.author.name" /></label>
						<input class="col-sm-6 form-control authorName" name="authors[0].name" />
					</div>
					
					<div class="col-sm-5 form-group">
						<label for="surname" ><spring:message code="book.create.form.author.surname" /></label>
						<input class="col-sm-6 form-control authorSurname" name="authors[0].surname" />
					</div>
					<div class="col-sm-1 form-group">
						<c:url var="searchAuthorUrl" value="${searchAuthorAction}" />
						<a href="${searchAuthorUrl }" class="addAuthorAction"><span class="	glyphicon glyphicon-plus"></span></a>
					</div>
				</div>
			</div>
			<div class="form-group col-sm-6">
				<form:label path="category"><spring:message code="book.create.form.category" /></form:label>
				<form:input cssClass="form-control" path="category" id="categoryId" />
			</div>
			<div class="form-group col-sm-6">
				<form:label path="location"><spring:message code="book.create.form.location" /></form:label>
				<form:input cssClass="form-control" path="location" id="locationId" />	
			</div>
			<div class="col-sm-12 form-group">
				<form:label path="observations" ><spring:message code="book.create.form.observations" /></form:label>
				<form:input	cssClass="form-control" path="observations" id="observationsId"/>
			</div>
		</div>
		
		<div class="formActions">
			<form:button class="btn btn-default clean"><span class="glyphicon glyphicon-remove"></span> <spring:message code="button.clean" /></form:button> 
			<form:button class="btn btn-primary create"><span class="glyphicon glyphicon-floppy-save"></span> <spring:message code="button.save" /></form:button>
		</div>
	</form:form>
</div>

<script type="text/javascript">

var addAuthorAction = $('a.addAuthorAction');
if (addAuthorAction) {
	
	addAuthorAction.click(function(ev) {
	
		if (ev) ev.preventDefault();
		
		var paramName = addAuthorAction.parent().parent().find('input.authorName').val();
		var paramSurname = addAuthorAction.parent().parent().find('input.authorSurname').val()
		
		var params = { 'name' : paramName, 'surname' : paramSurname };
		
		/* console.log(params);
		
		$.ajax({
			'url' : addAuthorAction.attr('href'),
			'method' : 'GET',
			'data' : params,
			'dataType' : 'html',
			'complete' : function (jqXHR, textStatus) {
				console.info('createFrom.ajax.complete> ' + textStatus);
			},
			'success' : function (data, textStatus, jqXHR) {
				console.info('createFrom.ajax.success> ' + data);
			},
			'error' : function(jqXHR, textStatus, errorThrown) {
				console.info('createFrom.ajax.error> ' + errorThrown);
			}
		});*/
		
		/* Retrieves field forms for adding author */
		var fieldsContainer = addAuthorAction.parents(".authorContainer");
		
		var originalFields = fieldsContainer.first();
		
		/* Gets the original fields filled for the user for adding them
		   later */
		var fieldsAdded = originalFields.clone();
		
		var indexField = originalFields.find("input[name=index]");
		var indexFieldValue = indexField.val();
		indexField.val(parseInt(indexFieldValue) + 1);
		
		var authorIdField = originalFields.find("input.authorId");
		
		var fieldValue = authorIdField.attr("name");
		console.log(fieldValue);
		authorIdField.attr("name", 
			fieldValue.replace("[" + indexFieldValue + "]", 
				"[" + (parseInt(indexFieldValue) + 1) + "]"));
		authorIdField.val("");
		
		var authorNameField = originalFields.find("input.authorName");
		fieldValue = authorNameField.attr("name");
		
		authorNameField.attr("name", 
			fieldValue.replace("[" + indexFieldValue + "]", 
				"[" + (parseInt(indexFieldValue) + 1) + "]"));
		authorNameField.val("");
		
		var authorSurnameField = originalFields.find('input.authorSurname')
		fieldValue = authorSurnameField.attr("name");
		
		authorSurnameField.attr("name", 
				fieldValue.replace("[" + indexFieldValue + "]", 
					"[" + (parseInt(indexFieldValue) + 1) + "]"));
		authorSurnameField.val("");
		
		/* Change 'add' link to 'remove' link. It will remove button
		   class adding the properly and configure the link's behaviour */
		var addLink = fieldsAdded.find('a.addAuthorAction');
		var icon = addLink.find('.glyphicon-plus');
		if (icon) {
			
			icon.removeClass('glyphicon-plus');
			icon.addClass('glyphicon-minus');
		}
		addLink.removeClass('addAuthorAction');
		addLink.addClass('removeAuthorAction');
		addLink.click(function (ev) {
			
			if (ev) ev.preventDefault();
			
			$(this).parents('.authorContainer').remove();
		});
		
		fieldsContainer.after(fieldsAdded);
	});
}

/* Configures autocomplete of 'name' and 'surname' fields. When the user is 
 * writing, we are going to search the authors and show them as an advice to
 * use it. If user clicks on one of the options we have shown, we will fill all
 * the fields (name, surname and id) */
var formFields = $('.authorContainer .form-control');
formFields.each(function (index, el) {
	console.log(el);
});

</script>