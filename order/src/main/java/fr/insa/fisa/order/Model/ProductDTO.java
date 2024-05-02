package fr.insa.fisa.order.Model;

import lombok.Data;

@Data
public class ProductDTO {
    private String description;
    private int id;
    private float montant;
    private String name;
    private int quantity;
}
