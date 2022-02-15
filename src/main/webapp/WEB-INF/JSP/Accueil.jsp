<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">ENI-Encheres</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	      <div class="navbar-nav">
	        <a class="nav-link" href="${pageContext.request.contextPath}/connecter">connexion</a>
	        <a class="nav-link" href="#">/</a>
	        <a class="nav-link" href="#">inscription</a>
	      </div>
	    </div>
	  </div>
	</nav>
	<h2 class="title">Liste des enchères</h2>
	<form action="${pageContext.request.contextPath}/filtreServlet" class="filters" method="post">
		<label for="name">Filtres : </label>
		<fieldset>
			<input type="text" name="filtre" id="filtre" required>	
		</fieldset>
		<input type="submit" value="Rechercher">
	</form>
	
	
</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>

	<style>
		.title {
			text-align: center;
			font-size: 1.5em;
		}
		.filters{
			font-weight: bold;
		}
		
	</style>
</html>

