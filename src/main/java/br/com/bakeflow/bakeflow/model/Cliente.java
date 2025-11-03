package br.com.bakeflow.bakeflow.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;



@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
    private static final long serialVersionUID = 1L;

     // gera PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_Cliente")
    private long ID_Cliente;

    @Column(name = "telefone", nullable = false, length = 100)
    @NotEmpty
    private String Telefone;

    @Column(name = "nome", nullable = false, length = 100)
    @NotEmpty
    private String Nome;
    
    @Column(name = "endereco", nullable = false, length = 100)
    @NotEmpty
    private String Endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

     // get set Cliente
    public long getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(long iD_Cliente) {
        ID_Cliente = iD_Cliente;
    }

     // get set Telefone
    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

     // get set Nome
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    // get set Endereço
    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    // Getters e Setters para pedidos
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
