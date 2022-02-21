package bo;

public class Retrait {
<<<<<<< HEAD
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

=======
	
	private String rue;
	private String code_postal;
	private String ville;
	private Article no_article;
	
	
	public Retrait() {
		super();
	}
	
	public Retrait(String rue, String code_postal, String ville) {
		super();
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		
	}
	
	public Retrait(String rue, String code_postal, String ville, Article no_article) {
		super();
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.no_article = no_article;
	}
	public Retrait(String rue, String code_postal, String ville, int no_article1) {
		super();
		this.no_article = new Article();
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.no_article.setNoArticle(no_article1);
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCode_postal() {
		return code_postal;
	}


	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public Article getNo_article() {
		return no_article;
	}


	public void setNo_article(Article no_article) {
		this.no_article = no_article;
	}
	
	
>>>>>>> branch 'main' of https://github.com/Rvpl/ENI_Enchere.git
	
}
