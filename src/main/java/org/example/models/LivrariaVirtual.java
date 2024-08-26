package org.example.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class LivrariaVirtual implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final int MAX_IMPRESSOS = 10;
    private static final int MAX_ELETRONICOS = 20;
    private static final int MAX_VENDAS = 50;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Impresso> impressos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Eletronico> eletronicos;

    @OneToMany(mappedBy = "livrariaVirtual", cascade = CascadeType.ALL)
    private List<Venda> vendas;
    private int numImpressos;
    private int numEletronicos;
    private int numVendas;

    // Construtor
    public LivrariaVirtual() {
        this.impressos = new ArrayList<>(MAX_IMPRESSOS);
        this.eletronicos = new ArrayList<>(MAX_ELETRONICOS);
        this.vendas = new ArrayList<>();
        this.numImpressos = 0;
        this.numEletronicos = 0;
        this.numVendas = 0;
    }

    // Construtor com parâmetros
    public LivrariaVirtual(List<Impresso> impressos, List<Eletronico> eletronicos, List<Venda> vendas,
                           int numImpressos, int numEletronicos, int numVendas) {
        this.impressos = impressos != null ? impressos : new ArrayList<>(MAX_IMPRESSOS);
        this.eletronicos = eletronicos != null ? eletronicos : new ArrayList<>(MAX_ELETRONICOS);
        this.vendas = vendas != null ? vendas : new ArrayList<>();
        this.numImpressos = numImpressos;
        this.numEletronicos = numEletronicos;
        this.numVendas = numVendas;
    }

    //getters setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumImpressos() {
        return numImpressos;
    }

    public void setNumImpressos(int numImpressos) {
        this.numImpressos = numImpressos;
    }

    public int getNumEletronicos() {
        return numEletronicos;
    }

    public void setNumEletronicos(int numEletronicos) {
        this.numEletronicos = numEletronicos;
    }

    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }
}