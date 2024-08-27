package org.example;

import org.example.dao.LivroDAO;
import org.example.dao.VendaDAO;
import org.example.models.*;
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
        LivrariaVirtual livrariaVirtual = new LivrariaVirtual();
        while (true) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarLivro(livrariaVirtual);
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
                    System.out.println("Saindo da Livraria...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println(" \n--Bem vindo a Livraria-- ");
        System.out.println("*** Menu Principal ***");
        System.out.println();
        System.out.println("1 -> Cadastrar livro:");
        System.out.println("2 -> Realizar venda:");
        System.out.println("3 -> Listar livros:");
        System.out.println("4 -> Listar vendas:");
        System.out.println("5 -> Sair:");
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

    private static void cadastrarLivro(LivrariaVirtual livraria) {
        System.out.println("Opção 'Cadastrar livro' selecionada.");

        int tipoLivro = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("Escolha o tipo de livro:");
            System.out.println("1. Livro Impresso");
            System.out.println("2. Livro Eletrônico");
            System.out.println("3. Ambos");

            try {
                tipoLivro = scanner.nextInt();
                scanner.nextLine(); // Limpa a linha de entrada
                if (tipoLivro == 1 || tipoLivro == 2 || tipoLivro == 3) {
                    entradaValida = true;
                } else {
                    System.out.println("Opção inválida. Por favor, selecione 1 para Livro Impresso, 2 para Livro Eletrônico, ou 3 para Ambos.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa a linha de entrada
            }
        }

        // Verificar se há espaço para mais livros impressos e/ou eletrônicos
        if (tipoLivro == 1 || tipoLivro == 3) {
            if (livraria.getNumImpressos() >= LivrariaVirtual.MAX_IMPRESSOS) {
                System.out.println("Limite de livros impressos atingido. Não é possível cadastrar mais livros impressos.");
                tipoLivro = (tipoLivro == 3) ? 2 : 0; // Se a opção era "Ambos", continuar com o livro eletrônico. Se era "Impresso", cancelar.
            }
        }

        if (tipoLivro == 2 || tipoLivro == 3) {
            if (livraria.getNumEletronicos() >= LivrariaVirtual.MAX_ELETRONICOS) {
                System.out.println("Limite de livros eletrônicos atingido. Não é possível cadastrar mais livros eletrônicos.");
                tipoLivro = (tipoLivro == 3) ? 1 : 0; // Se a opção era "Ambos", continuar com o livro impresso. Se era "Eletrônico", cancelar.
            }
        }

        // Se nenhum tipo de livro puder ser cadastrado, sair
        if (tipoLivro == 0) {
            System.out.println("Nenhum livro foi cadastrado.");
            return;
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

        if (tipoLivro == 1 || tipoLivro == 3) {
            // Livro Impresso
            System.out.print("Digite o frete do livro: ");
            double frete = scanner.nextDouble();
            System.out.print("Digite o estoque do livro: ");
            int estoque = scanner.nextInt();
            scanner.nextLine(); // Limpa a linha de entrada
            Impresso livroImpresso = new Impresso(titulo, autor, editora, preco, frete, estoque);
            livraria.getImpressos().add(livroImpresso);
            livraria.setNumImpressos(livraria.getNumImpressos() + 1);
            System.out.println("Livro Impresso '" + titulo + "' de " + autor + " cadastrado com sucesso por R$" + preco + ".");
        }

        if (tipoLivro == 2 || tipoLivro == 3) {
            // Livro Eletrônico
            System.out.print("Digite o tamanho do arquivo (em KB): ");
            int tamanhoArquivo = scanner.nextInt();
            scanner.nextLine(); // Limpa a linha de entrada
            Eletronico livroEletronico = new Eletronico(titulo, autor, editora, preco, tamanhoArquivo);
            livraria.getEletronicos().add(livroEletronico);
            livraria.setNumEletronicos(livraria.getNumEletronicos() + 1);
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
                if(livros.get(j) instanceof Impresso){
                    Impresso livro = (Impresso) livros.get(j);
                    System.out.println((j + 1) + ". " + livro.getTitulo() + " - " + livro.getPreco() + " - Estoque: " + livro.getEstoque());
                }else {
                    Livro livro = livros.get(j);
                    System.out.println((j + 1) + ". " + livro.getTitulo() + " - " + livro.getPreco());
                }


            }

            // Solicitar escolha do livro
            System.out.print("Escolha o número do livro para o livro " + (i + 1) + ": ");
            int escolha = scanner.nextInt();
            if (escolha < 1 || escolha > livros.size()) {
                System.out.println("Escolha inválida. Tente novamente.");
                i--; // Decrementar para repetir a iteração atual
                continue;
            }

            // Criar e registrar a venda
            Livro livroSelecionado = livros.get(escolha - 1);
            if ("impresso".equalsIgnoreCase(tipo)) {
                Impresso livroImpresso = (Impresso) livros.get(escolha - 1);
                // Verificar e atualizar o estoque
                if (livroImpresso.getEstoque() > 0) {
                    // Adicionar livro selecionado à lista
                    livrosSelecionados.add(livroSelecionado);

                    // Registrar a venda
                    Venda venda = new Venda(cliente, livrosSelecionados);
                    vendaService.realizarVenda(venda);

                    System.out.println("Venda registrada com sucesso!");
                    livroImpresso.setEstoque(livroImpresso.getEstoque() - 1);
                    livroService.livroUpdate(livroImpresso); // Atualizar o livro no banco de dados
                } else {
                    System.out.println("Estoque insuficiente para o livro " + livroSelecionado.getTitulo());
                    i--; // Decrementar para repetir a iteração atual
                    continue;
                }
            }else if("eletronico".equalsIgnoreCase(tipo)){
                livrosSelecionados.add(livroSelecionado);

                // Registrar a venda
                Venda venda = new Venda(cliente, livrosSelecionados);
                vendaService.realizarVenda(venda);

                System.out.println("Venda registrada com sucesso!");
            }


        }
    }

    private static void listarLivros() {
        System.out.println("Opção 'Listar livros' selecionada.");
        // Implementar a lógica de listar livros aqui
        List<Livro> livros = livroService.listarTodosLivros();
        System.out.println(livros);
        System.out.println("Lista de todos os livros cadastrados...");
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