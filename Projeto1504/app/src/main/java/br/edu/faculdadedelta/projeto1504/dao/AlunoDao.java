package br.edu.faculdadedelta.projeto1504.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projeto1504.modelo.Aluno;

public class AlunoDao {

    private static List<Aluno> listaAlunos = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(Aluno aluno) {
        aluno.setId(idGenerator++);
        listaAlunos.add(aluno);
    }

    public void remover(Aluno aluno) {
        listaAlunos.remove(aluno);
    }

    public List<Aluno> listar() {
        return listaAlunos;
    }

    public void alterar(Aluno aluno) {
        for (Aluno alunoAux : listaAlunos) {
            long idAluno = aluno.getId();
            long idAlunoAux = alunoAux.getId();
            if (idAluno == idAlunoAux) {
                listaAlunos.remove(alunoAux);
                listaAlunos.add(aluno);
                break;
            }
        }
    }
}
