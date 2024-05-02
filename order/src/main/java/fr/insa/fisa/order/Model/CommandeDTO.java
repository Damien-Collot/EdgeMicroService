package fr.insa.fisa.order.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CommandeDTO {
    private int idClient;
    private List<ProductDTO> products;
    private String status;
    private String total;

    public CommandeDTO(int idClient, List<ProductDTO> products, String status, String total) {
        this.idClient = idClient;
        this.products = products;
        this.status = status;
        this.total = total;
    }
}
