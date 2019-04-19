package br.edu.faculdadedelta.exercicio02eldern2.modelo;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.util.Objects;

public class VendaElder implements Serializable {

    private Long id;
    private String cliente;
    private String produto;
    private double valor;
    private int quantidade;

    public VendaElder() {
    }

    public VendaElder(Long id, String cliente, String produto, double valor, int quantidade) {
        this.id = id;
        this.cliente = cliente;
        this.produto = produto;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendaElder)) return false;
        VendaElder that = (VendaElder) o;
        return Objects.equals(getId(), that.getId());
    }

    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
