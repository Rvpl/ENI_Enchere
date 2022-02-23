package bll;

import java.sql.SQLException;
import java.util.List;

import bo.Article;
import bo.Utilisateur;
import dal.ArticleJdbc;

public class articleBLL {
	
	private ArticleJdbc articleMng;
	
	public articleBLL() {
		super();
		articleMng = new ArticleJdbc();
	}

	public Article selectAll(int detailArticle) throws SQLException {
		return articleMng.detailVente(detailArticle);
	}
	
	public int recupMontant(int idArticle) {
		return articleMng.recupMontant(idArticle);
	
	}
	
	public boolean addEnchere(int montant, int noEncherisseur, int idArticle) {
		boolean ok = false;
		
		int montantInit = recupMontant(idArticle);
		
		if(montantInit < montant) {
			articleMng.addEnchere(montant,noEncherisseur, idArticle);
			ok = true;
		}else {
			System.out.println("Veuillez saisir un montant supérieur à la dernière enchère");
			ok = false;
		}
		return ok;
		
		}

	public int addArticle(Article nouvelArticle) {

		return articleMng.addArticle(nouvelArticle);
	}
	
	public List<Article> select(String nomArticle, int categorie) {

		return articleMng.select(nomArticle, categorie);
	}

	public List<Article> getArticles(String nomArticle) {
		return articleMng.getArticles(nomArticle);
	}


}
