package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Article;
import bo.Utilisateur;

public class ArticleJdbc {
	
	private static final String SELECT_ALL = "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,"
			+ "prix_initial,prix_vente,ARTICLES_VENDUS.no_utilisateur,no_categorie, nom,prenom,pseudo FROM ARTICLES_VENDUS,UTILISATEURS "
			+ "WHERE ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur;";
	
	
	private static final String SELECT_BY_NAMECATEG = "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,"
			+ "prix_initial,prix_vente,ARTICLES_VENDUS.no_utilisateur,no_categorie, nom,prenom,pseudo FROM ARTICLES_VENDUS,UTILISATEURS "
			+ "WHERE ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur AND nom_article  LIKE '%'+?+'%' AND no_categorie = ?;";
	
	//Affiche tout les articles peu importe la catégorie
	//   OU
	//Affiche les articles en fonction des catégories	
	public List<Article> select(String nomArticle, int categorie) {

		List<Article> articlesBN = new ArrayList<>();
		// usersBN = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {

			if (categorie == 0) {
				// Créer la commande
				Statement rqt = cnx.createStatement();

				ResultSet rs = rqt.executeQuery(SELECT_ALL);
				while (rs.next()) {
					Utilisateur user = new Utilisateur();
					Article article = new Article();
					article.setNoArticle(rs.getInt(1));
					article.setNomArticle(rs.getString(2));
					article.setDescription(rs.getString(3));
					article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
					article.setDateFinEncheres(rs.getDate(5).toLocalDate());
					article.setMiseAPrix(rs.getInt(6));
					article.setPrixVente(rs.getInt(7));
					article.getUtilisateur().setNoUtil(rs.getInt(8));
					

					user.setNoUtil(rs.getInt(8));
					user.setNom(rs.getString(10));
					user.setPrenom(rs.getString(11));
					user.setPseudo(rs.getString(12));
					article.setUtilisateur(user);

					articlesBN.add(article);
				}
			} else {
				// Créer la commande
				PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_NAMECATEG);

				// initialiser la variable
				rqt.setString(1, nomArticle);
				rqt.setInt(2, categorie);

				ResultSet rs = rqt.executeQuery();
				while (rs.next()) {
					Utilisateur user = new Utilisateur();
					Article article = new Article();
					article.setNoArticle(rs.getInt(1));
					article.setNomArticle(rs.getString(2));
					article.setDescription(rs.getString(3));
					article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
					article.setDateFinEncheres(rs.getDate(5).toLocalDate());
					article.setMiseAPrix(rs.getInt(6));
					article.setPrixVente(rs.getInt(7));

					user.setNoUtil(rs.getInt(8));
					user.setNom(rs.getString(10));
					user.setPrenom(rs.getString(11));
					user.setPseudo(rs.getString(12));

					article.setUtilisateur(user);
					articlesBN.add(article);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible de trouver l'article recherché");
		}
		return articlesBN;
	}

	
	
