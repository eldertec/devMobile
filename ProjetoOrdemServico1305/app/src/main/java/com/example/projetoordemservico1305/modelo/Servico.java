package com.example.projetoordemservico1305.modelo;

import java.io.Serializable;
import java.util.Date;

public class Servico implements Serializable {

    private Long id;
    private String cliente;
    private String servico;
    private int quantidade;
    private double valor;
    private Date dataExecucao;
    private double valorTotal;
    private double desconto;

    public Servico() {
    }

    public Servico(Long id, String cliente, String servico, int quantidade, double valor, Date dataExecucao) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataExecucao = dataExecucao;
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

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public double getValorTotal() {
        return valorTotal = (valor * quantidade) - desconto;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDesconto() {
        if(valorTotal > 500){
            desconto = valorTotal * 0.03;
        }
        if(dataExecucao.after(new Date("01/01/2019"))){
            desconto += (valorTotal * 0.01);
        }

        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servico)) return false;

        Servico servico = (Servico) o;

        return getId().equals(servico.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
