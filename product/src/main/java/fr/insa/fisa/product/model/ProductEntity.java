package fr.insa.fisa.product.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue
    Long Id;
    @Column
    String name;
    @Column
    String description;
    @Column
    float montant;
}
