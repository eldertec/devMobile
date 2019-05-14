package com.example.projetoviagem0605.dao;

import com.example.projetoviagem0605.modelo.Viagem;

import java.util.ArrayList;
import java.util.List;

public class ViagemDao {

    private static List<Viagem> listaViagens = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(Viagem viagem) {
        viagem.setId(idGenerator++);
        listaViagens.add(viagem);
    }

    public void remover(Viagem viagem) {
        listaViagens.remove(viagem);
    }

    public List<Viagem> listar() {
        return listaViagens;
    }

    public void alterar(Viagem viagem) {
        for (Viagem viagenAux : listaViagens) {
            long idViagem = viagem.getId();
            long idViagemAux = viagenAux.getId();
            if (idViagem == idViagemAux) {
                listaViagens.remove(viagenAux);
                listaViagens.add(viagem);
                break;
            }
        }
    }
}
