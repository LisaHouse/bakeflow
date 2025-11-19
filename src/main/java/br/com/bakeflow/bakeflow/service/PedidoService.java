package br.com.bakeflow.bakeflow.service;

import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Pedido findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Pedido pedido) {
        repository.save(pedido);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}