package DAO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reparateur")
public class Reparateur extends Utilisateur {

	public Reparateur() {
	    // default constructor
	}
	
    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    private Proprietaire proprietaire;

    

    public Reparateur(String nom, String prenom, String identifiant, String motDePasse) {
        super(nom, prenom, identifiant, motDePasse, "reparateur");
    }



    public Proprietaire getProprietaire() { return proprietaire; }
    public void setProprietaire(Proprietaire proprietaire) { this.proprietaire = proprietaire; }
}
