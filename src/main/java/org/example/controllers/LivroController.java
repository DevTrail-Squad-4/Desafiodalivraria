package org.example.controllers;

import org.example.models.Impresso;
import org.example.models.Eletronico;
import org.example.models.Livro;
import org.example.services.LivroService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LivroController {

    private LivroService livroService;

    // Construtor que aceita um LivroService como parâmetro
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    public void cadastrarLivro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        String titulo = request.getParameter("titulo");
        String autores = request.getParameter("autores");
        String editora = request.getParameter("editora");
        double preco = Double.parseDouble(request.getParameter("preco"));

        if ("impresso".equals(tipo)) {
            double frete = Double.parseDouble(request.getParameter("frete"));
            int estoque = Integer.parseInt(request.getParameter("estoque"));
            Impresso livro = new Impresso(titulo, autores, editora, preco, frete, estoque);
            livroService.cadastrarLivro(livro);

        } else if ("eletronico".equals(tipo)) {
            int tamanho = Integer.parseInt(request.getParameter("tamanho"));
            Eletronico livro = new Eletronico(titulo, autores, editora, preco, tamanho);
            livroService.cadastrarLivro(livro);
        }

        response.sendRedirect("listarLivros.jsp");
    }

    public void listarLivros(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Livro> livros = livroService.listarTodosLivros();
        request.setAttribute("livros", livros);
        try {
            request.getRequestDispatcher("listarLivros.jsp").forward(request, response);
        } catch (Exception e) {
            throw new IOException("Erro ao encaminhar para a página de listagem de livros", e);
        }
    }
}
