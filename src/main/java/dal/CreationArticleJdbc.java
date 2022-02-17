package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import bo.Article;

public class CreationArticleJdbc {

private static final String SQL_INSERT_ARTICLE ="INSERT INTO ARTICLES_VENDUS ( nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur) VALUES ( '?', '?', '?', '?', ?, ?, ?)";
	
	public void addArticle (Article nouvelArticle) {
		
		Connection cnx = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			
			
			PreparedStatement rqt = cnx.prepareStatement(SQL_INSERT_ARTICLE,  PreparedStatement.RETURN_GENERATED_KEYS);
			
			rqt.setString(1, nouvelArticle.getNomArticle());
			rqt.setString(1, nouvelArticle.getDescription());
			rqt.setString(1, nouvelArticle.getDateDebutEncheres().toString());
			rqt.setString(1, nouvelArticle.getDateFinEncheres().toString());
			rqt.setInt(1, nouvelArticle.getMiseAPrix());
			rqt.setInt(1, nouvelArticle.getPrixVente());
			rqt.setInt(1, nouvelArticle.getNoUtilisateur());
			
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
	
		
	}
		
}

