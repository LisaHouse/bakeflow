package br.com.bakeflow.bakeflow.service;

import br.com.bakeflow.bakeflow.model.*;
import br.com.bakeflow.bakeflow.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ProdutoService produtoService;
    private final EstoqueService estoqueService;

    public PedidoService(PedidoRepository repository,
                         ProdutoService produtoService,
                         EstoqueService estoqueService) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.estoqueService = estoqueService;
    }


    public List<Pedido> findAll() {
        return repository.findAllWithClienteAndItens();
    }

    public Pedido save(Pedido pedido) {

        BigDecimal total = BigDecimal.ZERO;

        for (Item_Pedido item : pedido.getItens()) {


            Produto p = produtoService.findById(item.getProduto().getIdProduto());
            item.setProduto(p);


            Estoque est = estoqueService.buscarPorProduto(p.getIdProduto());
            if (est == null || est.getQuantidade() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + p.getNome());
            }

            // descontar estoque
            est.setQuantidade(est.getQuantidade() - item.getQuantidade());
            estoqueService.save(est);

            // soma valor dos itens
            BigDecimal valor = p.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            item.setValor(valor);

            total = total.add(valor);


            item.setPedido(pedido);
        }

        pedido.setValorTotal(total);

        return repository.save(pedido);
    }
}
