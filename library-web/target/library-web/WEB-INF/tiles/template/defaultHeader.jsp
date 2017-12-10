<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="/WEB-INF/pages/actions.jsp" />

<div class='navbar navbar-default navbar-static-top'>
	<div class="navbar-header">
		
		<c:url var="indexUrl" value="/index.htm" />
		<a href="${indexUrl }" class="navbar-brand">Nzg library</a>
	
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> 
			<span class="icon-bar"></span>
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span>
		</button>
	</div>
	
	<ul class="nav navbar-nav navbar-right collapse navbar-collapse">
		
		<li class="dropdown">
			<a href="#" data-target="#" class="dropdown-toggle" data-toggle="dropdown">
				menu.item.book <span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<c:url var="searchBookUrl" value="${searchBookAction }" />
				<li>
					<a href="${searchBookUrl }">
						menu.item.book.search
					</a>
				</li>
				<li>
					<c:url var="createBookUrl" value="${createFormAction }" />
					<a href="${createBookUrl }"> 
						menu.item.book.create
					</a>
				</li>
			</ul>
		</li>
		
		<li class="dropdown">
			<a href="#" data-target="#" class="dropdown-toggle" data-toggle="dropdown">
				menu.item.author <span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
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
			</ul>
		</li>
		
		<li>
			<c:url var="aboutUrl" value="${aboutAction }" />
			<a href="${aboutUrl }">
				menu.item.about
			</a>
		</li>
		<!-- <li class="dropdown">
			<a href="/about" data-target="#" class="dropdown-toggle" data-toggle="dropdown">
				About <span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<li><a href="#">Our Story</a></li>
				<li><a href="#">Contact Us</a></li>
				<li class="divider"></li>
				<li><a href="#">Blog</a></li>
				<li class="divider"></li>
				<li><a href="#">Twitter</a></li>
				<li><a href="#">Facebook</a></li>
			</ul>
		</li> -->
	</ul>
</div>