	private static final String SELECT_ALL_BY_NAME = "SELECT no_article, nom_article, description,date_debut_encheres,date_fin_encheres,"
			+ "prix_initial,prix_vente,ARTICLES_VENDUS.no_utilisateur,no_categorie, nom,prenom,pseudo FROM ARTICLES_VENDUS,UTILISATEURS "
			+ "WHERE ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur AND nom_article  LIKE '%'+?+'%';";
	//Affiche tout les articles par nom
	public List<Article> getArticles(String nomArticle) {

		List<Article> articles = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Créer la commande
			PreparedStatement rqt = cnx.prepareStatement(SELECT_ALL_BY_NAME);

			// initialiser la variable
			rqt.setString(1, nomArticle);

			ResultSet rs = rqt.executeQuery();
			while (rs.next()) {
				Utilisateur user = new Utilisateur();
				Article article = new Article();
				article.setNoArticle(rs.getInt(1));
				article.setNomArticle(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
				article.setDateFinEncheres(rs.getDate(5).toLocalDate());
				article.setMiseAPrix(rs.getInt(6));
				article.setPrixVente(rs.getInt(7));
				article.getUtilisateur().setNoUtil(rs.getInt(8));

				user.setNoUtil(rs.getInt(8));
				user.setNom(rs.getString(10));
				user.setPrenom(rs.getString(11));
				user.setPseudo(rs.getString(12));
				article.setUtilisateur(user);
				articles.add(article);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres,"
			+ "date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_encherisseur) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?)";
	
	private static final String SQL_INSERT_ENCHERE = "INSERT INTO ENCHERES(date_enchere,montant_enchere,no_article,no_utilisateur) "
			+ "VALUES (?,?,?,?)";
	
	private static final String SQL_INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
	//Insertion de retrait / article / encheres
	public int addArticle(Article nouvelArticle) {

		int verif = 0;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement rqt = cnx.prepareStatement(SQL_INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);

			rqt.setString(1, nouvelArticle.getNomArticle());
			rqt.setString(2, nouvelArticle.getDescription());

			// modification de LocalDate à Date SQL
			rqt.setDate(3, java.sql.Date.valueOf(nouvelArticle.getDateDebutEncheres()));
			rqt.setDate(4, java.sql.Date.valueOf(nouvelArticle.getDateFinEncheres()));
			rqt.setInt(5, nouvelArticle.getMiseAPrix());
			rqt.setInt(6, 0);
			// Récupération de l'id utilisateur
			rqt.setInt(7, nouvelArticle.getUtilisateur().getNoUtil());

			rqt.setInt(8, nouvelArticle.getNoCategorie().getNoCategorie());
			rqt.setInt(9, nouvelArticle.getUtilisateur().getNoUtil());

			int nbLignesAffectees = rqt.executeUpdate();
			if (nbLignesAffectees == 0) {
				throw new Exception("Aucune ligne n'a été ajoutée en base");
			}
			verif = 1;
			ResultSet clefs = rqt.getGeneratedKeys();
			int clefAutoGeneree = -1;
			if (clefs.next()) {
				clefAutoGeneree = clefs.getInt(1);
				nouvelArticle.setNoArticle(clefAutoGeneree);

				PreparedStatement rqt2 = cnx.prepareStatement(SQL_INSERT_ENCHERE);

				rqt2.setDate(1, java.sql.Date.valueOf(nouvelArticle.getDateDebutEncheres()));
				rqt2.setInt(2, nouvelArticle.getMiseAPrix());
				rqt2.setInt(3, nouvelArticle.getNoArticle());
				rqt2.setInt(4, nouvelArticle.getUtilisateur().getNoUtil());

				int nbLigne = rqt2.executeUpdate();
				if (nbLigne == 0) {
					throw new Exception("Aucune ligne n'a été ajoutée en base");
				}
				verif = 2;

			}

			PreparedStatement rqt3 = cnx.prepareStatement(SQL_INSERT_RETRAIT);

			rqt3.setInt(1, nouvelArticle.getNoArticle());
			rqt3.setString(2, nouvelArticle.getRetrait().getRue());
			rqt3.setInt(3, nouvelArticle.getRetrait().getCodePostal());
			rqt3.setString(4, nouvelArticle.getRetrait().getVille());

			int nbLignes = rqt3.executeUpdate();
			if (nbLignes == 0) {
				throw new Exception("Erreur à la création du retrait");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return verif;
	}

	
	private static final String SQL_SELECT_ARTICLE = "SELECT nom_article,description, "
			+ "date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,"
			+ " ARTICLES_VENDUS.no_categorie,CATEGORIES.libelle, RETRAITS.rue,RETRAITS.code_postal, "
			+ "RETRAITS.ville, pseudo, nom,prenom FROM ARTICLES_VENDUS,CATEGORIES,RETRAITS,UTILISATEURS"
			+ " WHERE ARTICLES_VENDUS.no_article = RETRAITS.no_article "
			+ "AND CATEGORIES.no_categorie = ARTICLES_VENDUS.no_categorie"
			+ " AND ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur AND ARTICLES_VENDUS.no_article = ?";
	//Selection des éléments d'articles, utilisateur,retrait et catégorie par numéro d'article
	public Article detailVente(int detailArticle) throws SQLException {
		Article nouvelArticle = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(SQL_SELECT_ARTICLE);
			rqt.setInt(1, detailArticle);
			ResultSet rs = rqt.executeQuery();
			if (rs.next()) {
				nouvelArticle = new Article();
				nouvelArticle.setNomArticle(rs.getString(1));
				nouvelArticle.setDescription(rs.getString(2));
				nouvelArticle.setDateDebutEncheres(rs.getDate(3).toLocalDate());
				nouvelArticle.setDateFinEncheres(rs.getDate(4).toLocalDate());
				nouvelArticle.setMiseAPrix(rs.getInt(5));
				nouvelArticle.setPrixVente(rs.getInt(6));
				nouvelArticle.getUtilisateur().setNoUtil(rs.getInt(7));
				nouvelArticle.getNoCategorie().setNoCategorie(rs.getInt(8));
				nouvelArticle.getNoCategorie().setLibelle(rs.getString(9));
				nouvelArticle.getRetrait().setRue(rs.getString(10));
				nouvelArticle.getRetrait().setCodePostal(rs.getInt(11));
				nouvelArticle.getRetrait().setVille(rs.getString(12));
				nouvelArticle.getUtilisateur().setPseudo(rs.getString(13));
				nouvelArticle.getUtilisateur().setNom(rs.getString(14));
				nouvelArticle.getUtilisateur().setPrenom(rs.getString(15));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nouvelArticle;
	}

	private static final String SQL_UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET prix_vente=? , no_encherisseur =? WHERE no_article=? ";
	//Mise à jour de l'article lors d'une enchère
	public void addEnchere(int montant, int noEncherisseur, int noArticle) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(SQL_UPDATE_ARTICLE);
			rqt.setInt(1, montant);
			rqt.setInt(2, noEncherisseur);
			rqt.setInt(3, noArticle);
			rqt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Récupération du prix de vente
	private static final String SQL_SELECT_MONTANT = "SELECT prix_vente FROM ARTICLES_VENDUS WHERE no_article=? ";
	public int recupMontant(int idArticle) {
		int montant = 0;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(SQL_SELECT_MONTANT);
			rqt.setInt(1, idArticle);
			ResultSet rs = rqt.executeQuery();
			if (rs.next()) {
				montant = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return montant;
	}

	}
