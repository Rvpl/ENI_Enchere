package bll;

import bo.Utilisateur;
import dal.UtilisateurJdbc;

public class utilisateurBLL {

	private UtilisateurJdbc userMng;
	
	public utilisateurBLL() {
		userMng = new UtilisateurJdbc();
	}
	
	//COnnexion
	public Utilisateur selectUtilisateur(String pseudo, String mdp) {
		return userMng.select(pseudo, mdp);
	}
	
	//Inscription
	public int insert(Utilisateur nouvelUtilisateur) {
		return userMng.insert(nouvelUtilisateur);
	}
	
	//Modification
	public int update(Utilisateur modificationUtilisateur) {
		return userMng.update(modificationUtilisateur);
	}
	
	//Suppression
	public void delete(Integer utilisateurASupprimer) {

		try {
			userMng.delete(utilisateurASupprimer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
