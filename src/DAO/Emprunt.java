package DAO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_emprunt")
    private Date dateEmprunt;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_retour")
    private Date dateRetour;

    
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // ---- Constructeurs ----
    public Emprunt() {}

    public Emprunt(Date dateEmprunt, Date dateRetour, Utilisateur reparateur) {
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.utilisateur = reparateur;
    }

    // ---- Getters et Setters ----
    public int getId() {
        return id;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
}
