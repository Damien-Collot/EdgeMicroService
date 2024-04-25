package fr.insa.fisa.product.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ou GenerationType.AUTO selon la BDD
    private Long id;

    @Column(name = "name") // Si le nom de la colonne est différent, spécifie-le ici
    private String name;

    @Column
    private String description;

    @Column(name = "montant")
    private float montant;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
}
