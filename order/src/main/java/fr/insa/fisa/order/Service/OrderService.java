package fr.insa.fisa.order.Service;

import fr.insa.fisa.order.Model.CommandeDTO;
import fr.insa.fisa.order.Model.OrderEntity;
import fr.insa.fisa.order.Model.ProductDTO;
import fr.insa.fisa.order.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity newCommande(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    public Optional<OrderEntity> findOneById(int id) {
        return orderRepository.findById(id);
    }

    public OrderEntity findAllByIdClient(int id) {
        return (OrderEntity) orderRepository.findAllByIdClient(id);
    }

    public void addProduct(CommandeDTO commandeDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(commandeDTO.getId());
        orderEntity.setIdClient(commandeDTO.getIdClient());
        orderEntity.setReference(commandeDTO.getReference());
        orderEntity.setDate(commandeDTO.getDate());
        orderEntity.setReference(commandeDTO.getReference());

        List<ProductDTO> products = commandeDTO.getProducts();
        for (ProductDTO product : products){
            orderEntity.setIdProduit(product.getIdProduct());
            orderEntity.setQuantite(product.getQuantite());
            orderRepository.save(orderEntity);
        }
    }
}
