package bll;

import bo.Article;
import dal.CreationArticleJdbc;

public class NouvelArtcleManager {
	
	private CreationArticleJdbc creationArticleMng;

	public NouvelArtcleManager() {
		super();
		creationArticleMng = new CreationArticleJdbc();
	}
	
	public int addArticle (Article nouvelArticle) {
		
		return creationArticleMng.addArticle(nouvelArticle);
	}
	
}
