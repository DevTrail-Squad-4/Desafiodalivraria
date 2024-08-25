package org.example.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

public class Eletronico extends Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private int tamanho;



    public Eletronico() {
        super();
        this.tamanho = 0;
    }

    public Eletronico(String titulo, String autores, String editora, float preco, int tamanho) {
        super(titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return String.format("%s%nTamanho do Arquivo: %d KB",
                super.toString(), tamanho);
    }


    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
