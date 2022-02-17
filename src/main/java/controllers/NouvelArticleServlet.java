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
		
		
		if(request.getParameter("nomArticle") != null) {
			nomArticle = request.getParameter("nomArticle").trim();
		}
		if(request.getParameter("description") != null) {
			description = request.getParameter("description").trim();
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
		
		Article article = new Article(nomArticle,description,dateDebutEncheres,dateFinEncheres,miseAPrix);
		
		int exist = creaArticleMng.addArticle(article);
		if(exist == 0) {
			
			request.setAttribute("nomArticle", nomArticle);
			request.setAttribute("description", description);
			request.setAttribute("date_debut", dateDebutEncheres);
			request.setAttribute("date_fin", dateFinEncheres);
			request.setAttribute("prix", miseAPrix);

			doGet(request, response);
		}
		
	}

}
