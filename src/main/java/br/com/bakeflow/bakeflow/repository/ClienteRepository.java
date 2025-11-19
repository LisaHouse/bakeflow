package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.*;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    

}
