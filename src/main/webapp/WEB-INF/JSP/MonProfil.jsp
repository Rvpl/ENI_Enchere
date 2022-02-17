<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="UTF-8">
<title>Mon Profil</title>
<%@ include file="/WEB-INF/JSP/Fragments/headerCo.jspf"%>
<body>
	<!-- Utilisation de la session pour récupérer les informations de l'utilisateur -->
	<div class="container">
		<p>Pseudo : ${sessionScope.utilisateur.pseudo}</p>
		<p>Nom : ${sessionScope.utilisateur.nom}</p>
		<p>Prenom : ${sessionScope.utilisateur.prenom}</p>
		<p>Email : ${sessionScope.utilisateur.email}</p>
		<p>Téléphone : ${sessionScope.utilisateur.numero}</p>
		<p>Rue : ${sessionScope.utilisateur.rue}</p>
		<p>Code Postal : ${sessionScope.utilisateur.codePostal}</p>
		<p>Ville : ${sessionScope.utilisateur.ville}</p>
		
		<form action="${pageContext.request.contextPath}/modificationProfil" method="get">
			<button class="btn btn-warning">Modifier</button>
		</form>
	</div>

<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>
</body>
</html>