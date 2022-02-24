package bll;

import java.sql.SQLException;

import bo.Article;
import dal.ArticleJdbc;
import dal.DALException;

public class DetailVenteManager {

	private ArticleJdbc articleMng;

	public DetailVenteManager() {
		super();
		articleMng = new ArticleJdbc();
	}

	public Article selectAll(int detailArticle) throws BLLException {
		Article unArticle = null;
		try {
			unArticle = articleMng.detailVente(detailArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unArticle;
	}
	
	public int recupMontant(int idArticle) throws BLLException{
		int montant = 0;
		try {
			montant = articleMng.recupMontant(idArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return montant;	
	}
	
	public boolean addEnchere(int montant, int noEncherisseur, int idArticle, int credit) throws BLLException{
		boolean ok = false;
		
		int montantInit = 0;
		try {
			montantInit = recupMontant(idArticle);
		if (credit > montant) {
			if(montantInit < montant) {
				articleMng.addEnchere(montant,noEncherisseur, idArticle);
				ok = true;
			}else {
				ok = false;
			}
		}else {			
			throw new BLLException("Vous n'avez pas assez de crédit!"); 
		}
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
		
		}

	
}