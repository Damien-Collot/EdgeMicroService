package fr.insa.fisa.client.controller;

import fr.insa.fisa.client.model.ClientEntity;
import fr.insa.fisa.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/service/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        List<ClientEntity> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClient(@PathVariable Long id) {
        return clientService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String login, @RequestParam String password) {
        System.out.println("Login attempt: " + login); // Afficher la tentative de connexion
        try {
            ClientEntity client = clientService.findByLoginAndPassword(login, password);
            if (client != null){
                return ResponseEntity.ok(client);
            }else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }



    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestParam String name, @RequestParam String login, @RequestParam String password) {
        if (clientService.findByLogin(login).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        ClientEntity newClient = new ClientEntity();
        newClient.setName(name);
        newClient.setLogin(login);
        newClient.setPassword(password);
        clientService.createClient(newClient);

        return ResponseEntity.ok("User registered successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return clientService.findById(id).map(c -> {
            clientService.deleteClient(id);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
