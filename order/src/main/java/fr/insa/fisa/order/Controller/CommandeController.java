package fr.insa.fisa.order.Controller;

import fr.insa.fisa.order.Model.CommandeDTO;
import fr.insa.fisa.order.Model.OrderEntity;
import fr.insa.fisa.order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/service/order")
public class CommandeController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createCommande")
    public ResponseEntity<?> createOrder(@RequestBody OrderEntity orderEntity) {
        try {
            OrderEntity res = orderService.newCommande(orderEntity);
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating order");
        }
    }

    @GetMapping("/getCommande/{idCommande}")
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

    @GetMapping("/getCommandes/{idClient}")
    public ResponseEntity<?> getOrders(@PathVariable("idClient") int id) {
        try {
            List<OrderEntity> orderEntity = orderService.findAllByIdClient(id);
            if (orderEntity != null){
                return ResponseEntity.ok(orderEntity);
            }else{
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PostMapping("/addProductToOrder")
    public ResponseEntity<?> addProductsToOrder(@RequestBody CommandeDTO commandeDTO) {
        try {
            orderService.addProduct(commandeDTO);
            return ResponseEntity.ok("Commande créée");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
