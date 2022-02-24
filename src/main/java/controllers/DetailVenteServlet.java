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
import bo.Article;
import bo.Utilisateur;

/**
 * Servlet implementation class EnchereServlet
 */
@WebServlet("/detailVente")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailVenteManager detailVenteMng;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailVenteServlet() {
		super();
		detailVenteMng = new DetailVenteManager();

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
		String nom = null;
		String description = null;
		String cate = null;
		int prixVente = 0;
		int miseAPrix = 0;
		LocalDate dateFin = null;
		String rue = null;
		int cp = 0;
		String ville = null;
		String pseudo = null;

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
				
				nom = article.getNomArticle();
				description = article.getDescription();
				cate = article.getNoCategorie().getLibelle();
				prixVente = article.getPrixVente();
				miseAPrix = article.getMiseAPrix();
				dateFin = article.getDateFinEncheres();
				rue = article.getRetrait().getRue();
				ville = article.getRetrait().getVille();
				pseudo = article.getUtilisateur().getPseudo();
				System.out.println(nom+" "+pseudo);
				
			} else {
				response.sendRedirect(request.getContextPath() + "/home");
			}
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			request.setAttribute("nomArticle", nom);
			request.setAttribute("description", description);
			request.setAttribute("categorie", cate);
			request.setAttribute("prixVente", prixVente);
			request.setAttribute("miseAPrixStr", miseAPrix);
			request.setAttribute("dateFinEncheresStr", dateFin);
			request.setAttribute("rue",rue);
			request.setAttribute("codePostal", cp);
			request.setAttribute("ville", ville);
			request.setAttribute("pseudo", pseudo);
			doGet(request, response);
			e.printStackTrace();
		}

	}

}