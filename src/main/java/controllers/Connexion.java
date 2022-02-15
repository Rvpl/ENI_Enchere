package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ConnecterManager;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connecter")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnecterManager connexionMng;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        connexionMng = new ConnecterManager();
       }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Connexion.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = null;
		String mdp = null;
		
		if(request.getParameter("login") != null) {
			login = request.getParameter("login").trim();
		}
		
		if(request.getParameter("password") != null) {
			mdp = request.getParameter("password").trim();
		}
		
		int identifiant = connexionMng.selectUtilisateur(login, mdp);
		if(identifiant != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", identifiant);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
			if (rd != null) {
				rd.forward(request, response);
			}
		}else {
			System.out.println("Utilisateur inconnu");
		}
	}

}
