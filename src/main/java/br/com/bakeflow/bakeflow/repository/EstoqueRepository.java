package br.com.bakeflow.bakeflow.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.bakeflow.bakeflow.model.Estoque;
import br.com.bakeflow.bakeflow.model.Produto;
import java.util.List;

public interface EstoqueRepository extends CrudRepository<Estoque, Long> {
    
    // Buscar por ID
    Estoque findByIdEstoque(Long idEstoque);
    
    // Buscar por produto
    List<Estoque> findByProduto(Produto produto);
    
    // Buscar por quantidade menor que
    List<Estoque> findByQuantidadeLessThan(Integer quantidade);
    
    // Buscar por quantidade maior que
    List<Estoque> findByQuantidadeGreaterThan(Integer quantidade);
    
    // Buscar por produto e quantidade maior que
    List<Estoque> findByProdutoAndQuantidadeGreaterThan(Produto produto, Integer quantidade);
    
    // Buscar por produto ordenado por quantidade
    List<Estoque> findByProdutoOrderByQuantidadeDesc(Produto produto);
}