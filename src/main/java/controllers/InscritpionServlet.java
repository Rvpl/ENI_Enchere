package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Utilisateur;
import dal.InscriptionJdbc;

/**
 * Servlet implementation class InscritpionServlet
 */
@WebServlet("/inscription")
public class InscritpionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InscriptionJdbc inscriptionMng;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscritpionServlet() {
        super();
        inscriptionMng= new InscriptionJdbc();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prenom=  null;
		String nom=  null;
		String pseudo=  null;
		String email=  null;
		String telephoneStr=  null;
		int tel= 0;
		String rue=  null;
		String codePostalStr = null;
		int cp =0;
		String ville=  null;
		int credit=  100;
		String motDePasse=  null;
		String administrateur=  null;
		String confirmationMDP= null;
		
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
		if(request.getParameter("motDePasse") != null) {
			motDePasse = request.getParameter("motDePasse").trim();
		}
		
		if(request.getParameter("nom") != null) {
			nom = request.getParameter("nom").trim();
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
		
		
		if(request.getParameter("confirmationMDP") != null) {
			if (request.getParameter("confirmation") == motDePasse) {			
			motDePasse = request.getParameter("confirmationMDP").trim();
			}
		}
		
			Utilisateur user = new Utilisateur(pseudo,nom,prenom,email,tel,rue,cp,ville,motDePasse);
			int exist = inscriptionMng.insert(user);
			if(exist != 0) {
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




