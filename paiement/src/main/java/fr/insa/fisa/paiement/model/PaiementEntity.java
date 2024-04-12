package fr.insa.fisa.paiement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class PaiementEntity {
    @Id
    @GeneratedValue
    Long Id;
    @Column
    String libelle;
    @Column(name="id_client")
    Long idClient;
    @Column
    float montant;
    @Column(name="date_paiement")
    LocalDateTime datePaiement;
    @Column
    boolean valid;
}
