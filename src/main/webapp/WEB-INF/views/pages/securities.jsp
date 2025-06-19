<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<br/>
	<br/>
	<h4>Liste des instruments</h4>
		<table class="table table-active">
			<tr>
				<td><strong>Isin</strong></td>
				<td><strong>Valeur</strong></td>
				<td><strong>Symbol Index</strong></td>
				<td><strong>Source</strong></td>
				<td><strong>Marché</strong></td>
			</tr>
			<c:forEach items="${securities}" var="security">
				<tr>
					<td>${security.isin}</td>
					<td>${security.stockName}</td>
					<td>${security.securityID}</td>
					<td>${security.securityIDSource}</td>
					<td>${security.emm}</td>
				</tr>
			</c:forEach>
		</table>

