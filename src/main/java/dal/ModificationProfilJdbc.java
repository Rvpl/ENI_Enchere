package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Utilisateur;

public class ModificationProfilJdbc {
	private static final String SQL_UPDATE ="UPDATE mot_de_passe FROM UTILISATEURS WHERE no_utilisateur= ?;";
	private static final String SQL_VERIF ="SELECT pseudo FROM UTILISATEURS WHERE pseudo = ?;";
	
	
	public int update (Utilisateur modificationUtilisateur) {
		
		Connection cnx = null;
		int exist = 0;		
		 try {
			cnx = ConnectionProvider.getConnection();
			
			// Si le pseudo existe déjà on renvoie 1 qui confirme qu'il n'est pas unique
			//TODO gérer lorsque l'utilisateur garde le même pseudo (conflit avec son ancien pseudo qui est pareil)
			 
		/*	PreparedStatement rqt = cnx.prepareStatement(SQL_VERIF);
			rqt.setString(1, modificationProfile.getPseudo());
			ResultSet nbLigne = rqt.executeQuery();
			if(nbLigne.next()) {
				exist =1;
			}else { */
				
				// sinon on valorise la requête et on l'execute
				PreparedStatement ordre = cnx.prepareStatement(SQL_UPDATE, PreparedStatement.RETURN_GENERATED_KEYS );
				ordre.setString(1, modificationUtilisateur.getPseudo());
				ordre.setString(2, modificationUtilisateur.getNom());
				ordre.setString(3, modificationUtilisateur.getPrenom());
				ordre.setString(4, modificationUtilisateur.getEmail());
				ordre.setInt(5, modificationUtilisateur.getNumero());
				ordre.setString(6, modificationUtilisateur.getRue());
				ordre.setInt(7, modificationUtilisateur.getCodePostal());
				ordre.setString(8, modificationUtilisateur.getVille());
				ordre.setString(9, modificationUtilisateur.getMdp());
				int nbLignesAffectees = ordre.executeUpdate();
				if (nbLignesAffectees == 0) {
					throw new Exception("Aucune ligne n'a été ajoutée en base");
				}
				ResultSet clefs = ordre.getGeneratedKeys();
				int clefAutoGeneree = -1;
				if (clefs.next()) {
					clefAutoGeneree = clefs.getInt(1);
					modificationUtilisateur.setNoUtil(clefAutoGeneree);
				}
				
			//}
			
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
