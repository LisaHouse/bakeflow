package br.com.bakeflow.bakeflow.service;

import br.com.bakeflow.bakeflow.model.Cliente;
import br.com.bakeflow.bakeflow.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Cliente cliente) {
        repository.save(cliente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}