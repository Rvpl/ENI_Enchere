package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Retrait;

public class RetraitArticleJdbc {

	private static final String SQL_INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article,rue, code_postal, ville) VALUES (?, ?, ?, ?)";

	public int addRetrait(Retrait retraitArticle) {

		int retrait = 0;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement rqt = cnx.prepareStatement(SQL_INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);

			rqt.setInt(1, retraitArticle.getNo_article());
			rqt.setString(2, retraitArticle.getRue());
			rqt.setInt(3, retraitArticle.getCodePostal());
			rqt.setString(4, retraitArticle.getVille());

			int nbLignesAffectees = rqt.executeUpdate();
			if (nbLignesAffectees == 0) {
				throw new Exception("Aucune ligne n'a été ajoutée en base");
			}
			retrait = 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retrait;

	}
}
