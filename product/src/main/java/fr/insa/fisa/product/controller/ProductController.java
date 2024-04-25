package fr.insa.fisa.product.controller;

import fr.insa.fisa.product.model.ProductEntity;
import fr.insa.fisa.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        try {
            ProductEntity res = productService.findOneById(id);
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findOne(@PathVariable("name") String name) {
        try {
            ProductEntity res = productService.findOneByName(name);
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        try {
            List<ProductEntity> res = productService.findAll();
            if (res.size() > 0) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
