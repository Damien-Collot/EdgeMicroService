package fr.insa.fisa.product.repository;

import fr.insa.fisa.product.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    public Optional<ProductEntity> findById(Long id);
}
