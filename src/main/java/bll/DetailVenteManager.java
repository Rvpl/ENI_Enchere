package bll;

import java.sql.SQLException;

import bo.Article;
import dal.ArticleJdbc;

public class DetailVenteManager {

	private ArticleJdbc articleMng;

	public DetailVenteManager() {
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

	
}