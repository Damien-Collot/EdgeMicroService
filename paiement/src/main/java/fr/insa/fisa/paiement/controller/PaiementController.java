package fr.insa.fisa.paiement.controller;

import fr.insa.fisa.paiement.dto.PaiementDto;
import fr.insa.fisa.paiement.model.PaiementEntity;
import fr.insa.fisa.paiement.service.PaiementService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/service/paiement")
public class PaiementController {
    @Resource
    PaiementService paiementService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try{
            List<PaiementEntity> res = paiementService.getAll();
            if (res.size() > 0) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> getAllByIdClient(@PathVariable("id") Long idClient){
        try{
            List<PaiementEntity> res = paiementService.getAllByIdClient(idClient);
            if (res.size() > 0) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createPaiement(@RequestBody PaiementDto paiementDto){
        try{
            PaiementEntity res = paiementService.createOne(paiementDto);
            return ResponseEntity.ok(res);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
