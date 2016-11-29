<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kirjan lainaus</title>
</head>
<body>
	<section data-role="page">
	<div data-role="header">
		<h1>Kirjan lainaus järjestelmä</h1>
	</div>
	<div class="ui-content" data-role="ui-grid-b">
		<c:forEach var="numero" items="${listNumerot}">
			<div class="ui-block-a">
				<c:out value="${numero.numero}" />
				<button class="ui-btn">lisätiedot</button>
			</div>
		</c:forEach>
	</div>
	<div data-role="footer" class="ui-grid-a">
			<div class="ui-block-a">
				<button class="ui-btn"><span data-icon="bars"></span></button>
			</div>
			<div class="ui-block-b">
				<button class="ui-btn"></button>
			</div>
	</div>
	</section>
</body>
</html>