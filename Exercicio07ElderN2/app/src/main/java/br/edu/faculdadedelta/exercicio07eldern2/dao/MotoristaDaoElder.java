package br.edu.faculdadedelta.exercicio07eldern2.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio07eldern2.modelo.MotoristaElder;

public class MotoristaDaoElder {

    private static List<MotoristaElder> listaMotoristas = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(MotoristaElder motorista) {
        motorista.setId(idGenerator++);
        listaMotoristas.add(motorista);
    }

    public void remover(MotoristaElder motorista) {
        listaMotoristas.remove(motorista);
    }

    public List<MotoristaElder> listar() {
        return listaMotoristas;
    }

    public void alterar(MotoristaElder motorista) {
        for (MotoristaElder motoristaAux : listaMotoristas) {
            long idMotorista = motorista.getId();
            long idMotoristaAux = motoristaAux.getId();
            if (idMotorista == idMotoristaAux) {
                listaMotoristas.remove(motoristaAux);
                listaMotoristas.add(motorista);
                break;
            }
        }
    }
}
