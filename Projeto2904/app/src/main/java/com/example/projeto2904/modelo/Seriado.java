package com.example.projeto2904.modelo;

import java.io.Serializable;

public class Seriado implements Serializable {

    private Long id;
    private String titulo;
    private String genero;
    private int temporada;
    private int nota;
    private int anoLancamento;

    public Seriado() {
    }

    public Seriado(Long id, String titulo, String genero, int temporada, int nota, int anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.temporada = temporada;
        this.nota = nota;
        this.anoLancamento = anoLancamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seriado)) return false;

        Seriado seriado = (Seriado) o;

        return getId().equals(seriado.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
