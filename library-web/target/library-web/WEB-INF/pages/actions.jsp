<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- BOOK -->
<c:set var="searchBookAction" scope="request" value="/book/searchForm.htm" />

<c:set var="createFormAction" scope="request" value="/book/insertForm.htm" />
<c:set var="createBookAction" scope="request" value="/book/insertBook.htm" />

<c:set var="updateFormAction" scope="request" value="/book/updateForm.htm" />

<c:set var="viewBookDetailAction" scope="request" value="/book/viewBookDetail.htm" />

<c:set var="removeBookAjaxAction" scope="request" value="/ajax/book/remove.do" />

<c:set var="updateBookAjaxAction" scope="request" value="/ajax/book/update.do" />

<!-- AUTHOR -->
<c:set var="createAuthorFormAction" scope="request" value="/author/createAuhtor.htm" />
<c:set var="createAuthorAction" scope="request" value="/author/createAuhtorAction.htm" />

<c:set var="searchAuthor" scope="request" value="/author/searchAuthors.htm" />
<c:set var="searchAuthorAction" scope="request" value="/author/searchAuthorsAction.htm" />

<c:set var="updateAuthorAction" scope="request" value="/author/updateAuthorAction.htm" />

<c:set var="viewAuthorDetailAction" scope="request" value="/author/viewAuhtorDetails.htm" />

<c:set var="removeAuthorAjaxAction" scope="request" value="/ajax/author/remove" />

<!-- ABOUT -->
<c:set var="aboutAction" scope="request" value="/about.htm" />