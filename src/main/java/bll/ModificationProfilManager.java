package bll;

import bo.Utilisateur;
import dal.ModificationProfilJdbc;

public class ModificationProfilManager {
	private ModificationProfilJdbc modificationProfilMng;

	public ModificationProfilManager() {
		super();
		modificationProfilMng = new ModificationProfilJdbc();
	}

	public int update(Utilisateur modificationUtilisateur) {
		return modificationProfilMng.update(modificationUtilisateur);

	}

}
