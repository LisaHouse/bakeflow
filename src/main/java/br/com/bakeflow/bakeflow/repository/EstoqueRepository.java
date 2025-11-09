package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Estoque;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;

public interface EstoqueRepository extends CrudRepository<Estoque, Long> {
    
   
    Estoque findByIdEstoque(Long idEstoque);
    
   
    List<Estoque> findByProduto(Produto produto);
    
    
    List<Estoque> findByQuantidadeLessThan(Integer quantidade);
    
   
    List<Estoque> findByQuantidadeGreaterThan(Integer quantidade);
    
    
    List<Estoque> findByProdutoAndQuantidadeGreaterThan(Produto produto, Integer quantidade);
    
    
    List<Estoque> findByProdutoOrderByQuantidadeDesc(Produto produto);
}


