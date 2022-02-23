package bll;

import java.util.List;

import bo.Article;
import bo.Utilisateur;
import dal.ArticleJdbcImpl;

public class ArticleManager {

	private ArticleJdbcImpl articleMng;

	public ArticleManager() {
		super();
		articleMng = new ArticleJdbcImpl();
	}

	public List<Article> select(String nomArticle, int categorie) {

		return articleMng.select(nomArticle, categorie);
	}

	public List<Article> getArticles(String nomArticle) {
		return articleMng.getArticles(nomArticle);
	}

	public List<Utilisateur> getUserBN(String nomArticle) {
		return articleMng.getUsersBN();
	}

	public List<Utilisateur> getUser() {
		return articleMng.getUsers();
	}
}
