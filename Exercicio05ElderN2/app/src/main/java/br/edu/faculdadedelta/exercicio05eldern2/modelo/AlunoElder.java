package br.edu.faculdadedelta.exercicio05eldern2.modelo;

import java.io.Serializable;
import java.util.Date;

public class AlunoElder implements Serializable {

    private Long id;
    private String nome;
    private int idade;
    private Date dataNascimento;
    private String instrucao;

    public AlunoElder() {
    }

    public AlunoElder(Long id, String nome, int idade, Date dataNascimento, String instrucao) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
        this.instrucao = instrucao;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlunoElder)) return false;

        AlunoElder that = (AlunoElder) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
