package bll;

import java.util.List;

import bo.Articles;
import bo.Utilisateur;
import dal.ArticleJdbcImpl;

public class ArticleManager {
	
	private ArticleJdbcImpl articleMng;
	
	public ArticleManager() {
		super();
		articleMng = new ArticleJdbcImpl();
	}
	
	public List<Articles> select(String nomArticle) {
		
		return articleMng.select(nomArticle);
	}
	
	public List<Articles> getArticles() {
		return articleMng.getArticles();
	}
	
	public List<Utilisateur> getUserBN(String nomArticle){
		return articleMng.getUsersBN();
	}
	
	public List<Utilisateur> getUser(){
		return articleMng.getUsers();
	}
}
