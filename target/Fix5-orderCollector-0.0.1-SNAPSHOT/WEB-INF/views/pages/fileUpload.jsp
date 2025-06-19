<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div>
	
		<h3>Télécharger le fichiers des instruments BVMT</h3>
	
		<form:form method="POST" action="${pageContext.request.contextPath}/fileUpload" enctype="multipart/form-data">
			
			<table class="">
				<tr>
					<td>Selectionner le fichier à uploader</td>
					<td><input type="file" name="file" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
			
		</form:form>
		
		<br /> 
	
	</div>

