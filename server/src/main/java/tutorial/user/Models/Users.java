package tutorial.user.Models;

 
public class Users {
	private int id;
	private String nom, prenom;

	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 */
	public Users(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}

	
	public Users() { }


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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

	
	
	
}
