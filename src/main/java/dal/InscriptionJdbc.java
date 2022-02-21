package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;

import bo.Utilisateur;

public class InscriptionJdbc {
	private static final String SQL_INSERT ="INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SQL_VERIF ="SELECT pseudo FROM UTILISATEURS WHERE pseudo = ?;";
	
	
	public int insert (Utilisateur nouvelUtilisateur) {
		
		 
		int exist = 0;		
		 try (Connection cnx = ConnectionProvider.getConnection()){
			 
			
			// Si le pseudo existe déjà on renvoie 1 qui confirme qu'il n'est pas unique
			PreparedStatement rqt = cnx.prepareStatement(SQL_VERIF);
			rqt.setString(1, nouvelUtilisateur.getPseudo());
			ResultSet nbLigne = rqt.executeQuery();
			if(nbLigne.next()) {
				exist =1;
			}else {
				
				// sinon on valorise la requête et on l'execute
				PreparedStatement ordre = cnx.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS );
				ordre.setString(1, nouvelUtilisateur.getPseudo());
				ordre.setString(2, nouvelUtilisateur.getNom());
				ordre.setString(3, nouvelUtilisateur.getPrenom());
				ordre.setString(4, nouvelUtilisateur.getEmail());
				ordre.setInt(5, nouvelUtilisateur.getNumero());
				ordre.setString(6, nouvelUtilisateur.getRue());
				ordre.setInt(7, nouvelUtilisateur.getCodePostal());
				ordre.setString(8, nouvelUtilisateur.getVille());
				ordre.setString(9, nouvelUtilisateur.getMdp());
				ordre.setInt(10, 100);
				ordre.setByte(11, (byte) 0);
				int nbLignesAffectees = ordre.executeUpdate();
				if (nbLignesAffectees == 0) {
					throw new Exception("Aucune ligne n'a été ajoutée en base");
				}
				ResultSet clefs = ordre.getGeneratedKeys();
				int clefAutoGeneree = -1;
				if (clefs.next()) {
					clefAutoGeneree = clefs.getInt(1);
					nouvelUtilisateur.setNoUtil(clefAutoGeneree);
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
