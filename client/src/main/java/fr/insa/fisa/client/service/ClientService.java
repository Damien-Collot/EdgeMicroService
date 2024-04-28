package fr.insa.fisa.client.service;

import fr.insa.fisa.client.model.ClientEntity;
import fr.insa.fisa.client.repository.ClientRepository;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Resource
    private ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ClientService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public void createClient(ClientEntity client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
    }

    public List<ClientEntity> findAll() {
        return (List<ClientEntity>) clientRepository.findAll();
    }

    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Optional<ClientEntity> findByLogin(String login) {
        return clientRepository.findByLogin(login);
    }
}
