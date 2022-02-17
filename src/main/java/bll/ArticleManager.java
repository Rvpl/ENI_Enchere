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
	
	public List<Article> select(String nomArticle) {
		
		return articleMng.select(nomArticle);
	}
	
	public List<Article> getArticles() {
		return articleMng.getArticles();
	}
	
	public List<Utilisateur> getUserBN(String nomArticle){
		return articleMng.getUsersBN();
	}
	
	public List<Utilisateur> getUser(){
		return articleMng.getUsers();
	}
}
