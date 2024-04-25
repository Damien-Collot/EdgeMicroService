package fr.insa.fisa.client.controller;

import fr.insa.fisa.client.model.ClientEntity;
import fr.insa.fisa.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        List<ClientEntity> clients = clientService.findAll();
        return ResponseEntity.ok(clients); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClient(@PathVariable Long id) {
        return clientService.findById(id)
                .map(ResponseEntity::ok)  // 200 OK
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String login, @RequestParam String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<ClientEntity> storedClientOpt = clientService.findByLogin(login);

        if (storedClientOpt.isPresent()) {
            ClientEntity storedClient = storedClientOpt.get();
            if (bCryptPasswordEncoder.matches(password, storedClient.getPassword())) {
                return ResponseEntity.ok().body("User logged in successfully!");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestParam String name, @RequestParam String login, @RequestParam String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (clientService.findByLogin(login).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        ClientEntity newClient = new ClientEntity();
        newClient.setName(name);
        newClient.setLogin(login);
        newClient.setPassword(bCryptPasswordEncoder.encode(password));

        clientService.createClient(newClient);

        return ResponseEntity.ok("User registered successfully!");
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return clientService.findById(id).map(c -> {
            clientService.deleteClient(id);
            return ResponseEntity.ok().<Void>build(); // 200 OK
        }).orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }
}
