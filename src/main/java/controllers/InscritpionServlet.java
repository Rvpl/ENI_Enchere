package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.utilisateurBLL;
import bo.Utilisateur;

/**
 * Servlet implementation class InscritpionServlet
 */
@WebServlet("/inscription")
public class InscritpionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private utilisateurBLL userMng;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscritpionServlet() {
		super();
		userMng = new utilisateurBLL();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prenom = null;
		String nom = null;
		String pseudo = null;
		String email = null;
		String telephoneStr = null;
		int tel = 0;
		String rue = null;
		String codePostalStr = null;
		int cp = 0;
		String ville = null;
		int credit = 100;
		String motDePasse = null;
		String administrateur = null;
		String confirmationMDP = null;
		int exist = 0;;
		
		
		try {
			pseudo = request.getParameter("pseudo").trim();
			
			prenom = request.getParameter("Prenom").trim();

			telephoneStr = request.getParameter("telephone").trim();
			tel = Integer.parseInt(telephoneStr);
			
			codePostalStr = request.getParameter("codePostal").trim();
			cp = Integer.parseInt(codePostalStr);

			if(request.getParameter("confirmationMDP").trim().equals(request.getParameter("motdePasse"))){
				motDePasse = request.getParameter("motDePasse").trim();
			}

			nom = request.getParameter("nom").trim();

			email = request.getParameter("email").trim();

			rue = request.getParameter("rue").trim();

			ville = request.getParameter("ville").trim();

			Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, motDePasse);
			
			exist = userMng.insert(user);
			
			if (exist != 0) {
				request.setAttribute("exist", exist);
				request.setAttribute("pseudo", pseudo);
				request.setAttribute("nom", nom);
				request.setAttribute("prenom", prenom);
				request.setAttribute("email", email);
				request.setAttribute("tel", tel);
				request.setAttribute("rue", rue);
				request.setAttribute("cp", cp);
				request.setAttribute("ville", ville);
				doGet(request, response);
			}
			response.sendRedirect(request.getContextPath()+"/home");
			
		} catch (BLLException e) {
			request.setAttribute("exist", exist);
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("tel", tel);
			request.setAttribute("rue", rue);
			request.setAttribute("cp", cp);
			request.setAttribute("ville", ville);
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp").forward(request, response);
			e.printStackTrace();
		}catch(NumberFormatException e) {
			request.setAttribute("exist", exist);
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("tel", tel);
			request.setAttribute("rue", rue);
			request.setAttribute("cp", cp);
			request.setAttribute("ville", ville);
			request.setAttribute("error", "Veuillez saisir des chiffresdans la/les colonnes Téléphone/Code postal");
			request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

}
