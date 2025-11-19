package br.com.bakeflow.bakeflow.repository;

import br.com.bakeflow.bakeflow.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Endereco;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {




}
