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
        return clientService.findByLogin(login)
                .map(client -> {
                    boolean matches = password.equals(client.getPassword());
                    System.out.println("Password match result: " + matches);
                    return matches ? ResponseEntity.ok("User logged in successfully!") : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
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
