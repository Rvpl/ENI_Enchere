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
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/modificationProfil")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private utilisateurBLL userMng;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProfilServlet() {
		super();
		userMng = new utilisateurBLL();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModifierProfil.jsp");
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
		String prenom = null;
		String nom = null;
		String pseudo = null;
		String email = null;
		String telephoneStr = null;
		String rue = null;
		String ville = null;
		String motDePasse = null;
		String codePostalStr = null;
		String idStr = null;
		int tel = 0;
		int cp = 0;
		int id = 0;

		int exist;
		try {
			pseudo = request.getParameter("pseudo").trim();

			prenom = request.getParameter("Prenom").trim();

			telephoneStr = request.getParameter("telephone").trim();
			tel = Integer.parseInt(telephoneStr);

			codePostalStr = request.getParameter("codePostal").trim();
			cp = Integer.parseInt(codePostalStr);

			nom = request.getParameter("nom").trim();

			motDePasse = request.getParameter("nouveauMDP").trim();

			email = request.getParameter("email").trim();

			rue = request.getParameter("rue").trim();

			ville = request.getParameter("ville").trim();

			idStr = request.getParameter("id").trim();
			id = Integer.parseInt(idStr);

			if (request.getParameter("motDePasseActuel").equals(request.getParameter("verifMdp"))) {
				if (request.getParameter("nouveauMDP") != null && request.getParameter("confirmationMDP").equals(request.getParameter("nouveauMDP"))) {
					motDePasse = request.getParameter("nouveauMDP").trim();
				}else {
					throw new BLLException("Veuillez entrer le même mot de passe");
				}
			}else {
				request.setAttribute("error", "Le mot de passe actuel n'est pas le bon");
				throw new BLLException("Le mot de passe actuel n'est pas le bon");
			}
			
			Utilisateur user = new Utilisateur();
			user.setNoUtil(id);
			user.setPseudo(pseudo);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setEmail(email);
			user.setCodePostal(cp);
			user.setMdp(motDePasse);
			user.setNumero(tel);
			user.setRue(rue);
			user.setVille(ville);

			exist = userMng.update(user);
			
			// SI l'utilisateur existe déjà en BDD on renvoie le message d'erreur
			if (exist == 2) {
				doGet(request, response);
				throw new BLLException("Pseudo déjà utilisé veuillez en choisir un autre");		
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/MonProfil.jsp");
				if (rd != null) {
					rd.forward(request, response);
				}
			}
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			
			doGet(request, response);
			e.printStackTrace();
		}

		

	}

}
