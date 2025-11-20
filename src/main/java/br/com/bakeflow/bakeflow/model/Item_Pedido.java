package br.com.bakeflow.bakeflow.model;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class Item_Pedido implements Serializable {
    private static final long serialVersionUID = 1L;


    // gera PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_item_pedido")
    private long idItemPedido;


    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false, foreignKey = @ForeignKey(name = "fk_itempedido_produto"))
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor", precision = 10, scale = 2)
    private BigDecimal valor;

    public long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(long ID_ItemPedido) {
        this.idItemPedido = ID_ItemPedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
