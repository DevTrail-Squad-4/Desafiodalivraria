import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

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
        // Implementar a lógica de cadastro de livro aqui
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.next();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.next();
        System.out.print("Digite o preço do livro: ");
        double preco = scanner.nextDouble();
        System.out.println("Livro '" + titulo + "' de " + autor + " cadastrado com sucesso por R$" + preco + ".");
    }

    private static void realizarVenda() {
        System.out.println("Opção 'Realizar venda' selecionada.");
        // Implementar a lógica de realizar a venda aqui
        System.out.print("Digite o ID do livro para venda: ");
        int livroId = scanner.nextInt();
        System.out.print("Digite a quantidade vendida: ");
        int quantidade = scanner.nextInt();
        System.out.println("Venda registrada: Livro ID " + livroId + ", Quantidade " + quantidade + ".");
    }

    private static void listarLivros() {
        System.out.println("Opção 'Listar livros' selecionada.");
        // Implementar a lógica de listar livros aqui
        System.out.println("Listando todos os livros cadastrados...");
    }

    private static void listarVendas() {
        System.out.println("Opção 'Listar vendas' selecionada.");
        // Implementar a lógica de listar vendas aqui
        System.out.println("Listando todas as vendas realizadas...");
    }
}
