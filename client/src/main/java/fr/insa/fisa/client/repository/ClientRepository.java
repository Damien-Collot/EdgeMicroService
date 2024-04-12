package fr.insa.fisa.client.repository;

import fr.insa.fisa.client.model.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    public List<ClientEntity> findAll();
    public Optional<ClientEntity> findById(Long id);

    public ClientEntity createClient(ClientEntity client);

    public void deleteClient(Long id);

    Optional<ClientEntity> findByLogin(String login);
}
