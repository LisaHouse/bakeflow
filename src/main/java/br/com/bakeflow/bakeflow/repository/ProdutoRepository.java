package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;
import java.math.BigDecimal;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    
 
    Produto findByIdProduto(Long idProduto);
    
    
    List<Produto> findByNome(String nome);
    
    
    List<Produto> findByPrecoLessThan(BigDecimal preco);
    
    
    List<Produto> findByPrecoGreaterThan(BigDecimal preco);
    
   
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}