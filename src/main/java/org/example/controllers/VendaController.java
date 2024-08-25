package org.example.controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.example.models.Livro;
import org.example.models.Venda;

public class VendaController {
    private VendaService vendaService = new VendaService(); // Serviço que interage com a camada de persistência
    private LivroService livroService = new LivroService(); // Serviço para buscar livros

    public void realizarVenda(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cliente = request.getParameter("cliente");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        List<Livro> livrosSelecionados = new ArrayList<Livro>();

        for (int i = 0; i < quantidade; i++) {
            Long livroId = Long.parseLong(request.getParameter("livroId" + i));
            Livro livro = livroService.buscarLivroPorId(livroId);
            livrosSelecionados.add(livro);
        }

        Venda venda = new Venda(cliente, livrosSelecionados);
        vendaService.realizarVenda(venda);

        response.sendRedirect("listarVendas.jsp");
    }

    public void listarVendas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Venda> vendas = vendaService.listarVendas();
        request.setAttribute("vendas", vendas);
        request.getRequestDispatcher("listarVendas.jsp").forward(request, response);
    }
}
