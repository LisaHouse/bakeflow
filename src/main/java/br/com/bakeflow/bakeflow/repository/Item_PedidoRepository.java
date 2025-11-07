package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Item_Pedido;
import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;
import java.math.BigDecimal;

public interface Item_PedidoRepository extends CrudRepository<Item_Pedido, Long> {
    
    // Buscar por ID
    Item_Pedido findByID_ItemPedido(Long ID_ItemPedido);
    
    // Buscar por pedido
    List<Item_Pedido> findByPedido(Pedido pedido);
    
    // Buscar por produto
    List<Item_Pedido> findByProduto(Produto produto);
    
    // Buscar por quantidade maior que
    List<Item_Pedido> findByQuantidadeGreaterThan(Integer quantidade);
    
    // Buscar por valor maior que
    List<Item_Pedido> findByValorGreaterThan(BigDecimal valor);
    
    // Buscar itens de um pedido por produto
    List<Item_Pedido> findByPedidoAndProduto(Pedido pedido, Produto produto);
}