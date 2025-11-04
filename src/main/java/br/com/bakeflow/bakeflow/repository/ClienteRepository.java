package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.*;
import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Cliente findByID_Cliente(long ID_Cliente);

    List<Cliente> findBytelefone(String telefone);

    List<Cliente> findBynome(String nome);
    
    List<Cliente> findByendereco(String endereco);
}
