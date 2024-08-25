package org.example.controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.models.Impresso;
import org.example.models.Eletronico;
import java.io.IOException;
import java.util.List;

public class LivroControler {

    private LivroService service = new LivroService();

    public void cadastrarLivro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        String titulo =  request.getParameter("titulo");
        String autores = request.getParameter("autores");
        String editora = request.getParameter("editora");
        double preco = Double.parseDouble(request.getParameter("preco"));   
        
        if("impresso".equals(tipo)){
            double frete = Double.parseDouble(request.getParameter("frete"));
            int estoque = Integer.parseInt(request.getParameter("estoque"));
            Impresso livro = new Impresso(titulo, autores, editora, preco, frete, estoque);
            LivroService.cadastrarLivro(livro);
    
        }else if("eletronico".equals(tipo)){
            int tamanho = Integer.parseInt(request.getParameter("tamanho"));
            Eletronico livro = new Eletronico(titulo, autores, editora, preco, tamanho);
            livroService.cadastrarLivro(livro);

        }

        response.sendRedirect("listarLivros.jsp");

    }

    public void listarLivros(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Livro> livros = LivroService.listarLivros();
        request.setAttribute("livros", livros);
        request.getRequestDispatcher("listarLivros.jsp").forward(request, response);
    }
}
