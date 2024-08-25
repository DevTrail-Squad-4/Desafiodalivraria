package org.example.services;

import org.example.models.Livro;
import org.example.models.Impresso;
import org.example.models.Eletronico;
import org.example.repository.LivroRepository;
import java.util.List;

public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void cadastrarLivro(Livro livro) {
        livroRepository.save(livro);
    }

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public List<Impresso> listarLivrosImpressos() {
        return livroRepository.findImpressos();
    }

    public List<Eletronico> listarLivrosEletronicos() {
        return livroRepository.findEletronicos();
    }
}