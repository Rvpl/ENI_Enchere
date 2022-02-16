package bll;

import bo.Utilisateur;
import dal.ConnexionJdbcImpl;

public class ConnecterManager {

	private ConnexionJdbcImpl connexionMng;
	
	public ConnecterManager() {
		super();
		connexionMng = new ConnexionJdbcImpl();
	}
	
	public Utilisateur selectUtilisateur(String pseudo, String mdp) {
		return connexionMng.select(pseudo, mdp);
	}
}

