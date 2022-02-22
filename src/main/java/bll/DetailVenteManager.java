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
	
	public int addEnchere(Article detailArticle) throws SQLException {
		return detailVenteMng.detailVente(detailArticle);
	}
	
}
