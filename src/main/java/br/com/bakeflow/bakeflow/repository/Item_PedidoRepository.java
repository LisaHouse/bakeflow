package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Item_Pedido;
import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;
import java.math.BigDecimal;

public interface Item_PedidoRepository extends CrudRepository<Item_Pedido, Long> {
    
    
    Item_Pedido findByidItemPedido(Long ID_ItemPedido);
    

    List<Item_Pedido> findByPedido(Pedido pedido);
    
  
    List<Item_Pedido> findByProduto(Produto produto);
    
   
    List<Item_Pedido> findByQuantidadeGreaterThan(Integer quantidade);
    
    
    List<Item_Pedido> findByValorGreaterThan(BigDecimal valor);
    
   
    List<Item_Pedido> findByPedidoAndProduto(Pedido pedido, Produto produto);
}