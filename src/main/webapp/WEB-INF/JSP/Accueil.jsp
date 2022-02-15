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

	<h2 class="title">Liste des enchères</h2>
	<form action="${pageContext.request.contextPath}/home" class="filters" method="post">
		<label for="name">Filtres : </label>
		<fieldset>
			<input type="text" name="filtre" id="filtre" required>	
		</fieldset>
		<input type="submit" value="Rechercher">
	</form>
	
	 
</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>

</html>

