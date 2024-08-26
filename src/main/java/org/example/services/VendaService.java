package org.example.services;

import org.example.dao.VendaDAO;
import org.example.models.Venda;
import java.util.List;

public class VendaService {

    private VendaDAO vendaDAO;

    public VendaService(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public void realizarVenda(Venda venda) {
        vendaDAO.salvar(venda);
    }

    public List<Venda> listarTodasVendas() {
        return vendaDAO.listarTodos();
    }

    public void deletarLivro(Venda venda) {
        vendaDAO.deletar(venda);
}
}