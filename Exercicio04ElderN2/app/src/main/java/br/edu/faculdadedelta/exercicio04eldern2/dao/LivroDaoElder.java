package br.edu.faculdadedelta.exercicio04eldern2.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio04eldern2.modelo.LivroElder;

public class LivroDaoElder {

    private static List<LivroElder> listaLivros = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(LivroElder livro) {
        livro.setId(idGenerator++);
        listaLivros.add(livro);
    }

    public void remover(LivroElder livro) {
        listaLivros.remove(livro);
    }

    public List<LivroElder> listar() {
        return listaLivros;
    }

    public void alterar(LivroElder livro) {
        for (LivroElder livroAux : listaLivros) {
            long livroId = livro.getId();
            long livroAuxId = livroAux.getId();
            if (livroId == livroAuxId) {
                listaLivros.remove(livroAux);
                listaLivros.add(livro);
                break;
            }
        }
    }
}
