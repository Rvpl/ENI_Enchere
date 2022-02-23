package bll;

import java.sql.SQLException;

import bo.Article;
import dal.DetailVenteJdbc;

public class DetailVenteManager {

	private DetailVenteJdbc detailVenteMng;

	public DetailVenteManager() {
		super();
		detailVenteMng = new DetailVenteJdbc();
	}

	public Article selectAll(int detailArticle) throws SQLException {
		return detailVenteMng.detailVente(detailArticle);
	}
	
	public int recupMontant(int idArticle) {
		return detailVenteMng.recupMontant(idArticle);
	
	}
	
	public boolean addEnchere(int montant, int idArticle) {
		boolean ok = false;
		
		int montantInit = recupMontant(idArticle);
		
		if(montantInit < montant) {
			detailVenteMng.addEnchere(montant, idArticle);
			ok = true;
		}else {
			System.out.println("Veuillez saisir un montant supérieur à la dernière enchère");
			ok = false;
		}
		return ok;
		
		}

	
}