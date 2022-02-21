package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuppressionJdbc {
	
	private static final String SQL_DELETE_BY_NO= "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SQL_DELETE_ARTICLES = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	private static final String SQL_DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE no_article = ?";
	private static final String SQL_DELETE_ENCHERES ="DELETE FROM ENCHERES WHERE no_utilisateur = ?";
	
	//TODO Exécuter les requêtes afin de compléter la suppression de profil
	
	
	public void delete (Integer utilisateurASupprimer) throws Exception {
		
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			
			//Créer la commande
			PreparedStatement rqt = cnx.prepareStatement(SQL_DELETE_ARTICLES);
			PreparedStatement ordre = cnx.prepareStatement(SQL_DELETE_BY_NO);
			
			rqt.setInt(1, utilisateurASupprimer);
			rqt.executeUpdate();
			
			ordre.setInt(1,utilisateurASupprimer);
			
			int nbLigneAffectees = ordre.executeUpdate();
			
			if(nbLigneAffectees == 0) {
				throw new Exception("Aucune ligne n'a été supprimée de la BDD");
			}
			
		} catch (SQLException e) {
			throw new Exception("Impossible de supprimer l'utilisateur");
			
		
				
				
			}
			
			
		}
		
	}
	

