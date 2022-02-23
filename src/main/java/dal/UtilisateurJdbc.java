package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Utilisateur;

public class UtilisateurJdbc {
	
	//Connexion
	private static final String SELECT_UTIL = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,credit FROM utilisateurs WHERE pseudo = ? AND mot_de_passe = ?";

	public Utilisateur select(String pseudo, String mdp) {

		Utilisateur user = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Créer la commande
			PreparedStatement rqt = cnx.prepareStatement(SELECT_UTIL);

			// Valoriser les paramètres
			rqt.setString(1, pseudo);
			rqt.setString(2, mdp);
			ResultSet rs = rqt.executeQuery();

			if (rs.next()) {
				user = new Utilisateur();
				user.setNoUtil(rs.getInt(1));
				user.setPseudo(rs.getString(2));
				user.setNom(rs.getString(3));
				user.setPrenom(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setNumero(rs.getInt(6));
				user.setRue(rs.getString(7));
				user.setCodePostal(rs.getInt(8));
				user.setVille(rs.getString(9));
				user.setCredit(rs.getInt(10));
				user.setMdp(mdp);
			}
		} catch (Exception e) {
			System.out.println("Echec connexion à la BDD");
		}
		return user;
	}

	// Selectionner un utilisateur via son identifiant
	public Utilisateur select(int id) {
		return null;

	}
	
	//Inscription
	private static final String SQL_INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, "
			+ "mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SQL_VERIF = "SELECT pseudo FROM UTILISATEURS WHERE pseudo = ?;";
	public int insert(Utilisateur nouvelUtilisateur) {

		int exist = 0;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Si le pseudo existe déjà on renvoie 1 qui confirme qu'il n'est pas unique
			PreparedStatement rqt = cnx.prepareStatement(SQL_VERIF);
			rqt.setString(1, nouvelUtilisateur.getPseudo());
			ResultSet nbLigne = rqt.executeQuery();
			if (nbLigne.next()) {
				exist = 1;
			} else {

				// sinon on valorise la requête et on l'execute
				PreparedStatement ordre = cnx.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
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

	
	//Modification
	private static final String SQL_UPDATE = "UPDATE UTILISATEURS SET pseudo= ?, nom= ?, prenom= ?, email= ?, telephone= ?, rue= ?, "
			+ "code_postal= ?,ville= ?, mot_de_passe= ?  WHERE no_utilisateur= ? ";

	public int update(Utilisateur modificationUtilisateur) {

		int exist = 0;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Si le pseudo existe déjà on renvoie 1 qui confirme qu'il n'est pas unique
			// TODO gérer lorsque l'utilisateur garde le même pseudo (conflit avec son
			// ancien pseudo qui est pareil)

			PreparedStatement rqt = cnx.prepareStatement(SQL_VERIF);
			rqt.setString(1, modificationUtilisateur.getPseudo());
			ResultSet nbLigne = rqt.executeQuery();
			PreparedStatement ordre = cnx.prepareStatement(SQL_UPDATE);
			// Si la requête renvoie une ligne
			if (nbLigne.next()) {
				// existe passe à un qui signifie qu'il existe au moins 1 utilisateur avec ce
				// pseudo
				exist = 1;
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

				// si le nom d'utilisateur qu'on a rentré = nom utilisateur acutel on exécute qd
				// même l'update
				if (modificationUtilisateur.getPseudo() == modificationUtilisateur.getPseudo()) {
					ordre.executeUpdate();
				} else {
					// Sinon on renvoi 2 pour dire que le pseudo existe déjà mais d'un autre
					// utilisateur donc d'en choisir un autre
					exist = 2;
				}
			} else {

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

	
	//Suppression 
	private static final String SQL_DELETE_BY_NO = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SQL_DELETE_ARTICLES = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	private static final String SQL_DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE no_article = ?";
	private static final String SQL_DELETE_ENCHERES = "DELETE FROM ENCHERES WHERE no_utilisateur = ?";

	// TODO Exécuter les requêtes afin de compléter la suppression de profil

	public void delete(Integer utilisateurASupprimer) throws Exception {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Créer la commande
			PreparedStatement rqt = cnx.prepareStatement(SQL_DELETE_ARTICLES);
			PreparedStatement ordre = cnx.prepareStatement(SQL_DELETE_BY_NO);

			rqt.setInt(1, utilisateurASupprimer);
			rqt.executeUpdate();

			ordre.setInt(1, utilisateurASupprimer);

			int nbLigneAffectees = ordre.executeUpdate();

			if (nbLigneAffectees == 0) {
				throw new Exception("Aucune ligne n'a été supprimée de la BDD");
			}

		} catch (SQLException e) {
			throw new Exception("Impossible de supprimer l'utilisateur");

		}

	}

	
}
