package fr.insa.fisa.product.service;

import fr.insa.fisa.product.model.ProductEntity;
import fr.insa.fisa.product.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Resource
    ProductRepository productRepository;

    public ProductEntity findOneById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public ProductEntity findOneByName(String name){
        return productRepository.findByName(name).orElse(null);
    }

    public List<ProductEntity> findAll(){
        return productRepository.findAll();
    }
}
