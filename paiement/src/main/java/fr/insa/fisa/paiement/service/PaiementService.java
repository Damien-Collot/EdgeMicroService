package fr.insa.fisa.paiement.service;

import fr.insa.fisa.paiement.dto.PaiementDto;
import fr.insa.fisa.paiement.model.PaiementEntity;
import fr.insa.fisa.paiement.repository.PaiementRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PaiementService {
    @Resource
    public PaiementRepository paiementRepository;

    public PaiementEntity createOne(PaiementDto paiementDto){
        PaiementEntity paiement = new PaiementEntity();
        Random rd = new Random();
        paiement.setLibelle(paiementDto.getLibelle());
        paiement.setIdClient(paiementDto.getIdClient());
        paiement.setMontant(paiementDto.getMontant());
        paiement.setDatePaiement(paiementDto.getDatePaiement());
        paiement.setValid(rd.nextBoolean());
        return paiementRepository.save(paiement);
    }

    public List<PaiementEntity> getAll(){
        return paiementRepository.findAll();
    }

    public List<PaiementEntity> getAllByIdClient(Long idClient){
        return paiementRepository.findAllByIdClient(idClient);
    }
}
