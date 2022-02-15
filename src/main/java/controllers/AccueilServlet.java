package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticleManager;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/home")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager ArticleMng;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
        ArticleMng = new ArticleManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtre = null;
		if(request.getParameter("filtre")!= null) {
			filtre = request.getParameter("filtre");
		}
		int num_article = ArticleMng.select(filtre);
		if(num_article != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("filtre", num_article);
		}
		
		doGet(request, response);
	}

}
