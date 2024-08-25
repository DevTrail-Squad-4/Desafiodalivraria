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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Livro> livros;
    private String cliente;
    private float valor;


    public Venda(String cliente) {
        this.numero = ++numVendas;
        this.livros = new ArrayList<>();
        this.cliente = cliente;
        this.valor = 0.0f;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Venda #%d%nCliente: %s%nValor Total: R$ %.2f%n",
                numero, cliente, valor));
        sb.append("Livros na Venda:\n");
        for (Livro livro : livros) {
            sb.append(livro.toString()).append("\n");
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

    public float getValor() { return valor; }
    public void setValor(float valor) { this.valor = valor; }

    // Métodos
    public void addLivro(Livro l, int index) {
        if (index >= 0 && index < livros.size()) {
            livros.add(index, l);
            valor += l.getPreco();
            if (l instanceof Impresso) {
                ((Impresso) l).atualizarEstoque();
            }
        }
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }
}

