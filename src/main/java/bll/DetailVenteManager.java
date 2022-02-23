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
	
	public void addEnchere(int montant, int idArticle) {
		int montantInit = recupMontant(idArticle);
		if(montantInit < montant) {
			detailVenteMng.addEnchere(montant, idArticle);
		}else {
			System.out.println("Veuillez saisir un montant supérieur à la dernière enchère");
		}
		
		}

	public int recupMontant(int idArticle) {
		return detailVenteMng.recupMontant(idArticle);
	
	}
}