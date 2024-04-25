package fr.insa.fisa.paiement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="paiement")
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
