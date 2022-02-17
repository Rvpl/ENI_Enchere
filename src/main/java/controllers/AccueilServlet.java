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
import bo.Article;
import bo.Utilisateur;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/home")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager ArticleMng;
	private List<Article>articles;
	private List<Article>articlesBN;
	private List<Utilisateur> users;
	private List<Utilisateur> usersBN;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
        ArticleMng = new ArticleManager();
        articles = new ArrayList<>();
        articlesBN = new ArrayList<>();
        users = new ArrayList<>();
        usersBN = new ArrayList<>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession();	
		articles = ArticleMng.getArticles();
		users = ArticleMng.getUser();
		
		request.setAttribute("users", users);
		request.setAttribute("articles", articles);
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		if (rd != null) {
			rd.forward(request, response);					
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtre = null;
		if(request.getParameter("filtre")!= null) {
			filtre = request.getParameter("filtre");
		}
		usersBN = ArticleMng.getUserBN(filtre);	
		articlesBN = ArticleMng.select(filtre);
		request.setAttribute("usersBN", usersBN);	
		request.setAttribute("articlesBN", articlesBN);	
		
		doGet(request, response);
	}

}
