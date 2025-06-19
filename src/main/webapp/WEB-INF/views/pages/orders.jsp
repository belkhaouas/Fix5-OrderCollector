<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Gestion des Titres</title>
		<style type="text/css">
			.error {
				color: red;
			}
			table {
				width: 50%;
				border-collapse: collapse;
				border-spacing: 0px;
			}
			table td {
				border: 1px solid #565454;
				padding: 20px;
			}
		</style>
	</head>
	<body>
		<h2>Liste des instruments</h2>
		<table>
			<tr>
				<td><strong>isin</strong></td>
				<td><strong>securityID</strong></td>
				<td><strong>securityIDSource</strong></td>
				<td><strong>emm</strong></td>
			</tr>
			<c:forEach items="${securities}" var="security">
				<tr>
					<td>${security.isin}</td>
					<td>${security.securityID}</td>
					<td>${security.securityIDSource}</td>
					<td>${security.emm}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>