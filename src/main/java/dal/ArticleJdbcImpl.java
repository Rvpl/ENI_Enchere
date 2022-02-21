package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Article;
import bo.Utilisateur;

public class ArticleJdbcImpl {
	private List<Utilisateur>usersBN;
	private List<Utilisateur>users;
	private static final String SELECT_ALL =  "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,ARTICLES_VENDUS.no_utilisateur,no_categorie, nom,prenom,pseudo FROM ARTICLES_VENDUS,UTILISATEURS WHERE ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur;";	
	//requête qui affiche les articles en fonction des catégories
	private static final String SELECT_BY_NAMECATEG ="SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,ARTICLES_VENDUS.no_utilisateur,no_categorie, nom,prenom,pseudo FROM ARTICLES_VENDUS,UTILISATEURS WHERE ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur AND nom_article  LIKE '%'+?+'%' AND no_categorie = ?;";
	private static final String SELECT_ALL_BY_NAME = "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,ARTICLES_VENDUS.no_utilisateur,no_categorie, nom,prenom,pseudo FROM ARTICLES_VENDUS,UTILISATEURS WHERE ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur AND nom_article  LIKE '%'+?+'%';";
	//Retourne une liste d'article en fonction de leur noms
	public List<Article> select (String nomArticle, int categorie) {
			
		List<Article>articlesBN = new ArrayList<>();
		usersBN = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
				
			
			if(categorie == 0) {
				//Créer la commande
				Statement rqt = cnx.createStatement();
				
				ResultSet rs = rqt.executeQuery(SELECT_ALL);
				while(rs.next()) {
					Utilisateur user = new Utilisateur();
					Article article = new Article();
					article.setNoArticle(rs.getInt(1));
					article.setNomArticle(rs.getString(2));
					article.setDescription(rs.getString(3));
					article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
					article.setDateFinEncheres(rs.getDate(5).toLocalDate());
					article.setMiseAPrix(rs.getInt(6));
					article.setPrixVente(rs.getInt(7));
					article.getUtilisateur().setNoUtil(rs.getInt(8));
					
					
					user.setNoUtil(rs.getInt(8));	
					user.setNom(rs.getString(10));
					user.setPrenom(rs.getString(11));
					user.setPseudo(rs.getString(12));
					usersBN.add(user);
				
					articlesBN.add(article);
					}
			}else {
				//Créer la commande
				PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_NAMECATEG);
				
				//initialiser la variable
				rqt.setString(1, nomArticle);
				rqt.setInt(2, categorie);
				
				
				ResultSet rs = rqt.executeQuery();
				while(rs.next()) {
					Utilisateur user = new Utilisateur();
					Article article = new Article();
					article.setNoArticle(rs.getInt(1));
					article.setNomArticle(rs.getString(2));
					article.setDescription(rs.getString(3));
					article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
					article.setDateFinEncheres(rs.getDate(5).toLocalDate());
					article.setMiseAPrix(rs.getInt(6));
					article.setPrixVente(rs.getInt(7));
					article.getUtilisateur().setNoUtil(rs.getInt(8));
					
					
					user.setNoUtil(rs.getInt(8));	
					user.setNom(rs.getString(10));
					user.setPrenom(rs.getString(11));
					user.setPseudo(rs.getString(12));
					usersBN.add(user);
				
					articlesBN.add(article);
					}
			}
				

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible de trouver l'article recherché");
		}
		return articlesBN;
	}	
	//Retourne tout les articles
	public List<Article> getArticles(String nomArticle) {
		 
		List<Article>articles = new ArrayList<>();
		users = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
					//Créer la commande
					PreparedStatement rqt = cnx.prepareStatement(SELECT_ALL_BY_NAME);
					
					//initialiser la variable
					rqt.setString(1, nomArticle);
					
					
					ResultSet rs = rqt.executeQuery();
					while(rs.next()) {
						Utilisateur user = new Utilisateur();
						Article article = new Article();
						article.setNoArticle(rs.getInt(1));
						article.setNomArticle(rs.getString(2));
						article.setDescription(rs.getString(3));
						article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
						article.setDateFinEncheres(rs.getDate(5).toLocalDate());
						article.setMiseAPrix(rs.getInt(6));
						article.setPrixVente(rs.getInt(7));
						article.getUtilisateur().setNoUtil(rs.getInt(8));
						
						
						user.setNoUtil(rs.getInt(8));	
						user.setNom(rs.getString(10));
						user.setPrenom(rs.getString(11));
						user.setPseudo(rs.getString(12));
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
