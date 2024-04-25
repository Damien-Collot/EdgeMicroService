package fr.insa.fisa.order.Repository;

import fr.insa.fisa.order.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    public List<OrderEntity> findAllByIdClient(int id);
}
