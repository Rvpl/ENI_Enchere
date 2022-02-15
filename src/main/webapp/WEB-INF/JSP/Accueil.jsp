<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
<c:if test="${sessionScope.utilisateur == null }">
		<%@ include file="/WEB-INF/JSP/Fragments/headerDeco.jspf"%>
	</c:if>
	<c:if test="${sessionScope.utilisateur != null }">
		<%@ include file="/WEB-INF/JSP/Fragments/headerCo.jspf"%>
	</c:if>
	
	<div class="container">
	

	<h2 class="title">Liste des enchères</h2>
	<form action="${pageContext.request.contextPath}/home" class="filters" method="post">
		<label for="name">Filtres : </label>
		<fieldset>
			<input type="text" name="filtre" id="filtre" required>	
		</fieldset>
		<input type="submit" value="Rechercher">
	</form>
	
	<c:if test="${requestScope.articles != null}">
		<c:if test="${ requestScope.articlesBN == null}">
		<div class = "container">
			<div class="row">
			<c:forEach var="article" items="${requestScope.articles}" >
			 	<div class="col-3">
			 			<p>${article.nomArticle }</p>
				 		<p>prix : ${article.prixVente }</p>
				 		<p>Fin de l'enchère : ${article.dateFinEncheres }</p>
				 		<p>Vendeur : ${article.noUtilisateur  }</p>
			 		</div> 	
		 		</c:forEach>
		 	</div>
		</div>
		
		</c:if>	
	</c:if>
	
	<c:if test="${requestScope.articlesBN != null}">
	<div class="row">
		<c:forEach var="articleBN" items="${requestScope.articlesBN}" >
		 	<div class="col-3">
		 		<p>${articleBN.nomArticle }</p>
		 		<p>prix : ${articleBN.prixVente }</p>
		 		<p>Fin de l'enchère : ${articleBN.dateFinEncheres }</p>
		 		<p>Vendeur : ${articleBN.noUtilisateur  }</p>
		 	</div>
	 	</c:forEach>
	</div>
		
	</c:if>
	</div>
 
</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>

</html>

