<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
	<h1>Mon profil</h1>
	<div class=container>
	<form action="${pageContext.request.contextPath}/inscription" method="post">
	
		<div class=blocInscription>	
			<div class=infos>
				<label>Pseudo:</label>
				<input type="text" name="pseudo">
			</div>
			<div class=infos>
				<br>
				<label>Prénom:</label>
				<input type="text" name="Prenom">
			</div>
			<div class=infos>
				<br>
				<label>Téléphone:</label>
				<input type="text" name="telephone">
			</div>
			<div class=infos>
				<br>
				<label>Code postal:</label>
				<input type="text" name="codePostal">
			</div>
			<div class=infos>
				<br>
				<label>Mot de passe:</label>
				<input type="password" name="motDePasse">
			</div>
			
				<br>
		</div>
		
			<div class=blocInscription>	
				<div class=infos>
					<label>Nom:</label>
					<input type="text" name="nom">
				</div>
				<div class=infos>
					<br>
					<label>Email:</label>
					<input type="text" name="email">
				</div>
				<div class=infos>
					<br>
					<label>rue:</label>
					<input type="text" name="rue">
				</div>
				<div class=infos>
					<br>
					<label>ville:</label>
					<input type="text" name="ville">
				</div>
				<div class=infos>
					<br>
					<label>Confirmation:</label>
					<input type="password" name="confirmationMDP">
				</div>			
				<br>
		</div>
	</div>
	<div class=bouton>
	<input type="submit" value = "creer">
	<a href="http://localhost:8080/ENI_Enchere/"><button>Annuler</button></a>
	</div>
	</form>
		
	
</body>
<style>
h1{
    text-align: center;
}
.container{
    display: flex;
    justify-content: space-evenly;}
    
.bouton{
text-align: center;
}	
		

</style>
</html>