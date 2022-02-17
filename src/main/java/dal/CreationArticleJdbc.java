package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Article;
import bo.Utilisateur;

public class CreationArticleJdbc {

private static final String SQL_INSERT_ARTICLE ="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES ( ?, ?, ?, ?, ?, ?, ?, 1)";
private static final String SQL_INSERT_ENCHERE ="INSERT INTO ENCHERES(date_enchere,montant_enchere,no_article,no_utilisateur) VALUES (?,?,?,?)";
private static final String SQL_SELCT_USER ="SELECT pseudo,nom,prenom FROM UTILISATEURS WHERE no_utilisateur = ?";
	public int addArticle (Article nouvelArticle) {
		
		Connection cnx = null;
		int verif = 0;
		
		try {
			cnx = ConnectionProvider.getConnection();
			
			
			PreparedStatement rqt = cnx.prepareStatement(SQL_INSERT_ARTICLE,  PreparedStatement.RETURN_GENERATED_KEYS);
			
			rqt.setString(1, nouvelArticle.getNomArticle());
			rqt.setString(2, nouvelArticle.getDescription());
			
			//modification de LocalDate à Date SQL
			rqt.setDate(3, java.sql.Date.valueOf(nouvelArticle.getDateDebutEncheres()));
			rqt.setDate(4, java.sql.Date.valueOf(nouvelArticle.getDateFinEncheres()));
			rqt.setInt(5, nouvelArticle.getMiseAPrix());
			rqt.setInt(6, 0);
			//Récupération de l'id utilisateur
			rqt.setInt(7, nouvelArticle.getUtilisateur().getNoUtil());
			int nbLignesAffectees = rqt.executeUpdate();
			if (nbLignesAffectees == 0) {
				throw new Exception("Aucune ligne n'a été ajoutée en base");
			}
			verif = 1;
			ResultSet clefs = rqt.getGeneratedKeys();
			int clefAutoGeneree = -1;
			if (clefs.next()) {
				clefAutoGeneree = clefs.getInt(1);
				nouvelArticle.setNoArticle(clefAutoGeneree);
				
				PreparedStatement rqt2 = cnx.prepareStatement(SQL_INSERT_ENCHERE);
				
				rqt2.setDate(1, java.sql.Date.valueOf(nouvelArticle.getDateDebutEncheres()));
				rqt2.setInt(2, nouvelArticle.getMiseAPrix());
				rqt2.setInt(3, nouvelArticle.getNoArticle());
				rqt2.setInt(4, nouvelArticle.getUtilisateur().getNoUtil());
				
				int nbLigne = rqt2.executeUpdate();
				if(nbLigne == 0) {
					throw new Exception("Aucune ligne n'a été ajoutée en base");
				}
				verif = 2;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return verif;
	
		
	}		
}
