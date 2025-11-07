package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.*;
import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
    // Buscar por ID
    Cliente findByID_Cliente(long ID_Cliente);

    // Buscar por telefone
    List<Cliente> findByTelefone(String telefone);

    // Buscar por nome
    List<Cliente> findByNome(String nome);
    
    // Buscar por endereço
    List<Cliente> findByEndereco(String endereco);
    
    // Buscar por nome contendo (case insensitive)
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    
    // Buscar por endereço contendo
    List<Cliente> findByEnderecoContainingIgnoreCase(String endereco);
    
    // Buscar clientes ordenados por nome
    List<Cliente> findAllByOrderByNomeAsc();
}
