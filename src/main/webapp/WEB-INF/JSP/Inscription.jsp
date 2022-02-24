<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
<%@ include file="/WEB-INF/JSP/Fragments/headerDeco.jspf"%>
</head>
<body>
	<h2 class="display-4 text-center mt-3">Création du profil</h2>
	<div class="container fs-5 rounded mt-5 w-50 p-3">
		<form action="${pageContext.request.contextPath}/inscription"
			method="post">
			<div class="row">
				<div class="col-12" style="text-align:center; color:red;">
					<p>${requestScope.error}</p>
				</div>
				<div class="col-6">
					<label>Pseudo :</label> <input type="text" name="pseudo"
						value="${requestScope.pseudo}" class="form-control">
				</div>
				<div class="col-6">
					<label>Nom :</label> <input type="text" name="nom"
						value="${requestScope.nom}" class="form-control">
				</div>
			</div>
			<div class="row">
				<div class="col-6">
					<label>Prénom :</label> <input type="text" name="Prenom"
						value="${requestScope.prenom}" class="form-control">
				</div>
				<div class="col-6">
					<label>Email :</label> <input type="email" name="email"
						value="${requestScope.email}" class="form-control">
				</div>
			</div>
			<div class="row">
				<div class="col-6">
					<label>Téléphone :</label> <input type="text" name="telephone"
						value="${requestScope.tel}" class="form-control">
				</div>
				<div class="col-6">
					<label>rue :</label> <input type="text" name="rue"
						value="${requestScope.rue}" class="form-control">
				</div>
			</div>
			<div class="row">
				<div class="col-6">
					<label>Code postal :</label> <input type="text" name="codePostal"
						value="${requestScope.cp}" class="form-control">
				</div>
				<div class="col-6">
					<label>ville :</label> <input type="text" name="ville"
						value="${requestScope.ville}" class="form-control">
				</div>
			</div>
			<div class="row">
				<div class="col-6">
					<label>Mot de passe :</label> <input type="password"
						name="motDePasse" class="form-control">
				</div>
				<div class="col-6">
					<label>Confirmation :</label> <input type="password"
						name="confirmationMDP" class="form-control">
				</div>
			</div>
			<div class="row">
				<div class="col-6 text-center ">
					<button class="btn btn-primary btn-lg mt-3  fw-bolder"
						type="submit" value="creer">Creer</button>
				</div>
				<div class="col-6 text-center ">
					<button class="btn btn-warning btn-lg mt-3">
						<a class="link-light text-decoration-none fw-bold"
							href="${pageContext.request.contextPath}/home">Annuler</a>
					</button>
				</div>
			</div>
		</form>
		<c:if test="${requestScope.exist == 1 }">
			<p>Le pseudo est déjà utilisé, veuillez en choisir un autre</p>
		</c:if>
		<c:if test="${requestScope.message == 1 }">
			<p>Le pseudo est déjà utilisé, veuillez en choisir un autre</p>
		</c:if>
	</div>
</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>

</html>