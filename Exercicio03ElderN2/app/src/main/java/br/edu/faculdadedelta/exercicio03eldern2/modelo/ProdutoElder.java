package br.edu.faculdadedelta.exercicio03eldern2.modelo;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.util.Objects;

public class ProdutoElder implements Serializable {

    private Long id;
    private String produto;
    private String fornecedor;
    private double valor;

    public ProdutoElder() {
    }

    public ProdutoElder(Long id, String produto, String fornecedor, double valor) {
        this.id = id;
        this.produto = produto;
        this.fornecedor = fornecedor;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoElder)) return false;
        ProdutoElder that = (ProdutoElder) o;
        return Objects.equals(getId(), that.getId());
    }

    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
