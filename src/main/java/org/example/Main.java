package org.example;

import org.example.dao.LivroDAO;
import org.example.dao.VendaDAO;
import org.example.models.Eletronico;
import org.example.models.Impresso;
import org.example.models.Livro;
import org.example.models.Venda;
import org.example.services.LivroService;
import org.example.services.VendaService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LivroService livroService = new LivroService(new LivroDAO());
    private static final VendaService vendaService = new VendaService(new VendaDAO());

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    realizarVenda();
                    break;
                case 3:
                    listarLivros();
                    break;
                case 4:
                    listarVendas();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n*** Menu Principal ***");
        System.out.println("1. Cadastrar livro");
        System.out.println("2. Realizar venda");
        System.out.println("3. Listar livros");
        System.out.println("4. Listar vendas");
        System.out.println("5. Sair");
    }

    private static int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número.");
            scanner.next();
            System.out.print("Escolha uma opção: ");
        }
        return scanner.nextInt();
    }

    private static void cadastrarLivro() {
        System.out.println("Opção 'Cadastrar livro' selecionada.");

        int tipoLivro = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("Escolha o tipo de livro:");
            System.out.println("1. Livro Impresso");
            System.out.println("2. Livro Eletrônico");

            try {
                tipoLivro = scanner.nextInt();
                scanner.nextLine(); // Limpa a linha de entrada
                if (tipoLivro == 1 || tipoLivro == 2) {
                    entradaValida = true;
                } else {
                    System.out.println("Opção inválida. Por favor, selecione 1 para Livro Impresso ou 2 para Livro Eletrônico.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa a linha de entrada
            }
        }

        // Coletar detalhes do livro
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o preço do livro: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpa a linha de entrada
        System.out.print("Digite a editora do livro: ");
        String editora = scanner.nextLine();

        if (tipoLivro == 1) {
            // Livro Impresso
            System.out.print("Digite o frete do livro: ");
            double frete = scanner.nextDouble();
            System.out.print("Digite o estoque do livro: ");
            int estoque = scanner.nextInt();
            scanner.nextLine(); // Limpa a linha de entrada
            Impresso livro = new Impresso(titulo, autor, editora, preco, frete, estoque);
            livroService.cadastrarLivro(livro);
            System.out.println("Livro Impresso '" + titulo + "' de " + autor + " cadastrado com sucesso por R$" + preco + ".");
        } else {
            // Livro Eletrônico
            System.out.print("Digite o tamanho do arquivo (em MB): ");
            int tamanhoArquivo = scanner.nextInt();
            scanner.nextLine(); // Limpa a linha de entrada
            Eletronico livro = new Eletronico(titulo, autor, editora, preco, tamanhoArquivo);
            livroService.cadastrarLivro(livro);
            System.out.println("Livro Eletrônico '" + titulo + "' de " + autor + " cadastrado com sucesso por R$" + preco + ".");
        }
    }


    private static void realizarVenda() {
        System.out.println("Opção 'Realizar venda' selecionada.");

        // Solicitar nome do cliente
        System.out.print("Digite o nome do cliente: ");
        String cliente = scanner.next();

        // Solicitar quantidade de livros
        System.out.print("Digite a quantidade de livros que deseja comprar: ");
        int quantidade = scanner.nextInt();

        // Inicializar a lista de livros selecionados
        List<Livro> livrosSelecionados = new ArrayList<>();

        // Iterar sobre a quantidade de livros
        for (int i = 0; i < quantidade; i++) {
            // Solicitar tipo de livro
            System.out.print("Digite o tipo de livro (impresso ou eletrônico) para o livro " + (i + 1) + ": ");
            String tipo = scanner.next();

            // Inicializar a lista de livros
            List<Livro> livros = new ArrayList<>();

            // Listar livros de acordo com o tipo escolhido
            if ("impresso".equalsIgnoreCase(tipo)) {
                List<Impresso> impressos = livroService.listarLivrosImpressos();
                livros.addAll(impressos); // Converter para List<Livro>
            } else if ("eletronico".equalsIgnoreCase(tipo)) {
                List<Eletronico> eletronicos = livroService.listarLivrosEletronicos();
                livros.addAll(eletronicos); // Converter para List<Livro>
            } else {
                System.out.println("Tipo de livro inválido. Tente novamente.");
                i--; // Decrementar para repetir a iteração atual
                continue;
            }

            // Exibir lista de livros
            System.out.println("Lista de livros " + tipo + ":");
            for (int j = 0; j < livros.size(); j++) {
                Livro livro = livros.get(j);
                System.out.println((j + 1) + ". " + livro.getTitulo() + " - " + livro.getPreco());
            }

            // Solicitar escolha do livro
            System.out.print("Escolha o número do livro para o livro " + (i + 1) + ": ");
            int escolha = scanner.nextInt();
            if (escolha < 1 || escolha > livros.size()) {
                System.out.println("Escolha inválida. Tente novamente.");
                i--; // Decrementar para repetir a iteração atual
                continue;
            }

            // Adicionar livro selecionado à lista
            Livro livroSelecionado = livros.get(escolha - 1);
            livrosSelecionados.add(livroSelecionado);
        }

        // Criar e registrar a venda
        Venda venda = new Venda(cliente, livrosSelecionados);
        vendaService.realizarVenda(venda);

        System.out.println("Venda registrada com sucesso!");
    }


    private static void listarLivros() {
        System.out.println("Opção 'Listar livros' selecionada.");
        // Implementar a lógica de listar livros aqui
        List<Livro> livros = livroService.listarTodosLivros();
        System.out.println(livros);
        System.out.println("Listando todos os livros cadastrados...");
    }

    private static void listarVendas() {
        System.out.println("Opção 'Listar vendas' selecionada.");

        // Listar todas as vendas
        List<Venda> vendas = vendaService.listarTodasVendas();

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
        } else {
            System.out.println("Vendas realizadas:");
            for (Venda venda : vendas) {
                // Força a inicialização da coleção 'livros'
                if (venda.getLivros() != null) {
                    venda.getLivros().size();
                }
                System.out.println(venda);
            }
        }
    }

}