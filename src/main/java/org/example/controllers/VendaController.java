package org.example.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.example.models.Livro;
import org.example.models.Venda;
import org.example.services.LivroService;
import org.example.services.VendaService;

public class VendaController {

    private VendaService vendaService; 
    private LivroService livroService; 


    public VendaController(VendaService vendaService, LivroService livroService) {
        this.vendaService = vendaService;
        this.livroService = livroService;
    }

    public void realizarVenda(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cliente = request.getParameter("cliente");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        List<Livro> livrosSelecionados = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            Long livroId = Long.parseLong(request.getParameter("livroId" + i));
            Livro livro = livroService.buscarLivroPorId(livroId);
            if (livro != null) {
                livrosSelecionados.add(livro);
            }
        }

        Venda venda = new Venda(cliente, livrosSelecionados);
        vendaService.realizarVenda(venda);

        response.sendRedirect("listarVendas.jsp");
    }

    public void listarVendas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Venda> vendas = vendaService.listarTodasVendas();
        request.setAttribute("vendas", vendas);
        try {
            request.getRequestDispatcher("listarVendas.jsp").forward(request, response);
        } catch (Exception e) {
            throw new IOException("Erro ao encaminhar para a página de listagem de vendas", e);
        }
    }
}
