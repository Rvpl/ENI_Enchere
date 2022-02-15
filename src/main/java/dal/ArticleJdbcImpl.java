package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleJdbcImpl {
	
	private static final String SELECT_BY_NAME = "SELECT no_article, nom_article FROM Articles_vendus WHERE nom_article LIKE ?";
	
	public int select (String nomArticle) {
		int numero = 0;
		Connection cnx = null;		
		try {
			cnx = ConnectionProvider.getConnection();
			
			//Créer la commande
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_NAME);
			
			//initialiser la variable
			rqt.setString(1, nomArticle);
			
			ResultSet rs = rqt.executeQuery();
			
			if(rs.next()) {
				numero = rs.getInt(1);
			}else {
				System.out.println("article non trouvé");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Echec connexion à la BDD");
		}
		return numero;
	}
}
