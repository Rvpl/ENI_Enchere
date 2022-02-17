package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Utilisateur;
import dal.ModificationProfilJdbc;

/**
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/modificationProfil") 
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private ModificationProfilJdbc modificationProfilMng;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfilServlet() {
    	 super();
    	 modificationProfilMng = new ModificationProfilJdbc();
    	
    } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModifierProfil.jsp");
		if (rd != null) {
			rd.forward(request, response); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prenom = null ;
		String nom = null ;
		String pseudo= null ;
		String email= null ;
		String telephoneStr= null ;
		String rue= null ;
		String ville= null ;
		String motDePasse= null ;
		String administrateur = null ; 
		String confimationMDP= null ; 
		String codePostalStr = null;
		String idStr = null;
		int tel= 0;
		int cp = 0;
		int id = 0;
		
		if(request.getParameter("pseudo") != null) {
			pseudo = request.getParameter("pseudo").trim();
		}
		if(request.getParameter("Prenom") != null) {
			prenom = request.getParameter("Prenom").trim();
		}
		if(request.getParameter("telephone") != null) {
			telephoneStr = request.getParameter("telephone").trim();
			tel = Integer.parseInt(telephoneStr);
		}
		if(request.getParameter("codePostal") != null) {
			codePostalStr = request.getParameter("codePostal").trim();
			cp = Integer.parseInt(codePostalStr);
		}
		
		
		if(request.getParameter("nom") != null) {
			nom = request.getParameter("nom").trim();
		}
		
		if(request.getParameter("nouveauMDP") != null) {
			motDePasse = request.getParameter("nouveauMDP").trim();
		}
		
		if(request.getParameter("email") != null) {
			email = request.getParameter("email").trim();
		}
		
		
		if(request.getParameter("rue") != null) {
			rue = request.getParameter("rue").trim();
		}
		
		
		if(request.getParameter("ville") != null) {
			ville = request.getParameter("ville").trim();
		}
		
		if(request.getParameter("id") != null) {
			idStr = request.getParameter("id").trim();
			id = Integer.parseInt(idStr);
			
		}
		
		if(request.getParameter("motDePasseActuel") == request.getParameter("verifMdp")) {
			if(request.getParameter("nouveauMDP") != null && request.getParameter("confirmationMDP") == request.getParameter("nouveauMDP")&& request.getParameter("confimationMDP")!= null) {			
				motDePasse = request.getParameter("nouveauMDP").trim();
				}
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
		
		
		
		int exist = modificationProfilMng.update(user);
		
		
		// SI l'utilisateur existe déjà en BDD on renvoie le message d'erreur
		exist = modificationProfilMng.update(user);
		if(exist == 2) {
			String error = "Pseudo déjà utilisé veuillez en choisir un autre";
			request.setAttribute("error", error);
			doGet(request, response);
		}else {

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/MonProfil.jsp");
			if (rd != null) {
				rd.forward(request, response); 
			}
		}
		
		
		
		
	
		
		
		
	}

}

