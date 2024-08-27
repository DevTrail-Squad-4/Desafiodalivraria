package org.example.services;

import org.example.dao.LivroDAO;
import org.example.models.Livro;
import org.example.models.Impresso;
import org.example.models.Eletronico;
import java.util.List;

public class LivroService {

    private LivroDAO livroDAO;

    public LivroService(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    public void cadastrarLivro(Livro livro) {
        livroDAO.salvar(livro);
    }

    public List<Livro> listarTodosLivros() {
        return livroDAO.listarTodos();
    }

    public List<Impresso> listarLivrosImpressos() {
        
        return livroDAO.listarImpressos();
    }

    public List<Eletronico> listarLivrosEletronicos() {
        return livroDAO.listarEletronicos();
    }

    public Livro buscarLivroPorId(long Livroid){
        return livroDAO.buscarPorId(Livroid);
    }
            
    public void livroUpdate(Impresso impresso){
        if (impresso.getEstoque() == 0){
            livroDAO.deletar(impresso);

        } else{
            livroDAO.atualizar(impresso);
        }
    }

}