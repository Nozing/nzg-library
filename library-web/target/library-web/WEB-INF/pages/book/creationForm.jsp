<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="insertForm">

	<h3>_Book creation form</h3>
	
	<c:url var="insertBookUrl" value="${createBookAction}" />
	<form:form action="${insertBookUrl}" commandName="insertForm"
		method="POST" cssClass=".form-inline">
		
		<div class="formCriterias">
			<div class="col-sm-12 form-group">
				<form:label path="title" >_title:</form:label>
				<form:input	cssClass="form-control" path="title" id="titleId"/>
			</div>
			
			<div class="authorContainer">
				<div class="col-sm-12">
					<input type="hidden" name="index" value="0" />
					<form:hidden cssClass="authorId" path="authors[0].id" />
					<div class="col-sm-5 form-group">	
						<label for="name" >_name</label>
						<input class="col-sm-6 form-control authorName" name="authors[0].name" />
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="surname" >_surname</label>
						<input class="col-sm-6 form-control authorSurname" name="authors[0].surname" />
					</div>
					<div class="col-sm-1 form-group">
						
						<c:url var="searchAuthorUrl" value="${searchAuthorAction}" />
						<a href="${searchAuthorUrl }" class="addAuthorAction">_add</a>
					</div>
				</div>
			</div>
			
			<div class="col-sm-12 form-group">
				<form:label path="observations" >_observations:</form:label>
				<form:input	cssClass="form-control" path="observations" id="observationsId"/>
			</div>
		</div>
		
		<div class="formActions">
			<form:button class="btn btn-default clean">_Clean</form:button> 
			<form:button class="btn btn-primary search">_Create</form:button>
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
		addLink.text('_remove');
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