package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Utilisateur;

public class ModificationProfilJdbc {
	private static final String SQL_UPDATE ="UPDATE UTILISATEURS SET pseudo= ?, nom= ?, prenom= ?, email= ?, telephone= ?, rue= ?, code_postal= ?, "
			+ "ville= ?, mot_de_passe= ?  WHERE no_utilisateur= ? ";
	private static final String SQL_VERIF ="SELECT pseudo FROM UTILISATEURS WHERE pseudo = ?;";
	
	
	public int update (Utilisateur modificationUtilisateur) {
		
		Connection cnx = null;
		int exist = 0;		 
		 try {
			cnx = ConnectionProvider.getConnection();
			
			// Si le pseudo existe déjà on renvoie 1 qui confirme qu'il n'est pas unique
			//TODO gérer lorsque l'utilisateur garde le même pseudo (conflit avec son ancien pseudo qui est pareil)
			 
			PreparedStatement rqt = cnx.prepareStatement(SQL_VERIF);
			rqt.setString(1, modificationUtilisateur.getPseudo());
			ResultSet nbLigne = rqt.executeQuery();
			PreparedStatement ordre = cnx.prepareStatement(SQL_UPDATE);
			//Si la requête renvoie une ligne
			if(nbLigne.next()) {
				//existe passe à un qui signifie qu'il existe au moins 1 utilisateur avec ce pseudo
				exist =1;				
				ordre.setString(1, modificationUtilisateur.getPseudo());
				ordre.setString(2, modificationUtilisateur.getNom());
				ordre.setString(3, modificationUtilisateur.getPrenom());
				ordre.setString(4, modificationUtilisateur.getEmail());
				ordre.setInt(5, modificationUtilisateur.getNumero());
				ordre.setString(6, modificationUtilisateur.getRue());
				ordre.setInt(7, modificationUtilisateur.getCodePostal());
				ordre.setString(8, modificationUtilisateur.getVille());
				ordre.setString(9, modificationUtilisateur.getMdp());
				ordre.setInt(10, modificationUtilisateur.getNoUtil());
				
				//si le nom d'utilisateur qu'on a rentré = nom utilisateur acutel on exécute qd même l'update
				if(modificationUtilisateur.getPseudo() == modificationUtilisateur.getPseudo()) {
					ordre.executeUpdate();
				}else {
					//Sinon on renvoi 2 pour dire que le pseudo existe déjà mais d'un autre utilisateur donc d'en choisir un autre
					exist = 2;
				}
			}else {
				
				// sinon le pseudo n'existe pas alors on valorise la requête et on l'execute
				ordre.setString(1, modificationUtilisateur.getPseudo());
				ordre.setString(2, modificationUtilisateur.getNom());
				ordre.setString(3, modificationUtilisateur.getPrenom());
				ordre.setString(4, modificationUtilisateur.getEmail());
				ordre.setInt(5, modificationUtilisateur.getNumero());
				ordre.setString(6, modificationUtilisateur.getRue());
				ordre.setInt(7, modificationUtilisateur.getCodePostal());
				ordre.setString(8, modificationUtilisateur.getVille());
				ordre.setString(9, modificationUtilisateur.getMdp());
				ordre.setInt(10, modificationUtilisateur.getNoUtil());
				int nbLignesAffectees = ordre.executeUpdate();
				if (nbLignesAffectees == 0) {
					throw new Exception("Aucune ligne n'a été ajoutée en base");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
		
	}
}
