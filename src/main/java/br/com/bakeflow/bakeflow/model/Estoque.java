package br.com.bakeflow.bakeflow.model;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "estoque")
public class Estoque {
    
    // Cria PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Estoque")
    private Long idEstoque;
    
    // Cria FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Produto", nullable = false, foreignKey = @ForeignKey(name = "fk_estoque_produto"))
    private Produto produto;
    
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    // Construtores
    public Estoque() {}

    public Estoque(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    // Getters e Setters
    public Long getIdEstoque() { return idEstoque; }
    public void setIdEstoque(Long idEstoque) { this.idEstoque = idEstoque; }
    
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    
}