package bo;

public class Retrait {
	
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
	
	
	
}
