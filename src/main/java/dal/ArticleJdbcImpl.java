package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Articles;
import bo.Utilisateur;

public class ArticleJdbcImpl {
	
	private List<Articles>articles;
	private List<Articles>articlesBN;
	private List<Utilisateur>users;
	private List<Utilisateur>usersBN;
	private static final String SELECT_BY_NAME = "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,ARTICLES_VENDUS.no_utilisateur,no_categorie, nom,prenom,pseudo FROM ARTICLES_VENDUS,UTILISATEURS WHERE ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur AND nom_article LIKE '%'+?+'%';";
	private static final String SELECT_ALL =     "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,ARTICLES_VENDUS.no_utilisateur,no_categorie, nom,prenom,pseudo FROM ARTICLES_VENDUS,UTILISATEURS WHERE ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur;";
	
	
	//Retourne une liste d'article en fonction de leur noms
	public List<Articles> select (String nomArticle) {
		Connection cnx = null;	
		articlesBN = new ArrayList<>();
		usersBN = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();	
			//Créer la commande
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_NAME);
			
			//initialiser la variable
			rqt.setString(1, nomArticle);
			
			
			ResultSet rs = rqt.executeQuery();
			while(rs.next()) {
				Utilisateur user = new Utilisateur();
				Articles article = new Articles();
				article.setNoArticle(rs.getInt(1));
				article.setNomArticle(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
				article.setDateFinEncheres(rs.getDate(5).toLocalDate());
				article.setMiseAPrix(rs.getInt(6));
				article.setPrixVente(rs.getInt(7));
				article.setNoUtilisateur(rs.getInt(8));
				
				
				user.setNoUtil(rs.getInt(8));	
				user.setNom(rs.getString(10));
				user.setPrenom(rs.getString(11));
				user.setPseudo(rs.getString(12));
				usersBN.add(user);
			
				articlesBN.add(article);
				}	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible de trouver l'article recherché");
		}
		return articlesBN;
	}	
	
	//Retourne tout les articles
	public List<Articles> getArticles() {
		Connection cnx = null;
		articles = new ArrayList<>();
		users = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			Statement req = cnx.createStatement();
			ResultSet nbLigne = req.executeQuery(SELECT_ALL);
			while(nbLigne.next()) {
				Utilisateur user = new Utilisateur();
				Articles article = new Articles();
				article.setNoArticle(nbLigne.getInt(1));
				article.setNomArticle(nbLigne.getString(2));
				article.setDescription(nbLigne.getString(3));
				article.setDateDebutEncheres(nbLigne.getDate(4).toLocalDate());
				article.setDateFinEncheres(nbLigne.getDate(5).toLocalDate());
				article.setMiseAPrix(nbLigne.getInt(6));
				article.setPrixVente(nbLigne.getInt(7));
				article.setNoUtilisateur(nbLigne.getInt(8));
				
				user.setNoUtil(nbLigne.getInt(8));		
				user.setNom(nbLigne.getString(10));
				user.setPrenom(nbLigne.getString(11));
				user.setPseudo(nbLigne.getString(12));
				users.add(user);
				articles.add(article);
		}
			}catch(Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	public List<Utilisateur> getUsers() {
		return users;
	}
	
	public List<Utilisateur> getUsersBN() {
		return usersBN;
	}
}
