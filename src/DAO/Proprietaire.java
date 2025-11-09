package DAO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proprietaire")
public class Proprietaire extends Utilisateur {
	

    private double commission;

    
    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL)
    private List<Reparateur> reparateurs;

   
	public Proprietaire() {
	    // default constructor
	}

    public Proprietaire(String nom,String email, String prenom, String identifiant, String motDePasse,double commission) {
        super(nom,email, prenom, motDePasse, "proprietaire");
        this.commission=commission;
    }

    public double getCommission() { return commission; }
    public void setCommission(double commission) { this.commission = commission; }



    public List<Reparateur> getReparateurs() { return reparateurs; }
    public void setReparateurs(List<Reparateur> reparateurs) { this.reparateurs = reparateurs; }
}
