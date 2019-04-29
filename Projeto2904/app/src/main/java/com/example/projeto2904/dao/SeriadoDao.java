package com.example.projeto2904.dao;

import com.example.projeto2904.modelo.Seriado;

import java.util.ArrayList;
import java.util.List;

public class SeriadoDao {

    private static List<Seriado> listaSeriados = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(Seriado seriado) {
        seriado.setId(idGenerator++);
        listaSeriados.add(seriado);
    }

    public void remover(Seriado seriado) {
        listaSeriados.remove(seriado);
    }

    public List<Seriado> listar() {
        return listaSeriados;
    }

    public void alterar(Seriado seriado) {
        for (Seriado seriadoAux : listaSeriados) {
            long seriadoId = seriado.getId();
            long seriadoAuxId = seriadoAux.getId();
            if (seriadoId == seriadoAuxId) {
                listaSeriados.remove(seriadoAux);
                listaSeriados.add(seriado);
                break;
            }
        }
    }
}
