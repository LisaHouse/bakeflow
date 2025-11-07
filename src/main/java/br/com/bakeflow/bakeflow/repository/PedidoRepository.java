package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.model.Cliente;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    Pedido findByID_Pedido(Long ID_Pedido);
    
 
    List<Pedido> findByCliente(Cliente cliente);
    
  
    List<Pedido> findByStatus(String status);
    

    List<Pedido> findByDataAtualizacaoBetween(Date inicio, Date fim);
    
  
    List<Pedido> findByValor_totalGreaterThan(BigDecimal valor);
    
   
    List<Pedido> findByClienteAndStatus(Cliente cliente, String status);
}