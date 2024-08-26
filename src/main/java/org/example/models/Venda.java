package org.example.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static int numVendas = 0;
    private int numero;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "venda_livro",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private List<Livro> livros;

    @ManyToOne
    @JoinColumn(name = "livraria_id")
    private LivrariaVirtual livrariaVirtual;

    private String cliente;
    private double valor;

    // Construtor padrão
    public Venda() {
    }

    public Venda(String cliente, List<Livro> livros) {
        this.numero = ++numVendas;
        this.livros = livros;
        this.cliente = cliente;
        this.valor = 0.0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venda ID: ").append(id).append(", Cliente: ").append(cliente).append("\n");
        sb.append("Livros:\n");
        if (livros != null && !livros.isEmpty()) {
            for (Livro livro : livros) {
                sb.append(livro.getTitulo()).append(" - ").append(livro.getPreco()).append("\n");
            }
        } else {
            sb.append("Nenhum livro associado.\n");
        }
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

}

