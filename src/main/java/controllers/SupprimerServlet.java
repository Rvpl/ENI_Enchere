package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.SuppressionManager;
import bo.Utilisateur;

/**
 * Servlet implementation class SupprimerServlet
 */
@WebServlet("/supprimer")
public class SupprimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SuppressionManager deleteMng;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerServlet() {
        super();
        deleteMng = new SuppressionManager();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = null;
		int id = 0;
		request.getSession();
		HttpSession session = request.getSession();
		session.getAttribute("utilisateur");
		
		if(request.getParameter("id")!= null) {
			idStr = request.getParameter("id");
			id = Integer.parseInt(idStr);
		}
		deleteMng.delete(id);
		session.invalidate();
		
        
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

}
