<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier profil</title>
</head>
<body>
	<h1>Mon profil</h1>
	<div class=container>
		<form action="${pageContext.request.contextPath}/modificationProfil" method="post">
			<div class="row">	
				<div class="col-6">
					<label>Pseudo:</label>
					<input type="text" name="pseudo" value="${requestScope.pseudo}">
				</div>
					
				<div class="col-6">
					<label>Nom:</label>
					<input type="text" name="nom"  value="${requestScope.nom}">
				</div>
				
				<div class="col-6">	
				<label>Prénom:</label>
					<input type="text" name="Prenom"  value="${requestScope.prenom}">
				</div>
				
				<div class="col-6">
					
					<label>Email:</label>
					<input type="text" name="email"  value="${requestScope.email}">
				</div>
				
				<div class="col-6">
					
					<label>Téléphone:</label>
					<input type="text" name="telephone"  value="${requestScope.tel}">
				</div>
				
				<div class="col-6">
					
					<label>rue:</label>
					<input type="text" name="rue"  value="${requestScope.rue}">
				</div>
				
				<div class="col-6">
					
					<label>Code postal:</label>
					<input type="text" name="codePostal"  value="${requestScope.cp}">
				</div>
				
				<div class="col-6">
					
					<label>ville:</label>
					<input type="text" name="ville"  value="${requestScope.ville}">
				</div>
				
				<div class="col-6">
					<label>Mot de passe actuel:</label>
					<input type="password" name=motDePasseActuel>
				</div>	
				
				<div class="col-6">
					<label>Nouveau mot de passe:</label>
					<input type="password" name="nouveauMDP" >
				</div>
				
				<div class="col-6">
					<label>Confirmation:</label>
					<input type="password" name="confirmationMDP">
				</div>
					
				<div class="col-6">
				<input name="credit" type="text" value = "${sessionScope.credit}">
			</div>
			<form action="${pageContext.request.contextPath}/supprimerProfil" method="post">
			<div class="col-6">
				<input type="submit" value = "Supprimer mon compte">
			</div>
			</form>
			<div class="col-6">
				<input type="submit" value = "enregistrer">
			</div>
		</form>
		<c:if test="${requestScope.exist == 1 }">
			<p>Le pseudo est déjà utilisé, veuillez en choisir un autre</p>
		</c:if>
	</div>
</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>

</html>