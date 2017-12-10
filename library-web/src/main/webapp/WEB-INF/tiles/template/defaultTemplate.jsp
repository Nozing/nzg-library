<!DOCTYPE HTML>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
	<head>
		<title><tiles:insertAttribute name="title" ignore="true"/></title>
		
		<meta charset="utf-8">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="<tiles:insertAttribute name="page_description" ignore="true"/>">
		
		<c:url var="contextUrl" value="/" />
		<link rel="shortcut icon" href="${contextUrl}favicon.ico" />
		<link rel="stylesheet" type="text/css" href="${contextUrl}css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${contextUrl}css/main.css">
		
		<script src="${contextUrl}js/lib/jquery/jquery-2.1.4.min.js"></script>
		<script src="${contextUrl}js/lib/bootstrap/bootstrap.min.js"></script>
		
		<script src="${contextUrl}js/common/libWeb.util.form.js"></script>
		<script src="${contextUrl}js/common/libWeb.util.pagination.js"></script>
		<script src="${contextUrl}js/common/libWeb.util.ajax.js"></script>
		<script src="${contextUrl}js/common/libWeb.view.loading.js"></script>
		<script src="${contextUrl}js/common/libWeb.view.popup.js"></script>
		
	</head>
    <body>
    	<div class="container">
	    	<div id="header_wrapper">
				<tiles:insertAttribute name="header" />
			</div>
			
			<div class="container" id="page">
				<tiles:insertAttribute name="content" />
			</div>
			
			<div id="footer_wrapper">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</body>
</html>