package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.model.Cliente;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {


}