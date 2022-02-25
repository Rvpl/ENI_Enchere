package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.articleBLL;
import bo.Article;
import bo.Retrait;

/**
 * Servlet implementation class NouvelArticleServlet
 */
@WebServlet("/newArticle")
public class NouvelArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private articleBLL articleMng;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NouvelArticleServlet() {
		super();
		articleMng = new articleBLL();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession();
		String[] tabCategorie = this.getServletContext().getInitParameter("categorie").split(";");
		request.setAttribute("categorie", tabCategorie);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
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

		String nomArticle = null;
		String description = null;
		LocalDate dateDebutEncheres = null;
		String dateDebutEncheresStr = null;
		LocalDate dateFinEncheres = null;
		String dateFinEncheresStr = null;
		int miseAPrix = 0;
		String miseAPrixStr = null;
		String idUtilStr = null;
		int idUtil = 0;
		String categorieStr = null;
		int categorie = 0;
		String ville = null;
		int cp = 0;
		String rue = null;

		
		try {
			nomArticle = request.getParameter("nomArticle").trim();

			description = request.getParameter("description").trim();

			categorieStr = request.getParameter("choixCategorie").trim();

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

			miseAPrixStr = request.getParameter("prix").trim();
			miseAPrix = Integer.parseInt(miseAPrixStr);

			dateDebutEncheresStr = request.getParameter("date_debut").trim();
			dateDebutEncheres = LocalDate.parse(dateDebutEncheresStr);

			dateFinEncheresStr = request.getParameter("date_fin").trim();
			dateFinEncheres = LocalDate.parse(dateFinEncheresStr);

			idUtilStr = request.getParameter("util").trim();
			idUtil = Integer.parseInt(idUtilStr);

			rue = request.getParameter("rue");

			cp = Integer.parseInt(request.getParameter("codePostal"));

			ville = request.getParameter("ville");

			Article article = new Article(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix,
					idUtil, categorie);
			Retrait retrait = new Retrait();
			retrait.setRue(rue);
			retrait.setVille(ville);
			retrait.setCodePostal(cp);
			retrait.setNo_article(article.getNoArticle());
			article.setRetrait(retrait);

			int exist = 0;
			exist = articleMng.addArticle(article);
			
			if (exist != 0) {

				response.sendRedirect(request.getContextPath()+"/home");
			}
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
		} catch(NumberFormatException n) {
			request.setAttribute("error", "veuillez entrer uniquement des nombres pour les champs nécessaires");
			doGet(request, response);
			n.printStackTrace();
		}catch(DateTimeParseException d) {
			request.setAttribute("error", "veuillez entrer des dates valides");
			doGet(request, response);
			d.printStackTrace();
		}
		
		

	}

}
