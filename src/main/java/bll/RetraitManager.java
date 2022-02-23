package bll;

import bo.Retrait;

import dal.RetraitArticleJdbc;

public class RetraitManager {

	private RetraitArticleJdbc retraitMng;

	public RetraitManager() {
		super();
		retraitMng = new RetraitArticleJdbc();
	}

	public int addRetrait(Retrait retraitArticle) {

		return retraitMng.addRetrait(retraitArticle);
	}

}
