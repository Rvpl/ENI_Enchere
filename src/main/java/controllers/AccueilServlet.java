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
import javax.servlet.http.HttpSession;

import bll.articleBLL;
import bll.utilisateurBLL;
import bo.Article;
import bo.Utilisateur;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/home")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private articleBLL articleMng;
	private utilisateurBLL userMng;
	private List<Article> articles;
	private List<Article> articlesBN;
	private List<Utilisateur> users;
	private List<Utilisateur> usersBN;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServlet() {
		super();
		articleMng = new articleBLL();
		userMng = new utilisateurBLL();
		articles = new ArrayList<>();
		articlesBN = new ArrayList<>();
		users = new ArrayList<>();
		usersBN = new ArrayList<>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int categorie = -1;
		String rech = "";
		request.getSession();
		HttpSession session = request.getSession();
		String[] tabCategorie = this.getServletContext().getInitParameter("categorie").split(";");
		request.setAttribute("categorie", tabCategorie);

		if (request.getParameter("choixCategorie") == null) {
			categorie = 0;
		}
		if (categorie == 0) {
			articles = articleMng.select(rech, categorie);
		}


		request.setAttribute("users", users);
		request.setAttribute("articles", articles);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
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
		String filtre = null;
		String categorieStr = null;
		int categorie = -1;
		if (request.getParameter("filtre") != null) {
			filtre = request.getParameter("filtre");
		}
		if (request.getParameter("choixCategorie") != null) {
			categorieStr = request.getParameter("choixCategorie");
			if (categorieStr.equals("Toutes")) {
				categorie = 0;
			}
			if (categorieStr.equals("informatique")) {
				categorie = 1;
			}
			if (categorieStr.equals("Ameublement")) {
				categorie = 2;
			}
			if (categorieStr.equals("VÃªtement")) {
				categorie = 3;
			}
			if (categorieStr.equals("Sport/Loisirs")) {
				categorie = 4;
			}
		}

		if (categorie == 0) {
			articlesBN = articleMng.getArticles(filtre);
		} else {
			articlesBN = articleMng.select(filtre, categorie);
		}
		usersBN = articleMng.getUserBN(filtre);

		request.setAttribute("usersBN", usersBN);
		request.setAttribute("articlesBN", articlesBN);

		doGet(request, response);
	}

}
