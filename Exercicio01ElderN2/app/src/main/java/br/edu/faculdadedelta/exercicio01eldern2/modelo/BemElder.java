package br.edu.faculdadedelta.exercicio01eldern2.modelo;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.util.Objects;

public class BemElder implements Serializable {

    private Long id;
    private String descricao;
    private String especificacao;
    private String departamento;
    private double valor;
    private int quantidade;

    public BemElder() {
    }

    public BemElder(Long id, String descricao, String especificacao, String departamento, double valor, int quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.especificacao = especificacao;
        this.departamento = departamento;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
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
        if (!(o instanceof BemElder)) return false;
        BemElder bemElder = (BemElder) o;
        return Objects.equals(getId(), bemElder.getId());
    }

    @SuppressLint("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
