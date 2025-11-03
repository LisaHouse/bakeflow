package br.com.bakeflow.bakeflow.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    // gera PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_Pedido")
    private long ID_Pedido;

    // Cria FK para Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Cliente", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;
    

    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Column(name = "status", nullable = false, length = 100)
    @NotEmpty
    private String Status;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valor_total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item_Pedido> itensPedido;

    // Getters e Setters para itensPedido
    public List<Item_Pedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<Item_Pedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
