<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Vous avez remporté la vente!</h1>
		<p>${requestScope.nomArticle}</p>
		<p>Description : ${requestScope.description }</p>
		<p>Catégorie : ${requestScope.categorie}</p>
		<p>meilleure offre : ${requestScope.prixVente} par ${requestScope.pseudo }</p>
		<p>mise a prix :${requestScope.miseAPrixStr}</p>
		<p>
			retrait : ${requestScope.rue} <br>${requestScope.codePostal}
			${requestScope.ville}
		</p>
		<p>vendeur : ${requestScope.pseudo}</p>
		<p>tel: ${requestScope.tel}<p>
	 <form action="${pageContext.request.contextPath}/Acquisition" method="post">
	 <button type="submit" name="back">back</button>
	</form>								
</body>
</html>