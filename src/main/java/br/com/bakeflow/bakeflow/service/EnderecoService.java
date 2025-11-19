package br.com.bakeflow.bakeflow.service;

import br.com.bakeflow.bakeflow.model.Endereco;
import br.com.bakeflow.bakeflow.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    public Endereco findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Endereco endereco) {
        repository.save(endereco);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}