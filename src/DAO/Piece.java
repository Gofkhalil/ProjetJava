package DAO;

import javax.persistence.*;

@Entity
@Table(name = "piece")
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private double prix;

    @ManyToOne
    @JoinColumn(name = "reparation_id")
    private Reparation reparation;

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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Reparation getReparation() {
		return reparation;
	}

	public void setReparation(Reparation reparation) {
		this.reparation = reparation;
	}

    
}
