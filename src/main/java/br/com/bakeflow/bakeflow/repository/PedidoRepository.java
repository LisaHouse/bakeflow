package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.model.Cliente;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    
    // Buscar por ID
    Pedido findByID_Pedido(Long ID_Pedido);
    
    // Buscar por cliente
    List<Pedido> findByCliente(Cliente cliente);
    
    // Buscar por status
    List<Pedido> findByStatus(String status);
    
    // Buscar por data
    List<Pedido> findByDataAtualizacaoBetween(Date inicio, Date fim);
    
    // Buscar por valor total maior que
    List<Pedido> findByValor_totalGreaterThan(BigDecimal valor);
    
    // Buscar pedidos de um cliente por status
    List<Pedido> findByClienteAndStatus(Cliente cliente, String status);
}