package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleManager;
import bo.Articles;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/home")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager ArticleMng;
	private List<Articles>articles;
	private List<Articles>articlesBN;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
        ArticleMng = new ArticleManager();
        articles = new ArrayList<>();
        articlesBN = new ArrayList<>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession();		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		if (rd != null) {
			articles = ArticleMng.getArticles();
			request.setAttribute("articles", articles);
			rd.forward(request, response);
		}
		request.setAttribute("articlesBN", null);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtre = null;
		if(request.getParameter("filtre")!= null) {
			filtre = request.getParameter("filtre");
		}
		articlesBN = ArticleMng.select(filtre);
		request.setAttribute("articlesBN", articlesBN);	
		
		doGet(request, response);
	}

}
