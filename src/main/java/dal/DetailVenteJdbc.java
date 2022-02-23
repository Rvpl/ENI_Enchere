package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Article;

public class DetailVenteJdbc {
	private static final String SQL_SELECT_ARTICLE = "SELECT nom_article,description, "
			+ "date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,"
			+ " ARTICLES_VENDUS.no_categorie,CATEGORIES.libelle, RETRAITS.rue,RETRAITS.code_postal, "
			+ "RETRAITS.ville, pseudo, nom,prenom FROM ARTICLES_VENDUS,CATEGORIES,RETRAITS,UTILISATEURS"
			+ " WHERE ARTICLES_VENDUS.no_article = RETRAITS.no_article "
			+ "AND CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie"
			+ " AND ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur AND ARTICLES_VENDUS.no_article = ?";

	public Article detailVente(int detailArticle) throws SQLException {
		Article nouvelArticle = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(SQL_SELECT_ARTICLE);
			rqt.setInt(1, detailArticle);
			ResultSet rs = rqt.executeQuery();
			if (rs.next()) {
				nouvelArticle = new Article();
				nouvelArticle.setNomArticle(rs.getString(1));
				nouvelArticle.setDescription(rs.getString(2));
				nouvelArticle.setDateDebutEncheres(rs.getDate(3).toLocalDate());
				nouvelArticle.setDateFinEncheres(rs.getDate(4).toLocalDate());
				nouvelArticle.setMiseAPrix(rs.getInt(5));
				nouvelArticle.setPrixVente(rs.getInt(6));
				nouvelArticle.getUtilisateur().setNoUtil(rs.getInt(7));
				nouvelArticle.getNoCategorie().setNoCategorie(rs.getInt(8));
				nouvelArticle.getNoCategorie().setLibelle(rs.getString(9));
				nouvelArticle.getRetrait().setRue(rs.getString(10));
				nouvelArticle.getRetrait().setCodePostal(rs.getInt(11));
				nouvelArticle.getRetrait().setVille(rs.getString(12));
				nouvelArticle.getUtilisateur().setPseudo(rs.getString(13));
				nouvelArticle.getUtilisateur().setNom(rs.getString(14));
				nouvelArticle.getUtilisateur().setPrenom(rs.getString(15));
			}
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nouvelArticle;
	}
}
