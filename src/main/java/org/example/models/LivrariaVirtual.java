package org.example.models;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToMany(cascade = CascadeType.ALL)
    private Impresso[] impressos;

    @OneToMany(cascade = CascadeType.ALL)
    private Eletronico[] eletronicos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Venda[] vendas;
    private int numImpressos;
    private int numEletronicos;
    private int numVendas;

    // Construtor
    public LivrariaVirtual() {
        this.impressos = new Impresso[MAX_IMPRESSOS];
        this.eletronicos = new Eletronico[MAX_ELETRONICOS];
        this.vendas = new Venda[MAX_VENDAS];
        this.numImpressos = 0;
        this.numEletronicos = 0;
        this.numVendas = 0;
    }

    // Métodos
    public void cadastrarLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o tipo de livro (impresso, eletrônico): ");
        String tipo = scanner.nextLine();

        if (tipo.equalsIgnoreCase("impresso") && numImpressos < MAX_IMPRESSOS) {
            System.out.println("Digite os dados do livro impresso:");
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Autores: ");
            String autores = scanner.nextLine();
            System.out.print("Editora: ");
            String editora = scanner.nextLine();
            System.out.print("Preço: ");
            float preco = scanner.nextFloat();
            System.out.print("Frete: ");
            float frete = scanner.nextFloat();
            System.out.print("Estoque: ");
            int estoque = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            Impresso livro = new Impresso(titulo, autores, editora, preco, frete, estoque);
            impressos[numImpressos++] = livro;
        } else if (tipo.equalsIgnoreCase("eletrônico") && numEletronicos < MAX_ELETRONICOS) {
            System.out.println("Digite os dados do livro eletrônico:");
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Autores: ");
            String autores = scanner.nextLine();
            System.out.print("Editora: ");
            String editora = scanner.nextLine();
            System.out.print("Preço: ");
            float preco = scanner.nextFloat();
            System.out.print("Tamanho (KB): ");
            int tamanho = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            Eletronico livro = new Eletronico(titulo, autores, editora, preco, tamanho);
            eletronicos[numEletronicos++] = livro;
        } else {
            System.out.println("Número máximo de livros atingido ou tipo inválido.");
        }
    }

    public void realizarVenda() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do cliente: ");
        String cliente = scanner.nextLine();
        Venda venda = new Venda(cliente);

        System.out.print("Quantos livros deseja comprar? ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        for (int i = 0; i < quantidade; i++) {
            System.out.print("Digite o tipo de livro (impresso, eletrônico): ");
            String tipo = scanner.nextLine();
            if (tipo.equalsIgnoreCase("impresso")) {
                listarLivrosImpressos();
                System.out.print("Escolha o livro (número): ");
                int index = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                if (index >= 0 && index < numImpressos) {
                    venda.addLivro(impressos[index], i);
                } else {
                    System.out.println("Índice inválido.");
                }
            } else if (tipo.equalsIgnoreCase("eletrônico")) {
                listarLivrosEletronicos();
                System.out.print("Escolha o livro (número): ");
                int index = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                if (index >= 0 && index < numEletronicos) {
                    venda.addLivro(eletronicos[index], i);
                } else {
                    System.out.println("Índice inválido.");
                }
            } else {
                System.out.println("Tipo de livro inválido.");
            }
        }

        vendas[numVendas++] = venda;
        System.out.println("Venda realizada com sucesso!");
    }

    public void listarLivrosImpressos() {
        System.out.println("Livros Impressos:");
        for (int i = 0; i < numImpressos; i++) {
            System.out.println((i + 1) + ": " + impressos[i]);
        }
    }

    public void listarLivrosEletronicos() {
        System.out.println("Livros Eletrônicos:");
        for (int i = 0; i < numEletronicos; i++) {
            System.out.println((i + 1) + ": " + eletronicos[i]);
        }
    }

    public void listarLivros() {
        listarLivrosImpressos();
        listarLivrosEletronicos();
    }

    public void listarVendas() {
        System.out.println("Vendas Realizadas:");
        for (int i = 0; i < numVendas; i++) {
            System.out.println("Venda #" + vendas[i].getNumero() + " - Cliente: " + vendas[i].getCliente() + ", Valor: " + vendas[i].getValor());
            vendas[i].listarLivros();
        }
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