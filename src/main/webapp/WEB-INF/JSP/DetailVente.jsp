
<html>
<%@ include file="/WEB-INF/JSP/Fragments/headerCo.jspf"%>
<title>Enchère</title>




<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body style="text-align:center">
	<h1>Détail vente</h1>

	<div>
		<p>${requestScope.nomArticle}</p>
		<p>Description : ${requestScope.description }</p>
		<p>Catégorie : ${requestScope.categorie}</p>
		<p>meilleure offre : ${requestScope.prixVente} par ${requestScope.numEnchere}</p>
		<p>mise a prix :${requestScope.miseAPrixStr}</p>
		<p>fin de l'enchère : ${requestScope.dateFinEncheresStr}</p>
		<p>
			retrait : ${requestScope.rue} ${requestScope.codePostal}
			${requestScope.ville}
		</p>
		<p>vendeur : ${requestScope.pseudo}</p>
	</div>
	<form action="${pageContext.request.contextPath}/detailVente"
		method="post">
		<p>Ma proposition :</p>
		<input type="number" name="enchere"> <input type="submit"
			value="Encherir">
		<input type="hidden" value="${requestScope.noArticle}" name="noArticle">
	</form>
	<p>${error}</p>
	<p>${requestScope.test}</p>
	


</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>
</html>