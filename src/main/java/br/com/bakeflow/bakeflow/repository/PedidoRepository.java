package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.bakeflow.bakeflow.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {


}