package com.example.projetoordemservico1305.dao;

import com.example.projetoordemservico1305.modelo.Servico;

import java.util.ArrayList;
import java.util.List;

public class ServicoDao {

    private static List<Servico> listaServicos = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(Servico servico) {
        servico.setId(idGenerator++);
        listaServicos.add(servico);
    }

    public void remover(Servico servico) {
        listaServicos.remove(servico);
    }

    public List<Servico> listar() {
        return listaServicos;
    }

    public void alterar(Servico servico) {
        for (Servico servicoAux : listaServicos) {
            long idServico = servico.getId();
            long idServicoAux = servicoAux.getId();
            if (idServico == idServicoAux) {
                listaServicos.remove(servicoAux);
                listaServicos.add(servico);
                break;
            }
        }
    }
}
