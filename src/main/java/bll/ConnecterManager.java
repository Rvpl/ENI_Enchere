package bll;

import dal.ConnexionJdbcImpl;

public class ConnecterManager {

	private ConnexionJdbcImpl connexionMng;
	
	public ConnecterManager() {
		super();
		connexionMng = new ConnexionJdbcImpl();
	}
	
	public int selectUtilisateur(String pseudo, String mdp) {
		return connexionMng.select(pseudo, mdp);
	}
}

