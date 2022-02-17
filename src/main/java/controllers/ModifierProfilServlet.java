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
        // TODO Auto-generated constructor stub
    } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		int tel= 0;
		int cp = 0;
		
		if(request.getParameter("new pseudo") != null) {
			pseudo = request.getParameter("new pseudo").trim();
		}
		if(request.getParameter("new Prenom") != null) {
			prenom = request.getParameter("new Prenom").trim();
		}
		if(request.getParameter("new telephone") != null) {
			telephoneStr = request.getParameter("new telephone").trim();
			tel = Integer.parseInt(telephoneStr);
		}
		if(request.getParameter("new codePostal") != null) {
			codePostalStr = request.getParameter("new codePostal").trim();
			cp = Integer.parseInt(codePostalStr);
		}
		
		
		if(request.getParameter("nom") != null) {
			nom = request.getParameter("nom").trim();
		}
		
		if(request.getParameter("newMDP") != null) {
			motDePasse = request.getParameter("newMDP").trim();
		}
		
		if(request.getParameter("new email") != null) {
			email = request.getParameter("new email").trim();
		}
		
		
		if(request.getParameter("new rue") != null) {
			rue = request.getParameter("new rue").trim();
		}
		
		
		if(request.getParameter("new ville") != null) {
			ville = request.getParameter("new ville").trim();
		}
		
		if(request.getParameter("motDePasseActuel") == request.getParameter("motDePasse")) {
			if(request.getParameter("newMDP") != null && request.getParameter("confirmationMDP") == motDePasse) {			
				motDePasse = request.getParameter("newMDP").trim();
				}
			} 
		
		
		Utilisateur user = new Utilisateur(pseudo,nom,prenom,email,tel,rue,cp,ville,motDePasse);
		
		// SI l'utilisateur existe déjà en BDD on renvoie l'identifiant de l'user existant
		int exist = 0;//modificationProfilMng.update(user);
		if(exist == 0) {
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
		
		
		
		
	
		
		
		
	}

}

