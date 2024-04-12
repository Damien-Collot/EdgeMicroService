package fr.insa.fisa.client.service;

import fr.insa.fisa.client.model.ClientEntity;
import fr.insa.fisa.client.repository.ClientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public ClientEntity createClient(ClientEntity client){
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
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
