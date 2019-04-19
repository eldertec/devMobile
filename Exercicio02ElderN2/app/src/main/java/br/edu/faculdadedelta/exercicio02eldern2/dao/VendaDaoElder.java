package br.edu.faculdadedelta.exercicio02eldern2.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio02eldern2.modelo.VendaElder;

public class VendaDaoElder {

    private static List<VendaElder> listaVendas = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(VendaElder venda) {
        venda.setId(idGenerator++);
        listaVendas.add(venda);
    }

    public void remover(VendaElder venda) {
        listaVendas.remove(venda);
    }

    public List<VendaElder> listar() {
        return listaVendas;
    }

    public void alterar(VendaElder venda) {
        for (VendaElder vendaAux : listaVendas) {
            long idVenda = venda.getId();
            long idVendaAux = vendaAux.getId();
            if (idVenda == idVendaAux) {
                listaVendas.remove(vendaAux);
                listaVendas.add(venda);
                break;
            }
        }
    }
}
