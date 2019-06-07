package br.edu.faculdadedelta.exercicio8elder.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio8elder.modelo.ProcessoElder;

public class ProcessoDaoElder {

    private static List<ProcessoElder> listaProcessos = new ArrayList<>();
    private static Long idGerador = 1L;

    public void incluir(ProcessoElder processo){
        processo.setId(idGerador++);
        listaProcessos.add(processo);
    }

    public void remover(ProcessoElder processo){
        listaProcessos.remove(processo);
    }

    public List<ProcessoElder> listar(){
        return listaProcessos;
    }

    public void alterar(ProcessoElder processo){
        for(ProcessoElder processoAux : listaProcessos){
            long idProcesso = processo.getId();
            long idProcessoAux = processoAux.getId();
            if(idProcesso == idProcessoAux){
                listaProcessos.remove(processoAux);
                listaProcessos.add(processo);
                break;
            }
        }
    }

}
