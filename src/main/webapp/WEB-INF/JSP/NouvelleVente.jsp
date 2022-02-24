<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/JSP/Fragments/headerCo.jspf"%>
<title>Nouvelle Vente</title>
</head>
<body>
	<h2  class="display-4 text-center mt-3">Nouvelle Vente</h2>
	<div class="container text-center fs-5 mt-5 w-25 p-3">
		<form action="${pageContext.request.contextPath}/newArticle"
			method="post">
			<div class="form-group row mb-3">
				<label class="col-auto col-form-label w-25">Article: </label> 
				<div class="col-md-6">
					<input name="nomArticle" type="text" class="form-control">
				</div>
			</div>
			<div class="form-group row mb-3">
				<label class="col-auto col-form-label w-25">Description: </label>
				<div class="col-md-8">
					<textarea name="description" rows="" cols="" class="form-control"></textarea>
				</div>
			</div>
			<div class="form-group row mb-3">
				<label class="col-auto col-form-label w-25">Catégorie:</label> 
				<div class="col-md-6">
					<select name="choixCategorie" class="form-control">
						<option value="">Toutes</option>
						<c:forEach var="categorie" items="${requestScope.categorie}">
							<option value="${categorie}">${categorie}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group row mb-2">
				<label class="col-auto col-form-label w-25">Mise à prix:</label>
				<div class="col-md-6">
					 <input name="prix" type="number" class="form-control">
				</div>
			</div>
			<div class="form-group row mb-2">
				<label class="col-auto col-form-label w-25">Début de l'enchère:</label> 
				<div class="col-md-6">
					<input name="date_debut" type="date" class="form-control" >
				</div>
			</div>
			<div class="form-group row mb-2">
			<label class="col-auto col-form-label w-25">Fin de l'enchère:</label>
				<div class="col-md-6">
					 <input name="date_fin"type="date" class="form-control">
				</div>
			</div>
			
			
				<h3 class="display-6 text-center">Retrait</h3>
			<div class="form-group row mb-3">
				<label class="col-auto col-form-label w-25">Rue:</label>
					<div class="col-md-6">
					 	<input name="rue" type="text" value="${sessionScope.utilisateur.rue}" class="form-control">
					</div>
			</div>
			<div class="form-group row">
				<label class="col-auto col-form-label w-25">Code postal:</label> 
				<div class="col-md-6">
					<input name="codePostal" type="text" value="${sessionScope.utilisateur.codePostal}" class="form-control">
				</div>
			</div>
			<div class="form-group row mb-2">
				<label class="col-auto col-form-label w-25">Ville:</label>
				<div class="col-md-6">
					 <input name="ville" type="text"value="${sessionScope.utilisateur.ville}" class="form-control">
				</div>
			</div>
				
			<div class="form-group row mb-2">
				<div class="col-md-6 text-left">
					<button class="btn btn-primary mt-3  fw-bolder">Enregistrer</button>
				</div>
				<input type="hidden" name="util" value="${sessionScope.utilisateur.noUtil}"  >
			
						<div class="col-md-6 text-right">
							<button class="btn btn-danger mt-3 fw-bolder"><a class="link-light text-decoration-none fw-bold" href="${pageContext.request.contextPath}/home">Annuler</a></button>
						</div>	
					</div>
				
		</form>
	
	</div>
	
	<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>
</body>
</html>