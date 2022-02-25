package controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.BLLException;
import bll.DetailVenteManager;
import bll.utilisateurBLL;
import bo.Article;
import bo.Utilisateur;

/**
 * Servlet implementation class EnchereServlet
 */
@WebServlet("/detailVente")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailVenteManager detailVenteMng;
	private utilisateurBLL userMng;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailVenteServlet() {
		super();
		detailVenteMng = new DetailVenteManager();
		userMng = new utilisateurBLL();
		

// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Article article = detailVenteMng.selectAll(Integer.parseInt(request.getParameter("noArticle")));			
			Utilisateur numEnchere = userMng.selectEnchere(article.getNoEnchere());
			System.out.println(article.getNoEnchere());
			request.setAttribute("numEnchere", numEnchere.getPseudo());
			
			request.setAttribute("nomArticle", article.getNomArticle());
			request.setAttribute("description", article.getDescription());
			request.setAttribute("categorie", article.getNoCategorie().getLibelle());
			request.setAttribute("prixVente", article.getPrixVente());
			request.setAttribute("miseAPrixStr", article.getMiseAPrix());
			request.setAttribute("dateFinEncheresStr", article.getDateFinEncheres());
			request.setAttribute("rue", article.getRetrait().getRue());
			request.setAttribute("codePostal", article.getRetrait().getCodePostal());
			request.setAttribute("ville", article.getRetrait().getVille());
			request.setAttribute("pseudo", article.getUtilisateur().getPseudo());
			HttpSession session = request.getSession();
			session.setAttribute("idArticle", Integer.parseInt(request.getParameter("noArticle")));
			request.setAttribute("noArticle", request.getParameter("noArticle"));
		} catch (BLLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String montantStr = null;
		int montant = 0;
		HttpSession session = request.getSession();

		int idArticle = (Integer) session.getAttribute("idArticle");
		try {
			montantStr = request.getParameter("enchere");
			montant = Integer.parseInt(montantStr);

			Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
			if (detailVenteMng.addEnchere(montant, ((Utilisateur) session.getAttribute("utilisateur")).getNoUtil(),
					idArticle, user.getCredit()) == false) {
				Article article = detailVenteMng.selectAll(idArticle);
				request.setAttribute("nomArticle", article.getNomArticle());
				request.setAttribute("description", article.getDescription());
				request.setAttribute("categorie", article.getNoCategorie().getLibelle());
				request.setAttribute("prixVente", article.getPrixVente());
				request.setAttribute("miseAPrixStr", article.getMiseAPrix());
				request.setAttribute("dateFinEncheresStr", article.getDateFinEncheres());
				request.setAttribute("rue", article.getRetrait().getRue());
				request.setAttribute("codePostal", article.getRetrait().getCodePostal());
				request.setAttribute("ville", article.getRetrait().getVille());
				request.setAttribute("pseudo", article.getUtilisateur().getPseudo());
				
				

				
			} else {
				response.sendRedirect(request.getContextPath() + "/home");
			}
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
		}

	}

}