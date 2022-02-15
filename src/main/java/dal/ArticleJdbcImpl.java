package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Articles;

public class ArticleJdbcImpl {
	
	private List<Articles>articles;
	
	private static final String SELECT_BY_NAME = "SELECT no_article, nom_article FROM Articles_vendus WHERE nom_article LIKE ?";
	private static final String SELECT_ALL = "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM ARTICLES_VENDUS";
	
	public int select (String nomArticle) {
		int numero = 0;
		Connection cnx = null;	
		articles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			
			Statement req = cnx.createStatement();
			ResultSet nbLigne = req.executeQuery(SELECT_ALL);
			while(nbLigne.next()) {
				Articles article = new Articles();
				article.setNoArticle(nbLigne.getInt(1));
				article.setNomArticle(nbLigne.getString(2));
				article.setDescription(nbLigne.getString(3));
				article.setDateDebutEncheres(nbLigne.getDate(4).toLocalDate());
				article.setDateFinEncheres(nbLigne.getDate(5).toLocalDate());
				article.setMiseAPrix(nbLigne.getInt(6));
				article.setPrixVente(nbLigne.getInt(7));
				article.setNoUtilisateur(nbLigne.getInt(8));
				articles.add(article);
				System.out.println(article.toString());
				
			}
			
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

	public List<Articles> getArticles() {
		return articles;
	}
}
