<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="body">
	<h1>Home page !</h1>

	<p>The time on the server is ${serverTime}.</p>
	<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
		eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
		minim veniam, quis nostrud exercitation ullamco laboris nisi ut
		aliquip ex ea commodo consequat. Duis aute irure dolor in
		reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
		pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
		culpa qui officia deserunt mollit anim id est laborum.</p>
</div>

<ul>
	<c:forEach var="book" items="${books}">
		<li><c:out value="${book.title }" /></li>
	</c:forEach>
</ul>
<p>
<c:out value="${book.title }" />
</p>