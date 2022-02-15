package bll;

import dal.ArticleJdbcImpl;
import dal.ConnexionJdbcImpl;

public class ArticleManager {
	
	private ArticleJdbcImpl articleMng;
	
	public ArticleManager() {
		super();
		articleMng = new ArticleJdbcImpl();
	}
	
	public int select(String nomArticle) {
		
		return articleMng.select(nomArticle);
	}
}
