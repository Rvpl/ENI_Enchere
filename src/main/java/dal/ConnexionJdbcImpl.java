package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnexionJdbcImpl {

	private static final String SELECT_UTIL = "SELECT no_utilisateur,pseudo,nom,prenom FROM utilisateurs WHERE pseudo = ? AND mot_de_passe = ?";
	
	
	public int select(String pseudo,String mdp) {
		Connection cnx = null;
		int no = 0;
		try {
			cnx = ConnectionProvider.getConnection();
			
			//Créer la commande
			PreparedStatement rqt = cnx.prepareStatement(SELECT_UTIL);
			
			//Valoriser les paramètres
			rqt.setString(1, pseudo);
			rqt.setString(2, mdp);
			ResultSet rs = rqt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getInt(1));
			}
			
		}catch(Exception e) {
			System.out.println("Echec connexion à la BDD");
		}
		return no;
	}
}
