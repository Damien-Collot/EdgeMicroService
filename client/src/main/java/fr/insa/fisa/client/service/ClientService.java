package fr.insa.fisa.client.service;

import fr.insa.fisa.client.model.ClientEntity;
import fr.insa.fisa.client.repository.ClientRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Resource
    private ClientRepository clientRepository;

    public ClientService() {
    }


    public void createClient(ClientEntity client) {
        client.setPassword(client.getPassword());
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

    public ClientEntity findByLoginAndPassword(String login, String mdp) {
        ClientEntity client = clientRepository.findByLogin(login).orElse(null);
        if (client != null){
            if (client.getPassword().equals(mdp)) return client;
        }
        return null;
    }
}
