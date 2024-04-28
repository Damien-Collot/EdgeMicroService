package fr.insa.fisa.client.controller;

import fr.insa.fisa.client.model.ClientEntity;
import fr.insa.fisa.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/service/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        System.out.println("Login attempt: " + login + " with password: " + password);
        return clientService.findByLogin(login)
                .map(client -> {
                    System.out.println("Stored password hash: " + client.getPassword());
                    boolean matches = passwordEncoder.matches(password, client.getPassword());
                    DebugSignInResponse debugResponse = new DebugSignInResponse(
                            matches ? "User logged in successfully!" : "Login failed",
                            matches,
                            password,
                            client.getPassword()
                    );
                    return matches ? ResponseEntity.ok(debugResponse) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(debugResponse);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DebugSignInResponse(
                        "User not found",
                        false,
                        password,
                        "No password stored for this user"
                )));
    }



    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestParam String name, @RequestParam String login, @RequestParam String password) {
        if (clientService.findByLogin(login).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        ClientEntity newClient = new ClientEntity();
        newClient.setName(name);
        newClient.setLogin(login);
        newClient.setPassword(passwordEncoder.encode(password));

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
