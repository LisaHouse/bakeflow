package br.com.bakeflow.bakeflow.service;

import br.com.bakeflow.bakeflow.model.Produto;
import br.com.bakeflow.bakeflow.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Produto produto) {
        repository.save(produto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}