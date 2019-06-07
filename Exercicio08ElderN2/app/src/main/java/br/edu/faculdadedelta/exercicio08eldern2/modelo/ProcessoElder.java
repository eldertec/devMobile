package br.edu.faculdadedelta.exercicio08eldern2.modelo;

import java.io.Serializable;
import java.util.Date;

public class ProcessoElder implements Serializable {

    private Long id;
    private int numero;
    private String assunto;
    private double valor;
    private Date dataAutuacao;

    public ProcessoElder() {
    }

    public ProcessoElder(Long id, int numero, String assunto, double valor, Date dataAutuacao) {
        this.id = id;
        this.numero = numero;
        this.assunto = assunto;
        this.valor = valor;
        this.dataAutuacao = dataAutuacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataAutuacao() {
        return dataAutuacao;
    }

    public void setDataAutuacao(Date dataAutuacao) {
        this.dataAutuacao = dataAutuacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessoElder)) return false;

        ProcessoElder processoElder = (ProcessoElder) o;

        return getId().equals(processoElder.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
