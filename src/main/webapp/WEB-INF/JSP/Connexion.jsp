<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Connexion</title>
</head>
<body style="text-align:center">
<p> ENI - Enchères</p>

<form action="${pageContext.request.contextPath}/connecter" method="post">
	<label>Identifiant :</label>
	<input type="text" name="login" placeholder="Entrez vos identifiants">
	<label>Mot de passe : </label>
	<input type="password" name="password">
	<button class="btn btn-warning">Connexion</button>
</form>
<c:if test="${requestScope.introuvable == 0 }">
	<p>Login ou Mot de passe incorrect</p>
</c:if>
<button class="btn btn-warning">Créer un compte</button>

<body>

</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>
</html>