package br.com.bakeflow.bakeflow.service;

import br.com.bakeflow.bakeflow.model.Item_Pedido;
import br.com.bakeflow.bakeflow.repository.Item_PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Item_PedidoService {

    private final Item_PedidoRepository repository;

    public Item_PedidoService(Item_PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Item_Pedido> findAll() {
        return repository.findAll();
    }

    public Item_Pedido findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Item_Pedido item_Pedido) {
        repository.save(item_Pedido);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}