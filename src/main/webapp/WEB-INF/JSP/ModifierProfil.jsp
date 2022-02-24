<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/JSP/Fragments/Head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/JSP/Fragments/headerCo.jspf"%>
<title>Modifier profil</title>
</head>
<body>
	<h1>Mon profil</h1>
	<div class=container>
		<div>
			<p>${requestScope.error}</p>
		</div>
		<form action="${pageContext.request.contextPath}/modificationProfil"
			method="post">
			<div class="row">
				<div class="col-6">
					<label>Pseudo:</label> <input type="text" name="pseudo"
						value="${sessionScope.utilisateur.pseudo}">

					</div>			
					<div class="col-6">
						<label>Nom:</label> <input type="text" name="nom"
							value="${sessionScope.utilisateur.nom}">
					</div>

					<div class="col-6">
						<label>Prénom:</label> <input type="text" name="Prenom"
							value="${sessionScope.utilisateur.prenom}">
					</div>

					<div class="col-6">

						<label>Email:</label> <input type="text" name="email"
							value="${sessionScope.utilisateur.email}">
					</div>

					<div class="col-6">

						<label>Téléphone:</label> <input type="text" name="telephone"
							value="${sessionScope.utilisateur.numero}">
					</div>

					<div class="col-6">

						<label>rue:</label> <input type="text" name="rue"
							value="${sessionScope.utilisateur.rue}">
					</div>

					<div class="col-6">

						<label>Code postal:</label> <input type="text" name="codePostal"
							value="${sessionScope.utilisateur.codePostal}">
					</div>

					<div class="col-6">

						<label>ville:</label> <input type="text" name="ville"
							value="${sessionScope.utilisateur.ville}">
					</div>

					<div class="col-6">
						<label>Mot de passe actuel:</label> <input type="password"
							name=verifMdp>


					</div>

					<div class="col-6">
						<label>Nouveau mot de passe:</label> <input type="password"
							name="nouveauMDP">
					</div>

					<div class="col-6">
						<label>Confirmation:</label> <input type="password"
							name="confirmationMDP">
					</div>

					<div class="col-6">


						<label>crédit: ${sessionScope.utilisateur.credit}</label>
					</div>
					<div class="col-6">
						<input type="submit" value="enregistrer">
					</div>
				
				</div>
				<input name="id" value="${sessionScope.utilisateur.noUtil}"
					type="hidden"> <input type="password"
					name="motDePasseActuel" value="${sessionScope.utilisateur.mdp}"
					type="hidden" style="visibility: hidden">
		</form>

		<form action="${pageContext.request.contextPath}/supprimer"
			method="get">

			<div class="col-6">

				<input name="id" value="${sessionScope.utilisateur.noUtil}"
					type="hidden"> <input type="submit"
					value="Supprimer mon compte">

			</div>
			<div class="col-6" style="visibility: hidden">
				<label>Mot de passe actuel:</label> <input type="password"
					name=motDePasseActuel value="${sessionScope.utilisateur.mdp}"
					disabled> <input name="mdp"
					value="${sessionScope.utilisateur.mdp}">

			</div>
		</form>
	</div>
</body>
<%@ include file="/WEB-INF/JSP/Fragments/Footer.jspf"%>

</html>