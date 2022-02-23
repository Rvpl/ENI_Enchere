<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<c:if test="${sessionScope.utilisateur == null }">
		<%@ include file="/WEB-INF/JSP/Fragments/headerDeco.jspf"%>
		<div class="container">
			<h2 class="title">Liste des enchères</h2>
			<form action="${pageContext.request.contextPath}/home"
				class="filters" method="post">
				<label for="name">Filtres : </label>
				<fieldset>
					<input type="text" name="filtre" id="filtre" required>
				</fieldset>
				<select name="choixCategorie">
					<option value="Toutes">Toutes</option>
					<c:forEach var="categorie" items="${requestScope.categorie}">
						<option value="${categorie}">${categorie}</option>
					</c:forEach>
				</select> <input type="submit" value="Rechercher">
			</form>

			<c:if test="${requestScope.articles != null}">
				<c:if test="${ requestScope.articlesBN == null}">
					<div class="container">
						<div class="row">
							<c:forEach var="article" items="${requestScope.articles}">
								<div class="col-3" id="pageDecoRow">
									<p>${article.nomArticle }</p>
									<p>prix : ${article.miseAPrix } points</p>
									<p>Fin de l'enchère : ${article.dateFinEncheres }</p>
									<p>Vendeur : ${article.nomArticle}</p>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</c:if>

			<c:if test="${requestScope.articlesBN != null}">
				<div class="row">
					<c:forEach var="articleBN" items="${requestScope.articlesBN}">
						<div class="col-3">
							<p>${articleBN.nomArticle }</p>
							<p>prix : ${articleBN.miseAPrix }</p>
							<p>Fin de l'enchère : ${articleBN.dateFinEncheres }</p>
							<p>Vendeur :</p>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</c:if>



	<c:if test="${sessionScope.utilisateur != null }">
		<%@ include file="/WEB-INF/JSP/Fragments/headerCo.jspf"%>
		<div class="container">
			<h2 class="title">Liste des enchères</h2>
			<form action="${pageContext.request.contextPath}/home"
				class="filters" method="post">
				<label for="name">Filtres : </label>
				<fieldset>
					<input type="text" name="filtre" id="filtre" required>
				</fieldset>
				<select name="choixCategorie">
					<option value="Toutes">Toutes</option>
					<c:forEach var="categorie" items="${requestScope.categorie}">
						<option value="${categorie}">${categorie}</option>
					</c:forEach>
				</select>

				<div class="container-radio">
					<div class="row">
						<div class="col-3">
							<input type="radio" name="achat/revente" value=""
								checked="checked"> <label>Achats</label><br>
							<div style="padding-left: 25px">
								<input type="checkbox" name="achat/revente"> <label>enchères
									ouvertes</label><br> <input type="checkbox" name="mesEncheres">
								<label>mes enchères</label><br> <input type="checkbox"
									name="remportes"> <label>mes enchères
									remportées</label>
							</div>
						</div>

						<div class="col-3">
							<input type="radio" name="achat/revente" value=""
								disabled="disabled"> <label>Mes ventes</label><br>

							<div style="padding-left: 25px">
								<input type="checkbox" name="ventesEC"> <label>mes
									ventes ouvertes</label><br> <input type="checkbox" name="ventesND">
								<label>ventes non débutées</label><br> <input
									type="checkbox" name="ventesT"> <label>ventes
									terminées</label>
							</div>
						</div>
						<div class="col-3">
							<input type="submit" value="Rechercher">
						</div>
					</div>
				</div>
			</form>

			<c:if test="${requestScope.articles != null}">
				<c:if test="${ requestScope.articlesBN == null}">
					<div class="container" style="padding-top: 40px">
						<div class="row">
							<c:forEach var="article" items="${requestScope.articles}">
								<div class="col-3">
									<form action="${pageContext.request.contextPath}/detailVente"
										method="get">
										<input type="hidden" name="noArticle" type="submit"
											value="${article.noArticle}">
										<button>${article.nomArticle }</button>
									</form>
									<p>prix : ${article.miseAPrix } points</p>
									<p>Fin de l'enchère : ${article.dateFinEncheres }</p>
									<p>
										Vendeur : <a href="#">lien vers page vendeur</a>
									</p>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</c:if>

			<c:if test="${requestScope.articlesBN != null}">
				<div class="row" style="padding-top: 40px">
					<c:forEach var="articleBN" items="${requestScope.articlesBN}">
						<div class="col-3">
							<p>${articleBN.nomArticle }</p>
							<p>prix : ${articleBN.miseAPrix }</p>
							<p>Fin de l'enchère : ${articleBN.dateFinEncheres }</p>
							<p>Vendeur :</p>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</c:if>



</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>
<style>
#pageDecoRow {
	border: 1px solid;
	border-color: black;
	margin: 5px;
}
</style>
</html>

