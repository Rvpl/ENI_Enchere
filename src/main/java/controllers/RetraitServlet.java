package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Retrait;
import dal.RetraitArticleJdbc;

/**
 * Servlet implementation class RetraitServlet
 */
@WebServlet("/retrait")
public class RetraitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RetraitArticleJdbc retraitMng;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetraitServlet() {
        super();
        // TODO Auto-generated constructor stub
        retraitMng = new RetraitArticleJdbc();
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
		
		int no_article = 0;
		String rue = null;
		String code_postal = null;
		String ville = null;
		
		if(request.getParameter("rue") != null) {
			rue = request.getParameter("rue").trim();
		}
		if(request.getParameter("code_postal") != null) {
			code_postal = request.getParameter("code_postal").trim();
		}
		if(request.getParameter("ville") != null) {
			ville = request.getParameter("ville").trim();
			
		}
		
		Retrait ret = new Retrait(rue, code_postal, ville);
		
		doGet(request, response);
	}

}
