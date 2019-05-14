package com.example.projetoviagem0605.modelo;

import java.io.Serializable;
import java.util.Date;

public class Viagem implements Serializable {

    private Long id;
    private String destino;
    private Date dataSaida;
    private Date dataChegada;
    private String itinerario;
    private String funcionario;
    private double valor;

    public Viagem() {
    }

    public Viagem(Long id, String destino, Date dataSaida, Date dataChegada, String itinerario, String funcionario, double valor) {
        this.id = id;
        this.destino = destino;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.itinerario = itinerario;
        this.funcionario = funcionario;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
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
        if (!(o instanceof Viagem)) return false;

        Viagem viagem = (Viagem) o;

        return getId().equals(viagem.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
