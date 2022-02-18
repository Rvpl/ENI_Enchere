package bo;

import java.time.LocalDate;

public class Article {
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private Utilisateur noUtilisateur;
	private Categorie noCategorie;
	private String nomUtil;
	
	
	public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
	}


	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int noUtilisater, int noCategorie) {
		super();
		this.noUtilisateur = new Utilisateur();
		this.noCategorie = new Categorie();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur.setNoUtil(noUtilisater);;
		this.noCategorie.setNoCategorie(noCategorie);;
		nomUtil = noUtilisateur.getNom();
		
	}


	public Article() {
		this.noUtilisateur = new Utilisateur();
		this.noCategorie = new Categorie();
		nomUtil = noUtilisateur.getNom();
	}


	public Article(String nomArticle2, String description2, LocalDate dateDebutEncheres2, LocalDate dateFinEncheres2,int miseAPrix2, int idUtil, int categorie) {
		this.noUtilisateur = new Utilisateur();
		this.noCategorie = new Categorie();
		this.nomArticle = nomArticle2;
		this.description = description2;
		this.dateDebutEncheres = dateDebutEncheres2;
		this.dateFinEncheres = dateFinEncheres2;
		this.miseAPrix = miseAPrix2;
		this.noUtilisateur.setNoUtil(idUtil);
		this.noCategorie.setNoCategorie(categorie);
		nomUtil = noUtilisateur.getNom();
	}


	public int getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}


	public String getNomArticle() {
		return nomArticle;
	}


	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}


	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}


	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}


	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}


	public int getMiseAPrix() {
		return miseAPrix;
	}


	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}


	public int getPrixVente() {
		return prixVente;
	}


	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}


	public Utilisateur getUtilisateur() {
		return noUtilisateur;
	}


	public void setUtilisateur(Utilisateur noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	


	public Categorie getNoCategorie() {
		return noCategorie;
	}


	public void setNoCategorie(Categorie noCategorie) {
		this.noCategorie = noCategorie;
	}


	@Override
	public String toString() {
		return "Articles [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", noUtilisateur=" + noUtilisateur + "]";
	}


	public String getNomUtil() {
		return nomUtil = noUtilisateur.getNom();
	}
	
	
	
}
