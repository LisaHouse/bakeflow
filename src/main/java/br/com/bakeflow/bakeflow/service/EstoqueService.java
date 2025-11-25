package br.com.bakeflow.bakeflow.service;

import br.com.bakeflow.bakeflow.model.Estoque;
import br.com.bakeflow.bakeflow.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstoqueService {

    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public List<Estoque> findAll() {
        return repository.findAll();
    }

    public Estoque findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Estoque buscarPorProduto(Long idProduto) {
        return repository.findByProdutoIdProduto(idProduto).orElse(null);
    }

    public void save(Estoque estoque) {
        repository.save(estoque);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}