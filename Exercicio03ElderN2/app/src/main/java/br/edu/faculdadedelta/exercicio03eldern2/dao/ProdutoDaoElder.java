package br.edu.faculdadedelta.exercicio03eldern2.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio03eldern2.modelo.ProdutoElder;

public class ProdutoDaoElder {

    private static List<ProdutoElder> listaProdutos = new ArrayList<>();
    private static Long idGenerator = 1L;

    public void incluir(ProdutoElder produto) {
        produto.setId(idGenerator++);
        listaProdutos.add(produto);
    }

    public void remover(ProdutoElder produto) {
        listaProdutos.remove(produto);
    }

    public List<ProdutoElder> listar() {
        return listaProdutos;
    }

    public void alterar(ProdutoElder produto) {
        for (ProdutoElder produtoAux : listaProdutos) {
            long idproduto = produto.getId();
            long idProdutoAux = produtoAux.getId();
            if (idproduto == idProdutoAux) {
                listaProdutos.remove(produtoAux);
                listaProdutos.add(produto);
                break;
            }
        }
    }
}
