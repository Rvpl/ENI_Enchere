package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.BLLException;
import bll.DetailVenteManager;
import bo.Article;

/**
 * Servlet implementation class Acquisition
 */
@WebServlet("/Acquisition")
public class Acquisition extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailVenteManager detailVenteMng;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Acquisition() {
    	super();
    	detailVenteMng = new DetailVenteManager();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			request.setAttribute("tel", article.getUtilisateur().getNumero());
			HttpSession session = request.getSession();
			session.setAttribute("idArticle", Integer.parseInt(request.getParameter("noArticle")));
			request.setAttribute("noArticle", request.getParameter("noArticle"));
		} catch (BLLException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.getRequestDispatcher("/WEB-INF/JSP/PageAcquisition.jsp").forward(request, response);
				}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp").forward(request, response);
	}

}
