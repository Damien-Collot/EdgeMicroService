package fr.insa.fisa.paiement.repository;

import fr.insa.fisa.paiement.model.PaiementEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaiementRepository extends CrudRepository<PaiementEntity, Long> {
    public List<PaiementEntity> findAll();
    public List<PaiementEntity> findAllByIdClient(Long idClient);
}
