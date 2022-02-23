package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.DetailVenteManager;
import bo.Article;

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
			if (detailVenteMng.selectAll(Integer.parseInt(request.getParameter("noArticle"))) != null) {
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

			}
		} catch (NumberFormatException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp").forward(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String montantStr= null;
		int montant=0;
		HttpSession session = request.getSession();
		
		
		int idArticle  = (Integer)session.getAttribute("idArticle");
		try {
			if(request.getParameter("enchere")!= null) {
				montantStr= request.getParameter("enchere");
				montant=Integer.parseInt(montantStr);
				if(detailVenteMng.addEnchere(montant,idArticle) == false) {
					request.setAttribute("message", "Veuillez entrer une valeur supérieure à la dernière offre");
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
						request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp").forward(request, response);
				}else {
					response.sendRedirect(request.getContextPath() + "/home");
				}
			}
		} catch (NumberFormatException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

	}

}