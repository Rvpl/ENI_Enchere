package bo;

public class Retrait {
	private int no_article;
	private String rue;
	private int codePostal;
	private String ville;
	
	public Retrait() {
	}


	public Retrait(int noArticle, String rue, int cp, String ville) {
		this.setNo_article(noArticle);
		this.rue = rue;
		this.codePostal = cp;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}


	public int getNo_article() {
		return no_article;
	}


	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

}
