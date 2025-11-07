package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.*;
import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
  
    Cliente findByID_Cliente(long ID_Cliente);

   
    List<Cliente> findByTelefone(String telefone);

 
    List<Cliente> findByNome(String nome);
    
  
    List<Cliente> findByEndereco(String endereco);
    

    List<Cliente> findByNomeContainingIgnoreCase(String nome);


    List<Cliente> findByEnderecoContainingIgnoreCase(String endereco);
    
   
    List<Cliente> findAllByOrderByNomeAsc();
}
