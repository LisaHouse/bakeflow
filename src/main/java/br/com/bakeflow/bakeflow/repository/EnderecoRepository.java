package br.com.bakeflow.bakeflow.repository;

import br.com.bakeflow.bakeflow.model.Estoque;
import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Endereco;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;


public interface EnderecoRepository extends CrudRepository<Endereco, Long> {


    Endereco findByidEndereco(long idEndereco);


    List<Endereco> findByCep(String cep);


    List<Endereco> findByRua(String rua);


    List<Endereco> findByUf(String uf);


    List<Endereco> findByCidadeContainingIgnoreCase(String cidade);


    List<Endereco> findByComplementoContainingIgnoreCase(String complemento);


}
