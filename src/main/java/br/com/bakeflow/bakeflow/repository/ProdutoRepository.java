package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;
import java.math.BigDecimal;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    
    // Buscar por ID
    Produto findByIdProduto(Long idProduto);
    
    // Buscar por nome
    List<Produto> findByNome(String nome);
    
    // Buscar por preço menor que
    List<Produto> findByPrecoLessThan(BigDecimal preco);
    
    // Buscar por preço maior que
    List<Produto> findByPrecoGreaterThan(BigDecimal preco);
    
    // Buscar por nome contendo (case insensitive)
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}