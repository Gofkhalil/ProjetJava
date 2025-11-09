package DAO;

import javax.persistence.*;

@Entity
@Table(name = "caisse")
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double solde;

    private String type; 

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    public Caisse() {}

    public Caisse(double solde, String type) {
        this.solde = solde;
        this.type = type;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    
    public void crediter(double montant) { this.solde += montant; }
    public void debiter(double montant) { this.solde -= montant; }
}
