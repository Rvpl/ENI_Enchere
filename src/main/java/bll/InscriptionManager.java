package bll;

import bo.Utilisateur;
import dal.InscriptionJdbc;

public class InscriptionManager {
	private InscriptionJdbc inscriptionMng;

public InscriptionManager() {
	super();
	inscriptionMng= new InscriptionJdbc();
	}

public void insert(Utilisateur nouvelUtilisateur) {
	inscriptionMng.insert(nouvelUtilisateur);
}
}