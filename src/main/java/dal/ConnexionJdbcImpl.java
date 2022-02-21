package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bo.Utilisateur;

public class ConnexionJdbcImpl {

	private static final String SELECT_UTIL = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,credit FROM utilisateurs WHERE pseudo = ? AND mot_de_passe = ?";
	
	public Utilisateur select(String pseudo,String mdp) {
		
		Utilisateur user = null;
		try (Connection  cnx = ConnectionProvider.getConnection()){
			
			
			//Créer la commande
			PreparedStatement rqt = cnx.prepareStatement(SELECT_UTIL);
			
			//Valoriser les paramètres
			rqt.setString(1, pseudo);
			rqt.setString(2, mdp);
			ResultSet rs = rqt.executeQuery();
			
			if(rs.next()) {
				user = new Utilisateur();
				user.setNoUtil(rs.getInt(1));
				user.setPseudo(rs.getString(2));
				user.setNom(rs.getString(3));
				user.setPrenom(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setNumero(rs.getInt(6));
				user.setRue(rs.getString(7));
				user.setCodePostal(rs.getInt(8));
				user.setVille(rs.getString(9));
				user.setCredit(rs.getInt(10));
				user.setMdp(mdp);			}
		}catch(Exception e) {
			System.out.println("Echec connexion à la BDD");
		}
		return user;
	}
	
	//Selectionner un utilisateur via son identifiant
	public Utilisateur select(int id) {
		return null;
		
	}
}
