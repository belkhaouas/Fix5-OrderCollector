<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<br/>
	<br/>
	<h4>Etat de la connexion</h4>
		<table class="table">
			<tr class="table-active">
				<td class="col-md-10">
					${SessionOEG==1 ? "Connect�" : "D�connect�"}
				</td>
				<td class="col-md-1"><button type="button" class="btn btn-success">Connexion</button></td>
				<td class="col-md-1"><button type="button" class="btn btn-danger">D�connexion</button></td>
			</tr>
			
		</table>
