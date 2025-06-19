<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:getAsString name="title" /></title>
<link href="<c:url value='/resources/site.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/bootstrap.css' />" rel="stylesheet" ></link>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>

<body>
<div class="container">
	 <tiles:insertAttribute name="menu" />
	 <article class="article">
		<tiles:insertAttribute name="body" />
        </article>
    
    <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>
