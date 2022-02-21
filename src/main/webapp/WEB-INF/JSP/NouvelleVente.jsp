<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/JSP/Fragments/headerCo.jspf"%>
	<title>Nouvelle Vente</title>
</head>
<body style="text-align:center">
	<h2>Nouvelle Vente</h2>
	<div class="container">
	<form action="${pageContext.request.contextPath}/newArticle" method="post">
		<div class="row">
			<div class="col-12">
				<label>Article: </label>
				<input name="nomArticle" type="text">
			</div>
			<div class="col-12">
				<label>Description : </label>
				<textarea name="description" rows="" cols=""></textarea>
			</div>
			<div class="col-12">
				<label>Mise à prix :</label>
				<input name="prix" type="number">
			</div>
			<div class="col-12">
				<label>Début de l'enchère : </label>
				<input name="date_debut" type="date">
			</div>
			<div class="col-12">
				<label>Fin de l'enchère : </label>
				<input name="date_fin" type="date">
			</div>
			
			<h3>Retrait</h3>
			<div class="col-12">
				<label>Rue :</label>
				<input type="text" value="${sessionScope.utilisateur.rue}">
			</div>
			<div class="col-12">
				<label>Code postal :</label>
				<input type="text" value="${sessionScope.utilisateur.codePostal}">
			</div>
			<div class="col-12">
				<label>Ville:</label>
				<input type="text" value="${sessionScope.utilisateur.ville}">
			</div>
			<div class="col-12">
				<button class="btn btn-success">Enregistrer</button>
			</div>		
			<input type="hidden" name="util" value="${sessionScope.utilisateur.noUtil}">
			
			
		</div>	
	</form>
	<form action="${pageContext.request.contextPath}/home" method="get">
		<div> 
			<button class="btn btn-warning">Annuler</button>
		</div>	
	</form>
		
	</div>
	<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>
</body>
</html>