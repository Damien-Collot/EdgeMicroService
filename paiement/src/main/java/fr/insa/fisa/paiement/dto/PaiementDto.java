package fr.insa.fisa.paiement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaiementDto {
    private String libelle;
    private Long idClient;
    private float montant;
    private LocalDateTime datePaiement;

    public PaiementDto(String libelle, Long idClient, float montant, LocalDateTime datePaiement) {
        this.libelle = libelle;
        this.idClient = idClient;
        this.montant = montant;
        this.datePaiement = datePaiement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }
}
