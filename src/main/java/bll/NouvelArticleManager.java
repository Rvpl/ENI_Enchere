package bll;

import bo.Article;
import dal.CreationArticleJdbc;

public class NouvelArticleManager {

	private CreationArticleJdbc creationArticleMng;

	public NouvelArticleManager() {
		super();
		creationArticleMng = new CreationArticleJdbc();
	}

	public int addArticle(Article nouvelArticle) {

		return creationArticleMng.addArticle(nouvelArticle);
	}

}
