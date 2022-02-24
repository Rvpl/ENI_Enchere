package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.BLLException;
import bll.utilisateurBLL;
import bo.Utilisateur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connecter")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private utilisateurBLL userMng;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
		userMng = new utilisateurBLL();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupération des sessions
		request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Connexion.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = null;
		String mdp = null;
		
		login = request.getParameter("login").trim();
		mdp = request.getParameter("password").trim();
		

		Utilisateur identifiant = null;
		try {
			identifiant = userMng.selectUtilisateur(login, mdp);
			if (identifiant != null) {
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", identifiant);
				response.sendRedirect(request.getContextPath() + "/home");	
			} else {
				request.setAttribute("introuvable", 0);
				request.getRequestDispatcher("/WEB-INF/JSP/Connexion.jsp").forward(request, response);

			}		
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/JSP/Connexion.jsp").forward(request, response);
			e.printStackTrace();
		}
		
	}

}
