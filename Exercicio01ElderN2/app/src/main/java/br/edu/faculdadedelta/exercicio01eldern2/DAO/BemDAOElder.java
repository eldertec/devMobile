package br.edu.faculdadedelta.exercicio01eldern2.DAO;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio01eldern2.modelo.BemElder;

public class BemDAOElder {

    private static List<BemElder> listaBens = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(BemElder bem) {
        bem.setId(idGenerator++);
        listaBens.add(bem);
    }

    public void remover(BemElder bem) {
        listaBens.remove(bem);
    }

    public List<BemElder> listar() {
        return listaBens;
    }

    public void alterar(BemElder bem) {
        for (BemElder bemAux : listaBens) {
            long idBem = bem.getId();
            long idBemAux = bemAux.getId();
            if (idBem == idBemAux) {
                listaBens.remove(bemAux);
                listaBens.add(bem);
                break;
            }
        }
    }

}
