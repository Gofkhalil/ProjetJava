package DAO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reparation")
public class Reparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_appareil", nullable = false)
    private String typeAppareil;

    @Column(nullable = false)
    private double prix;

    @Column(nullable = false)
    private String etat; 

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin_estimee")
    private LocalDate dateFinEstimee;

    @Column(name = "date_livraison")
    private LocalDate dateLivraison;

    @Column(name = "code_suivi")
    private String codeSuivi;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
    
    
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    
    @OneToMany(mappedBy = "reparation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Piece> pieces = new ArrayList<>();

    
    public Reparation() {}

    public Reparation(String typeAppareil, double prix, String etat, LocalDate dateDebut, LocalDate dateFinEstimee,
                      LocalDate dateLivraison, String codeSuivi, Client client) {
        this.typeAppareil = typeAppareil;
        this.prix = prix;
        this.etat = etat;
        this.dateDebut = dateDebut;
        this.dateFinEstimee = dateFinEstimee;
        this.dateLivraison = dateLivraison;
        this.codeSuivi = codeSuivi;
        this.client = client;
    }

    
    public int getId() { return id; }

    public String getTypeAppareil() { return typeAppareil; }
    public void setTypeAppareil(String typeAppareil) { this.typeAppareil = typeAppareil; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFinEstimee() { return dateFinEstimee; }
    public void setDateFinEstimee(LocalDate dateFinEstimee) { this.dateFinEstimee = dateFinEstimee; }

    public LocalDate getDateLivraison() { return dateLivraison; }
    public void setDateLivraison(LocalDate dateLivraison) { this.dateLivraison = dateLivraison; }

    public String getCodeSuivi() { return codeSuivi; }
    public void setCodeSuivi(String codeSuivi) { this.codeSuivi = codeSuivi; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    //public List<Piece> getPieces() { return pieces; }
    //public void setPieces(List<Piece> pieces) { this.pieces = pieces; }

    /*public void addPiece(Piece piece) {
        pieces.add(piece);
    }*/
    
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

}
