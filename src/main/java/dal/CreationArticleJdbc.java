package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import bo.Article;

public class CreationArticleJdbc {

private static final String SQL_INSERT_ARTICLE ="INSERT INTO ARTICLES_VENDUS ( nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	
	public int addArticle (Article nouvelArticle) {
		
		Connection cnx = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			
			
			PreparedStatement rqt = cnx.prepareStatement(SQL_INSERT_ARTICLE,  PreparedStatement.RETURN_GENERATED_KEYS);
			
			rqt.setString(1, nouvelArticle.getNomArticle());
			rqt.setString(2, nouvelArticle.getDescription());
			rqt.setString(3, nouvelArticle.getDateDebutEncheres().toString());
			rqt.setString(4, nouvelArticle.getDateFinEncheres().toString());
			rqt.setInt(5, nouvelArticle.getMiseAPrix());
			rqt.setInt(6, nouvelArticle.getNoUtilisateur());
			//numéro catégorie à régler
			rqt.setInt(7, 1);
			int nbLignesAffectees = rqt.executeUpdate();
			if (nbLignesAffectees == 0) {
				throw new Exception("Aucune ligne n'a été ajoutée en base");
			}
			ResultSet clefs = rqt.getGeneratedKeys();
			int clefAutoGeneree = -1;
			if (clefs.next()) {
				clefAutoGeneree = clefs.getInt(1);
				nouvelArticle.setNoArticle(clefAutoGeneree);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	
		
	}
		
}

