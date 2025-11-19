package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Estoque;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    
   

}


