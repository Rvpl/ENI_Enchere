package controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Article;
import bo.Utilisateur;
import dal.CreationArticleJdbc;

/**
 * Servlet implementation class NouvelArticleServlet
 */
@WebServlet("/newArticle")
public class NouvelArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CreationArticleJdbc creaArticleMng;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelArticleServlet() {
        super();
        creaArticleMng = new CreationArticleJdbc();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession();
		String[] tabCategorie = this.getServletContext().getInitParameter("categorie").split(";");
		request.setAttribute("categorie", tabCategorie);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
		if (rd != null) {
			rd.forward(request, response); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomArticle = null;
		String description = null;
		LocalDate dateDebutEncheres = null;
		String dateDebutEncheresStr =null;
		LocalDate dateFinEncheres = null;
		String dateFinEncheresStr = null;
		int miseAPrix = 0;
		String miseAPrixStr = null;
		String idUtilStr = null;
		int idUtil = 0;
		String categorieStr = null;
		int categorie = 0;
		
		
		if(request.getParameter("nomArticle") != null) {
			nomArticle = request.getParameter("nomArticle").trim();
		}
		if(request.getParameter("description") != null) {
			description = request.getParameter("description").trim();
		}
		
		if(request.getParameter("choixCategorie") != null) {
			categorieStr = request.getParameter("choixCategorie").trim();
			if(categorieStr.equals("informatique")) {
				categorie = 1;
			}
			if(categorieStr.equals("Ameublement")) {
				categorie = 2;
			}
			if(categorieStr.equals("VÃªtement")) {
				categorie = 3;
			}
			if(categorieStr.equals("Sport/Loisirs")) {
				categorie = 4;
			}
		}
		
		if(request.getParameter("prix") != null) {
			miseAPrixStr = request.getParameter("prix").trim();
			miseAPrix = Integer.parseInt(miseAPrixStr);
		}
		if(request.getParameter("date_debut") != null) {
			dateDebutEncheresStr = request.getParameter("date_debut").trim();
			dateDebutEncheres = LocalDate.parse(dateDebutEncheresStr);
			
		}
		if(request.getParameter("date_fin") != null) {
			dateFinEncheresStr = request.getParameter("date_fin").trim();
			dateFinEncheres = LocalDate.parse(dateFinEncheresStr);
		}
		
		if(request.getParameter("util") != null) {
			idUtilStr = request.getParameter("util").trim();
			idUtil = Integer.parseInt(idUtilStr);
		}
		
		Article article = new Article(nomArticle,description,dateDebutEncheres,dateFinEncheres,miseAPrix,idUtil,categorie);
		
		int exist = creaArticleMng.addArticle(article);
		if(exist != 0) {

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
			if (rd != null) {
				rd.forward(request, response); 
			}
		}
		
		
		
	}

}
