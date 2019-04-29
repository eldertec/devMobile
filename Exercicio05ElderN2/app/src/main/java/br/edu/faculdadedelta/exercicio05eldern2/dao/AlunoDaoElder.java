package br.edu.faculdadedelta.exercicio05eldern2.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio05eldern2.modelo.AlunoElder;

public class AlunoDaoElder {

    private static List<AlunoElder> listaAlunos = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(AlunoElder aluno) {
        aluno.setId(idGenerator++);
        listaAlunos.add(aluno);
    }

    public void remover(AlunoElder aluno) {
        listaAlunos.remove(aluno);
    }

    public List<AlunoElder> listar() {
        return listaAlunos;
    }

    public void alterar(AlunoElder aluno) {
        for (AlunoElder alunoAux : listaAlunos) {
            long alunoId = aluno.getId();
            long alunoAuxId = alunoAux.getId();
            if (alunoAuxId == alunoId) {
                listaAlunos.remove(alunoAux);
                listaAlunos.add(aluno);
                break;
            }
        }
    }

}
