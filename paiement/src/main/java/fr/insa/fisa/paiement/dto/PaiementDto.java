package fr.insa.fisa.paiement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaiementDto {
    private String libelle;
    private Long idClient;
    private float montant;
    private LocalDateTime datePaiement;
}
