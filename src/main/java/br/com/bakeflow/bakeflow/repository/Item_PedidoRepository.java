package br.com.bakeflow.bakeflow.repository;

import br.com.bakeflow.bakeflow.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.bakeflow.bakeflow.model.Item_Pedido;

public interface Item_PedidoRepository extends JpaRepository<Item_Pedido, Long> {

    boolean existsByProduto(Produto produto);


}