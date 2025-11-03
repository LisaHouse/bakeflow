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
    @Column(name = "ID_ItemPedido")
    private long ID_ItemPedido;

    // Cria FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Pedido", nullable = false, foreignKey = @ForeignKey(name = "fk_itempedido_pedido"))
    private Pedido pedido;
    
    // Cria FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Produto", nullable = false, foreignKey = @ForeignKey(name = "fk_itempedido_produto"))
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor", precision = 10, scale = 2)
    private BigDecimal valor;

}
