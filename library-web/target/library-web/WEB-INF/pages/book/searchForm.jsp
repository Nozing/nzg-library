<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="searchForm">

	<h3>_Book search form</h3>
	<spring:message code="menu.item.about" />
	<c:url var="searchBooskUrl" value="${searchBookAction }" />
	<form:form action="${searchBooskUrl}" commandName="searchForm" method="POST"
			cssClass=".form-inline" id="searchFormId" >
				
		<div class="formCriterias">
			<div class="col-sm-12 form-group">
				<form:label path="title">_title:</form:label> 
				<form:input	cssClass="form-control" path="title" id="titleId" />
			</div>
			<div class="col-sm-6 form-group">
				<form:label path="authorName">_authorName:</form:label>
				<form:input cssClass="form-control" path="authorName" id="authorNameId" />
			</div>
			<div class="col-sm-6 form-group">
				<form:label path="authorSurname">_authorSurname:</form:label>
				<form:input cssClass="form-control" path="authorSurname" id="authorSurnameId" />
			</div>
			<div class="col-sm-6 form-group">
				<form:label path="category">_category:</form:label>
				<form:input cssClass="form-control" path="category" id="categoryId" />
			</div>
			<div class="col-sm-6 form-group">
				<form:label path="location">_location:</form:label>
				<form:input cssClass="form-control" path="location" id="locationId" />
			</div>
		</div>
		
		<div class="formActions">
		
			<form:button class="btn btn-default clean"><span class="glyphicon glyphicon-remove"></span> _clean</form:button>
			<form:button class="btn btn-primary search"><span class="glyphicon glyphicon-search"></span> _search</form:button>
		</div>
	</form:form>
</div>

<div class="searchResults">

	<table class="table table-condensed">
		<thead>
			<tr>
				<th>_title</th>
				<th>_author</th>
				<th>_category</th>
				<th>_location</th>
				<th>_actions</th>
			<tr>
		</thead>
		<tbody>
			<c:set var="result" value="${searchResult.result }"/>
			<c:choose>
				<c:when test="${!empty result}">
					<c:forEach items="${result }" var="book">
						<tr>
							<td>${book.title}</td>
							<td>
								<c:if test="${not empty book.authors }">
									<ul>
										<c:forEach items="${book.authors }" var="author" varStatus="i">
											<li>
												<%-- <a href="${author.authorId }"> --%>
												<c:out value="${author.name }" />
												<c:if test="${not empty author.surname }">
													<c:out value="${author.surname }" />
												</c:if>
												<%-- </a> --%>
												
											</li>											
										</c:forEach>
									</ul>
								</c:if>
							</td>
							<td><c:out value="${book.category}" /></td>
							<td><c:out value="${book.location}" /></td>
							<td>
								<ul class="actions">
									<li>
										<c:url var="viewBookDetailUrl" value="${viewBookDetailAction }">
											<c:param name="id">${book.id }</c:param>
										</c:url>
										<a href="${viewBookDetailUrl }"><span class="glyphicon glyphicon-eye-open"></span></a>
									</li>
									<li>
										<c:url var="removeBookAjaxActionUrl" value="${removeBookAjaxAction }">
											<c:param name="id">${book.id }</c:param>
										</c:url>
										<a href="${removeBookAjaxActionUrl }" class="deleteBook"><span class="glyphicon glyphicon-trash"></span></a>
									</li>
								</ul>
							</td>
						<tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">_No results</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<c:if test="${!empty searchResult && !empty searchResult.result }">
		<div class="fixed-table-pagination" style="display: block;">
			<div class="pull-left pagination-detail">
				<span class="pagination-info">
					
					<c:set var="lowLimit" value="${(searchResult.initial - 1) * searchResult.pageSize}" />
					<c:set var="maxLimit" value="${lowLimit + searchResult.pageSize}" />
					<c:if test="${maxLimit gt searchResult.total }">
						<c:set var="maxLimit" value="${searchResult.total}" />
					</c:if>
					Showing ${lowLimit + 1}  to ${maxLimit} of ${searchResult.total} rows</span>
				<span class="page-list">
					<span class="btn-group dropup">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							<span class="page-size"><c:out value="${searchForm.pageSize }"/></span>
							<span class="caret"/>
						</button>
						<ul class="dropdown-menu">
						
							<c:forEach items="${searchForm.pagesSize }" var="step">	
								<c:choose>
									<c:when test="${searchForm.pageSize == step}">
										<li class="active">
											<a href="<c:out value="${step}"/>"><c:out value="${step}"/></a>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="<c:out value="${step}"/>"><c:out value="${step}"/></a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</span> rows per page</span>
			</div>
			<div class="pull-right pagination">
				<ul class="pagination">
				
					<c:set var="numberOfPages" value="${searchResult.total / searchForm.pageSize}" />
					<fmt:formatNumber var="numberOfPages" 
						value="${numberOfPages + (numberOfPages % 1 == 0 ? 0 : 0.5)}" 
   						type="number" pattern="#" />

   					<c:set var="isDisabled" value="" />
					<c:if test="${searchForm.initial == 1 }" >
						<c:set var="isDisabled" value="disabled" />
					</c:if>
   					<li class="page-first ${isDisabled }">
						<a href="1">&lt;&lt;</a>
					</li>
   					
   					<c:set var="isDisabled" value="" />
					<c:if test="${searchForm.initial - 1 <= 0 }" >
						<c:set var="isDisabled" value="disabled" />
					</c:if>
					<li class="page-prev ${isDisabled }">
						<a href="${searchForm.initial - 1}">&lt;</a>
					</li>
					
					<c:if test="${searchForm.initial > 2 }">
						<li class="page-last-separator disabled">
							<a href="#">...</a>
						</li>
					</c:if>
										
					<c:if test="${searchForm.initial - 1 > 0 }">
						<li class="page-number">
							<a href="${searchForm.initial - 1}">${searchForm.initial - 1}</a>
						</li>
					</c:if>
					
					<li class="page-number active">
						<a href="${searchForm.initial }">${searchForm.initial }</a>
					</li>
					
					<c:if test="${searchForm.initial + 1 <= numberOfPages }">
						<li class="page-number">
							<a href="${searchForm.initial + 1}">${searchForm.initial + 1}</a>
						</li>
					</c:if>
												
					<c:if test="${searchForm.initial + 1 < numberOfPages }">
						<li class="page-last-separator disabled">
							<a href="#">...</a>
						</li>
					</c:if>
					
					<c:set var="isDisabled" value="" />
					<c:if test="${searchForm.initial + 1 > numberOfPages }" >
						<c:set var="isDisabled" value="disabled" />
					</c:if>
					<li class="page-next ${isDisabled }">
						<a href="${searchForm.initial + 1}">&gt;</a>
					</li>
					
					<c:set var="isDisabled" value="" />
					<c:if test="${searchForm.initial == numberOfPages }" >
						<c:set var="isDisabled" value="disabled" />
					</c:if>
					<li class="page-last ${isDisabled }">
						<a href="${numberOfPages }">&gt;&gt;</a>
					</li>
				</ul>
			</div>
		</div>
	</c:if>
