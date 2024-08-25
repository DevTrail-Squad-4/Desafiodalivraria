package org.example.controllers;

import org.example.dao.LivroDAO;
import org.example.models.Impresso;
import org.example.models.Eletronico;
import org.example.services.LivroService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListarLivrosController {

    private LivroService livroService;

    public ListarLivrosController(LivroDAO livroDAO) {
        this.livroService = new LivroService(livroDAO);
    }

    public void listarLivrosImpressos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Impresso> livrosImpressos = livroService.listarLivrosImpressos();
        request.setAttribute("livrosImpressos", livrosImpressos);
        try {
            request.getRequestDispatcher("listarLivrosImpressos.jsp").forward(request, response);
        } catch (Exception e) {
            throw new IOException("Erro ao encaminhar para a página de livros impressos", e);
        }
    }

    public void listarLivrosEletronicos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Eletronico> livrosEletronicos = livroService.listarLivrosEletronicos();
        request.setAttribute("livrosEletronicos", livrosEletronicos);
        try {
            request.getRequestDispatcher("listarLivrosEletronicos.jsp").forward(request, response);
        } catch (Exception e) {
            throw new IOException("Erro ao encaminhar para a página de livros eletrônicos", e);
        }
    }
}
