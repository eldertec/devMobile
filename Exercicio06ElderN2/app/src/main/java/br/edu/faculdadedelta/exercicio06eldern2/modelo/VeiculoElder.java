package br.edu.faculdadedelta.exercicio06eldern2.modelo;

import java.io.Serializable;
import java.util.Date;

public class VeiculoElder implements Serializable {

    private Long id;
    private String Placa;
    private String marca;
    private String modelo;
    private Date dataFabricacao;

    public VeiculoElder() {
    }

    public VeiculoElder(Long id, String placa, String marca, String modelo, Date dataFabricacao) {
        this.id = id;
        Placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.dataFabricacao = dataFabricacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VeiculoElder)) return false;

        VeiculoElder that = (VeiculoElder) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
