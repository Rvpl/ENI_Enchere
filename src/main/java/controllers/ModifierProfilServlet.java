package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ModificationProfilManager;
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
		
		if(request.getParameter("Npseudo") != null) {
			pseudo = request.getParameter("Npseudo").trim();
		}
		if(request.getParameter("NPrenom") != null) {
			prenom = request.getParameter("NPrenom").trim();
		}
		if(request.getParameter("Ntelephone") != null) {
			telephoneStr = request.getParameter("Ntelephone").trim();
			tel = Integer.parseInt(telephoneStr);
		}
		if(request.getParameter("NcodePostal") != null) {
			codePostalStr = request.getParameter("NcodePostal").trim();
			cp = Integer.parseInt(codePostalStr);
		}
		
		
		if(request.getParameter("Nnom") != null) {
			nom = request.getParameter("Nnom").trim();
		}
		
		if(request.getParameter("newMDP") != null) {
			motDePasse = request.getParameter("newMDP").trim();
		}
		
		if(request.getParameter("Nemail") != null) {
			email = request.getParameter("Nemail").trim();
		}
		
		
		if(request.getParameter("Nrue") != null) {
			rue = request.getParameter("Nrue").trim();
		}
		
		
		if(request.getParameter("Nville") != null) {
			ville = request.getParameter("Nville").trim();
		}
		
		if(request.getParameter("motDePasseActuel") == request.getSession().getAttribute("motDePasse")) {
			if(request.getParameter("newMDP") != null && request.getParameter("confirmationMDP") == motDePasse) {			
				motDePasse = request.getParameter("newMDP").trim();
				}
			} 
		
		
		ModificationProfilManager user = new ModificationProfilManager();
		
		// SI l'utilisateur existe déjà en BDD on renvoie l'identifiant de l'user existant
//		int exist = modificationProfilMng.update(user);
//		if(exist != 0) {
//			request.setAttribute("exist", exist);
//			request.setAttribute("pseudo", pseudo);
//			request.setAttribute("nom", nom);
//			request.setAttribute("prenom", prenom);
//			request.setAttribute("email", email);
//			request.setAttribute("tel", tel);
//			request.setAttribute("rue", rue);
//			request.setAttribute("cp", cp);
//			request.setAttribute("ville", ville);
//			doGet(request, response);
//		}
		
		
		
		
	
		
		
		
	}

}

