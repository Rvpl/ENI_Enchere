package bll;

import java.util.ArrayList;
import java.util.List;

import bo.Article;
import dal.ArticleJdbc;
import dal.DALException;

public class articleBLL {
	
	private ArticleJdbc articleMng;
	
	public articleBLL() {
		super();
		articleMng = new ArticleJdbc();
	}

	public Article selectAll(int detailArticle) throws BLLException {
		Article unArticle = null;
		try {
			if(detailArticle < 1) {
				throw  new BLLException("Article introuvable");
			}else {
				unArticle = articleMng.detailVente(detailArticle);
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		return unArticle;
	}
	
	public int recupMontant(int idArticle) throws BLLException {
		int montant = 0;
		try {
			montant = articleMng.recupMontant(idArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return montant;
	}
	
	public boolean addEnchere(int montant, int noEncherisseur, int idArticle) throws BLLException {
		boolean ok = false;
		
		int montantInit = recupMontant(idArticle);
		
		if(montantInit < montant) {
			try {
				articleMng.addEnchere(montant,noEncherisseur, idArticle);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ok = true;
		}else {
			ok = false;
		}
		return ok;
		
		}

	public int addArticle(Article nouvelArticle) throws BLLException {
		int id = 0;
		if(nouvelArticle.getNomArticle().isEmpty() || nouvelArticle.getNomArticle().isBlank()) {
			throw new BLLException("Nom article obligatoire");
		}
		if(nouvelArticle.getDescription().isEmpty() || nouvelArticle.getDescription().isBlank()) {
			throw new BLLException("Description article obligatoire");
		}
		if(nouvelArticle.getDateDebutEncheres().isAfter(nouvelArticle.getDateFinEncheres())){
			throw new BLLException("La date de début d'enchère ne peut être supérieur à la date de fin");
		}
		if(nouvelArticle.getMiseAPrix() < 0) {
			throw new BLLException("Veuillez entrer une valeur positive pour le prix de départ");
		}
		if(nouvelArticle.getNoCategorie().getNoCategorie() != 1 && nouvelArticle.getNoCategorie().getNoCategorie() != 2 
				&& nouvelArticle.getNoCategorie().getNoCategorie() != 3 && nouvelArticle.getNoCategorie().getNoCategorie() != 4) {
			throw new BLLException("Impossible de modifier le numéro de catégorie");
		}

		try {
			id = articleMng.addArticle(nouvelArticle);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public List<Article> select(String nomArticle, int categorie) {
		List<Article> uneListe = new ArrayList<>();
		try {
			uneListe = articleMng.select(nomArticle, categorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uneListe;
	}

	public List<Article> getArticles(String nomArticle) {
		List<Article> uneListe = new ArrayList<>();
		try {
			uneListe = articleMng.getArticles(nomArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uneListe;
	}


}
