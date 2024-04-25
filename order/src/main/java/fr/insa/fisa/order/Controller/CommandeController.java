package fr.insa.fisa.order.Controller;

import fr.insa.fisa.order.Model.CommandeDTO;
import fr.insa.fisa.order.Model.OrderEntity;
import fr.insa.fisa.order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/service/order")
public class CommandeController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/createCommande")
    public ResponseEntity<?> createOrder(@RequestBody OrderEntity orderEntity) {
        try {
            OrderEntity res = orderService.newCommande(orderEntity);
            if (res != null){
                return ResponseEntity.ok(res);
            }else{
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/getCommande{idCommande}")
    public ResponseEntity<?> getOrder(@PathVariable("idCommande") int id) {
        try {
            Optional<OrderEntity> orderEntity = orderService.findOneById(id);
            if (orderEntity != null){
                return ResponseEntity.ok(orderEntity);
            }else{
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/getCommandes{idClient}")
    public ResponseEntity<?> getOrders(@PathVariable("idClient") int id) {
        try {
            OrderEntity orderEntity = orderService.findAllByIdClient(id);
            if (orderEntity != null){
                return ResponseEntity.ok(orderEntity);
            }else{
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/addProductToOrder")
    public void addProductsToOrder(@RequestBody CommandeDTO commandeDTO) {
        try {
            orderService.addProduct(commandeDTO);
//            if (orderEntity != null){
//                return ResponseEntity.ok(orderEntity);
//            }else{
//                return ResponseEntity.noContent().build();
//            }
        }catch (Exception e){
//            return ResponseEntity.internalServerError().body(e);
        }
    }
}
