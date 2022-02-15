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
		<div class=blocInscription>	
			<div class=infos>
				<label>Pseudo:</label>
				<input type="text" name="id">
			</div>
			<div class=infos>
				<br>
				<label>Prénom:</label>
				<input type="text" name="name">
			</div>
			<div class=infos>
				<br>
				<label>Téléphone:</label>
				<input type="text" name="Phone Number">
			</div>
			<div class=infos>
				<br>
				<label>Code postal:</label>
				<input type="text" name="Postal code">
			</div>
			<div class=infos>
				<br>
				<label>Mot de passe:</label>
				<input type="password" name="password">
			</div>
			
				<br>
		</div>
		
			<div class=blocInscription>	
				<div class=infos>
					<label>Pseudo:</label>
					<input type="text" name="id">
				</div>
				<div class=infos>
					<br>
					<label>Prénom:</label>
					<input type="text" name="name">
				</div>
				<div class=infos>
					<br>
					<label>Téléphone:</label>
					<input type="text" name="Phone Number">
				</div>
				<div class=infos>
					<br>
					<label>Code postal:</label>
					<input type="text" name="Postal code">
				</div>
				<div class=infos>
					<br>
					<label>Mot de passe:</label>
					<input type="password" name="password">
				</div>			
				<br>
		</div>
	</div>
	<div class=bouton>
	<button >Créer</button>
	<button >Annuler</button>
	</div>
	
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