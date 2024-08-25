package org.example.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Impresso extends Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    private double frete;
    private int estoque;

    public Impresso() {
        super();
        this.frete = 0.0;
        this.estoque = 0;
    }

    public Impresso(String titulo, String autores, String editora, double preco, double frete, int estoque) {
        super(titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return String.format("%s%nFrete: R$ %.2f%nEstoque: %d unidades",
                super.toString(), frete, estoque);
    }


    public void atualizarEstoque(){
        if (this.estoque > 0) {
            this.estoque--;
        } else {
            System.out.println("Estoque esgotado!");
        }
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
