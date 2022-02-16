package bll;

import dal.SuppressionJdbc;

public class SuppressionManager {
	
	private SuppressionJdbc suppressionMng;

	public SuppressionManager() {
		super();
		suppressionMng = new SuppressionJdbc();
	}
	
	public void delete(Integer utilisateurASupprimer) {
		
		try {
			suppressionMng.delete(utilisateurASupprimer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
