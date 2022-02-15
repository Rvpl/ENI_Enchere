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
	private List<Articles>articlesBN;
	
	private static final String SELECT_BY_NAME = "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM ARTICLES_VENDUS Articles_vendus WHERE nom_article LIKE '%'+?+'%';";
	private static final String SELECT_ALL =     "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM ARTICLES_VENDUS;";
	
	public List<Articles> select (String nomArticle) {
		Connection cnx = null;	
		articlesBN = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();	
			//Créer la commande
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_NAME);
			
			//initialiser la variable
			rqt.setString(1, nomArticle);
			
			
			ResultSet rs = rqt.executeQuery();
			while(rs.next()) {
				Articles article = new Articles();
				article.setNoArticle(rs.getInt(1));
				article.setNomArticle(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
				article.setDateFinEncheres(rs.getDate(5).toLocalDate());
				article.setMiseAPrix(rs.getInt(6));
				article.setPrixVente(rs.getInt(7));
				article.setNoUtilisateur(rs.getInt(8));
				articlesBN.add(article);
				}	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Echec connexion à la BDD");
		}
		return articlesBN;
	}	
	
	public List<Articles> getArticles() {
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
		}
			}catch(Exception e) {
			e.printStackTrace();
		}
		return articles;
	}
}
