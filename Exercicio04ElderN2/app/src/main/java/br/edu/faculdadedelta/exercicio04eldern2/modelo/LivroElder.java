package br.edu.faculdadedelta.exercicio04eldern2.modelo;

import java.io.Serializable;

public class LivroElder implements Serializable {

    private Long id;
    private String nome;
    private String editora;
    private double valor;

    public LivroElder() {
    }

    public LivroElder(Long id, String nome, String editora, double valor) {
        this.id = id;
        this.nome = nome;
        this.editora = editora;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LivroElder)) return false;

        LivroElder that = (LivroElder) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
