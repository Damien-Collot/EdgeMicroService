package fr.insa.fisa.product.controller;

import fr.insa.fisa.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/product") // Définir le chemin de base ici
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/findOne/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.findOne(id));
    }
}
