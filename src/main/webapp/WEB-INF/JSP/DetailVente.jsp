<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/JSP/Fragments/headerCo.jspf"%>
	<title>Ecnhère</title>
</head>
<meta charset="UTF-8">

<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
	<h1>Détail vente</h1>
	<form action="${pageContext.request.contextPath}/detailVente" method="get">
	<div class="col-3">
		<p>  ${sessionScope.article.nomArticle}</p>
		<p>Description : ${sessionScope.article.description}</p>
		<p>Catégorie : ${sessionScope.article.libelle}</p>
		<p>meilleure offre : ${sessionScope.article.prixVente}</p>
		<p>mise a prix : ${sessionScope.article.miseAPrix}</p>
		<p>fin de l'enchère : ${sessionScope.article.dateFinEncheres}</p>
		<p>retrait : ${sessionScope.retrait.rue}<br>${sessionScope.retrait.codePostal}${sessionScope.retrait.ville}</p>
		<p>vendeur : ${requestScope.utilisateur.pseudo}</p>					
	</div> 
	</form>	


</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>
</html>