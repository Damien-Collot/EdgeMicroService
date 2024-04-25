package fr.insa.fisa.order.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CommandeDTO {
    private int id;
    private int idClient;
    private String reference;
    private LocalDate date;
    private String libelle;
    private List<ProductDTO> products;

    public CommandeDTO(int id, int idClient, String reference, LocalDate date, String libelle, List<ProductDTO> products) {
        this.id = id;
        this.idClient = idClient;
        this.reference = reference;
        this.date = date;
        this.libelle = libelle;
        this.products = products;
    }
}
