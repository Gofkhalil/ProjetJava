package DAO;

import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="utilisateur")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "caisse_id")
    private Caisse caisse;
	
	private String nom;
	@Column(name = "email", unique = true)
	private String email;
	private String prenom;
	private String motdepasse;
	private String role;
	
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Emprunt> historiqueEmprunts;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Reparation> Listesreparations;
	
	public Utilisateur() {
	   
	}

	
	public Utilisateur(String nom,String email, String prenom, String motDePasse, String role) {
        this.nom = nom;
        this.email=email;
        this.prenom = prenom;
        this.motdepasse = motDePasse;
        this.role = role;
    }
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}
	
	
	
	
	
	
	
}
