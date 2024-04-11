package fr.insa.fisa.product.controller;

import fr.insa.fisa.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/service/product")
public class ProductController {
    @Resource
    ProductService productService;

    @GetMapping("/findOne/{id}")
    public ResponseEntity<?> findOne(@Param("id") Long id){
        return ResponseEntity.ok(productService.findOne(id));
    }
}
