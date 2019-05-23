package br.edu.faculdadedelta.exercicio07eldern2.modelo;

import java.io.Serializable;
import java.util.Date;

public class MotoristaElder implements Serializable {

    private Long id;
    private String nome;
    private String cpf;
    private String cnh;
    private Date datanascimento;

    public MotoristaElder() {
    }

    public MotoristaElder(Long id, String nome, String cpf, String cnh, Date datanascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.datanascimento = datanascimento;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MotoristaElder)) return false;

        MotoristaElder that = (MotoristaElder) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
