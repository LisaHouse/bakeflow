package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Item_Pedido;
import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;
import java.math.BigDecimal;

public interface Item_PedidoRepository extends JpaRepository<Item_Pedido, Long> {
    

}