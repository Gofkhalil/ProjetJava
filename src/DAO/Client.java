package DAO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity  // <- indispensable
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String telephone;
    private String photo;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reparation> reparations = new ArrayList<>();

    public Client() {}

    public Client(String nom, String telephone, String photo) {
        this.nom = nom;
        this.telephone = telephone;
        this.photo = photo;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Reparation> getReparations() {
		return reparations;
	}

	public void setReparations(List<Reparation> reparations) {
		this.reparations = reparations;
	}

    
}
