package fr.insa.fisa.product.service;

import fr.insa.fisa.product.model.ProductEntity;
import fr.insa.fisa.product.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Resource
    ProductRepository productRepository;

    public ProductEntity findOne(Long id){
        return productRepository.findById(id).orElse(null);
    }
}
