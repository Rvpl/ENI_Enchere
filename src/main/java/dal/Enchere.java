package dal;

import java.time.LocalDate;

public class Enchere {

	private int no_enchere;
	private LocalDate date_debut;
	private int montant;
	private int no_article;
	private int no_util;
	
	
	public Enchere() {
		
	}
	public Enchere(int no_enchere, LocalDate date_debut,int montant, int no_article, int no_util) {
		
		this.no_enchere = no_enchere;
		this.date_debut = date_debut;
		this.montant = montant;
		this.no_article = no_article;
		this.no_util = no_util;
	}

	public int getNo_enchere() {
		return no_enchere;
	}

	public void setNo_enchere(int no_enchere) {
		this.no_enchere = no_enchere;
	}

	public LocalDate getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(LocalDate date_debut) {
		this.date_debut = date_debut;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public int getNo_util() {
		return no_util;
	}

	public void setNo_util(int no_util) {
		this.no_util = no_util;
	}

}