</div>

<script type="text/javascript">

$(document).ready(function(){
	
	var createForm = new $.LibWebForm($("#searchFormId"), { 'btnClean' : '.btn.clean' });
		
	$("a.deleteBook").each(function (index, item) {
		
		$(item).click(function (event) {
			
			if (event) event.preventDefault();
			
			var deleteLink = $(this);
			AjaxUtil.DELETE({
				url : deleteLink.attr('href'),
				error : function (jqXHR, textStatus, errorThrown) {
					
					alert("Error: Status (" + jqXHR.status + ") > " + errorThrown);
				},
				success : function () {
					
					deleteLink.closest("tr").remove();
				}
			});
			
			
		});
	});
	
	var formEl = $('#searchFormId');
	
	/* Setting pagination */
	var currentPage = $('li.page-number.active>a').text();
	var selectedPageSize = formEl.find('input[name="pageSize"]').attr('value');
	
	/* Sets page size in dropdown field */
	var selectedPageSizeEl = $('button>span.page-size');
	if (selectedPageSizeEl) {
		
		selectedPageSizeEl.text(selectedPageSize);
	}
	
	/* configure page size links */
	$('.page-list li').each(function(index, elem) { 
						
		 $(elem).find('a').click(function (evt) {
			
			if (evt) evt.preventDefault();
			
			/* sets form fields with values */
			configureFormForSending(formEl, currentPage, $(this).text()).submit();
		});
	});
	
	configureFormForSending = function (form, currentPage, pageSize) {
		
		$('<input>').attr({
		    type: 'text',
		    name: 'initial',
		    value: currentPage
		}).appendTo(form);
		
		$('<input>').attr({
		    type: 'text',
		    name: 'pageSize',
		    value: pageSize
		}).appendTo(form);
		
		return form;
	};
	
	/* Setting navigation links */	
	$('.pagination li[class|="page"]').click(function (evt) { 
		
		if (evt) evt.preventDefault();
		
		var link =  $(this).find('a');
		
		/* sets form fields with values */
		configureFormForSending(formEl, link.attr("href"), $('button>span.page-size').text()).submit();
	});	
});

</script>