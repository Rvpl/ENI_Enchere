package bll;

import bo.Utilisateur;
import dal.InscriptionJdbc;

public class InscriptionManager {
	private InscriptionJdbc inscriptionMng;

	public InscriptionManager() {
		super();
		inscriptionMng = new InscriptionJdbc();
	}

	public int insert(Utilisateur nouvelUtilisateur) {
		return inscriptionMng.insert(nouvelUtilisateur);
	}
}