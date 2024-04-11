package fr.insa.fisa.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue
    Long Id;
    @Column
    String name;

}
