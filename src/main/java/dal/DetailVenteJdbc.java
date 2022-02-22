package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bo.Article;
import bo.Retrait;
import bo.Utilisateur;


public class DetailVenteJdbc {
	private static final String SQL_SELECT_ARTICLE ="SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article =?";
	private static final String SQL_SELECT_RETRAIT= "SELECT rue , code_postal, ville FROM RETRAIT WHERE no_article=?";
	private static final String SQL_SELECT_VENDEUR= "SELECT pseudo FROM UTILISATEUR WHERE no_utilisateur=?";
	
	public int detailVente (Article detailArticle) throws SQLException  {
		try (Connection cnx = ConnectionProvider.getConnection()){
		
			Statement rqt = cnx.createStatement();
			ResultSet rs = rqt.executeQuery(SQL_SELECT_ARTICLE);
			String nom= rs.getString(Article.class.getName());
			
			
			
			
		
			
			PreparedStatement rqt2 = cnx.prepareStatement(SQL_SELECT_RETRAIT);
			Retrait retirer = new Retrait();			
			
			
			PreparedStatement rqt3 = cnx.prepareStatement(SQL_SELECT_VENDEUR);
			Utilisateur user= new Utilisateur();
			
			
			
		}
		return 0;
	}
}
