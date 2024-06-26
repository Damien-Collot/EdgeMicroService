package fr.insa.fisa.order.Service;

import fr.insa.fisa.order.Model.CommandeDTO;
import fr.insa.fisa.order.Model.OrderEntity;
import fr.insa.fisa.order.Model.ProductDTO;
import fr.insa.fisa.order.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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

    public List<OrderEntity> findAllByIdClient(int id) {
        return orderRepository.findAllByIdClient(id);
    }
    public void addProduct(CommandeDTO commandeDTO) {
        List<ProductDTO> products = commandeDTO.getProducts();
        for (ProductDTO product : products) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setIdClient(commandeDTO.getIdClient());
            orderEntity.setIdProduit(product.getId());
            orderEntity.setName(product.getName());
            orderEntity.setDescription(product.getDescription());
            orderEntity.setQuantite(product.getQuantity());
            orderEntity.setMontant(product.getMontant());
            orderEntity.setDate(ZonedDateTime.now());
            orderEntity.setStatus(commandeDTO.getStatus());
            orderEntity.setTotal(commandeDTO.getTotal());
            orderRepository.save(orderEntity);
        }
    }

}
