package bo;

public class Utilisateur {

	private int noUtil;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private int numero;
	private String rue;
	private int codePostal;
	private String ville;
	private String mdp;
	private int credit;
	private boolean admin;
	private int no_enchere;

	public Utilisateur(String pseudo, String nom, String prenom, String email, int numero, String rue, int codePostal,
			String ville, String mdp) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.mdp = mdp;
		credit = 100;
		admin = false;

	}
	public int getNoEnchere() {
		return no_enchere;
	}

	public Utilisateur() {
	}

	public int getNoUtil() {
		return noUtil;
	}

	public void setNoUtil(int noUtil) {
		this.noUtil = noUtil;
		no_enchere = noUtil;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

}
