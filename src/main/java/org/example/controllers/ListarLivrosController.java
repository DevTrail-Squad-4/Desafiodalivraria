package org.example.controllers;
import org.example.models.Impresso;
import org.example.models.Eletronico;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListarLivrosController {

    private LivroService livroService = new LivroService(); // Serviço que interage com a camada de persistência

    public void listarLivrosImpressos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Impresso> livrosImpressos = livroService.listarLivrosImpressos();
        request.setAttribute("livrosImpressos", livrosImpressos);
        request.getRequestDispatcher("listarLivrosImpressos.jsp").forward(request, response);
    }

    public void listarLivrosEletronicos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Eletronico> livrosEletronicos = livroService.listarLivrosEletronicos();
        request.setAttribute("livrosEletronicos", livrosEletronicos);
        request.getRequestDispatcher("listarLivrosEletronicos.jsp").forward(request, response);
    }
}
