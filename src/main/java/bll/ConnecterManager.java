package bll;

import dal.ConnexionJdbcImpl;

public class ConnecterManager {

	private ConnexionJdbcImpl connexionMng;
	
	public ConnecterManager() {
		super();
		connexionMng = new ConnexionJdbcImpl();
	}
	
	public void selectUtilisateur(String pseudo, String mdp) {
		connexionMng.select(pseudo, mdp);
	}
}

