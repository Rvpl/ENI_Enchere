package bll;

import bo.Utilisateur;
import dal.DALException;
import dal.UtilisateurJdbc;

public class utilisateurBLL {

	private UtilisateurJdbc userMng;
	
	public utilisateurBLL() {
		userMng = new UtilisateurJdbc();
	}
	
	//COnnexion
	public Utilisateur selectUtilisateur(String pseudo, String mdp) throws BLLException {
		if(pseudo.isBlank() || pseudo.isEmpty() || pseudo == null) {
			throw new BLLException("Pseudo obligatoire pour la connexion");
		}
		if(mdp.isBlank() || mdp.isEmpty() || mdp == null) {
			throw new BLLException("Mot de passe obligatoire pour la connexion");
		}
		return userMng.select(pseudo, mdp);	
	}
	
	//Inscription
	public int insert(Utilisateur nouvelUtilisateur) throws BLLException {
		if(nouvelUtilisateur.getPseudo().isEmpty() || nouvelUtilisateur.getPseudo().isBlank() ) {
			throw new BLLException("Pseudo obligatoire");
		}
		if(nouvelUtilisateur.getNom().isEmpty() || nouvelUtilisateur.getNom().isBlank()) {
			throw new BLLException("Nom obligatoire");
		}
		if(nouvelUtilisateur.getEmail().isEmpty() || nouvelUtilisateur.getEmail().isBlank()) {
			throw new BLLException("Email obligatoire");
		}
		if(nouvelUtilisateur.getNumero() < 0 || nouvelUtilisateur.getNumero() == 0) {
			throw new BLLException("numéro obligatoire");
		}
		if(nouvelUtilisateur.getRue().isBlank() || nouvelUtilisateur.getRue().isEmpty()) {
			throw new BLLException("Rue obligatoire");
		}
		if(nouvelUtilisateur.getCodePostal() < 0 || nouvelUtilisateur.getCodePostal() == 0) {
			throw new BLLException("Numéro obligatoire");
		}
		if(nouvelUtilisateur.getVille().isEmpty() || nouvelUtilisateur.getVille().isBlank()) {
			throw new BLLException("Ville obligatoire");
		}
		if(nouvelUtilisateur.getMdp().isEmpty() || nouvelUtilisateur.getMdp().isBlank()|| nouvelUtilisateur.getMdp() == null) {
			throw new BLLException("Mot de passe obligatoire");
		}
		return userMng.insert(nouvelUtilisateur);
	}
	
	//Modification
	public int update(Utilisateur modificationUtilisateur) throws BLLException {
		if(modificationUtilisateur.getPseudo().isEmpty() || modificationUtilisateur.getPseudo().isBlank() ) {
			throw new BLLException("Pseudo obligatoire");
		}
		if(modificationUtilisateur.getNom().isEmpty() || modificationUtilisateur.getNom().isBlank()) {
			throw new BLLException("Nom obligatoire");
		}
		if(modificationUtilisateur.getEmail().isEmpty() || modificationUtilisateur.getEmail().isBlank()) {
			throw new BLLException("Email obligatoire");
		}
		if(modificationUtilisateur.getNumero() < 0 || modificationUtilisateur.getNumero() == 0) {
			throw new BLLException("numéro obligatoire");
		}
		if(modificationUtilisateur.getRue().isBlank() || modificationUtilisateur.getRue().isEmpty()) {
			throw new BLLException("Rue obligatoire");
		}
		if(modificationUtilisateur.getCodePostal() < 0 || modificationUtilisateur.getCodePostal() == 0) {
			throw new BLLException("Numéro obligatoire");
		}
		if(modificationUtilisateur.getVille().isEmpty() || modificationUtilisateur.getVille().isBlank()) {
			throw new BLLException("Ville obligatoire");
		}
		if(modificationUtilisateur.getMdp().isEmpty() || modificationUtilisateur.getMdp().isBlank()) {
			throw new BLLException("Mot de passe obligatoire");
		}
		return userMng.update(modificationUtilisateur);
	}
	
	//Suppression
	public void delete(Integer utilisateurASupprimer) throws BLLException{
			try {
				if(utilisateurASupprimer != 0) {
					userMng.delete(utilisateurASupprimer);
				}		
			} catch (DALException e) {
				e.printStackTrace();
			}

	}	
}
