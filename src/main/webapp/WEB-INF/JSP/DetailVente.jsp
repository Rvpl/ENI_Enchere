<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/JSP/Fragments/headerCo.jspf"%>
<title>Enchère</title>
</head>
<meta charset="UTF-8">



<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<h1>Détail vente</h1>

	<div class="col-3">
		<p>${requestScope.nomArticle}</p>
		<p>Description : ${requestScope.description }</p>
		<p>Catégorie : ${requestScope.categorie}</p>
		<p>meilleure offre : ${requestScope.prixVente}</p>
		<p>mise a prix :${requestScope.miseAPrixStr}</p>
		<p>fin de l'enchère : ${requestScope.dateFinEncheresStr}</p>
		<p>
			retrait : ${requestScope.rue} <br>${requestScope.codePostal}
			${requestScope.ville}
		</p>
		<p>vendeur : ${requestScope.pseudo}</p>
	</div>
	<form action="${pageContext.request.contextPath}/detailVente" method="post">
			<p>Ma proposition :</p>
			<input type="number" name="enchere"> 
			<input type="submit" value="Encherir">
			<p>${requestScope.message}</p>	
			<p>${requestScope.error}</p>
			<input type="hidden" value="${requestScope.noArticle}"	name="noArticle">	
	</form>
	

</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>
</html>