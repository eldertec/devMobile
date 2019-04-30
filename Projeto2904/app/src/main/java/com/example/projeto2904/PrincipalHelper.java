package com.example.projeto2904;

import android.widget.EditText;

import com.example.projeto2904.modelo.Seriado;

public class PrincipalHelper {

    private EditText etTitulo;
    private EditText etGenero;
    private EditText etTemporada;
    private EditText etNota;
    private EditText etAnoLancamento;

    private Seriado seriado;

    public PrincipalHelper(PrincipalActivity activity) {
        etTitulo = activity.findViewById(R.id.etTitulo);
        etGenero = activity.findViewById(R.id.etGenero);
        etTemporada = activity.findViewById(R.id.etTemporada);
        etNota = activity.findViewById(R.id.etNota);
        etAnoLancamento = activity.findViewById(R.id.etAnoLancamento);
        seriado = new Seriado();
    }

    public Seriado popularModelo() {
        seriado.setTitulo(etTitulo.getText().toString());
        seriado.setGenero(etGenero.getText().toString());
        seriado.setTemporada(Integer.parseInt(etTemporada.getText().toString()));
        seriado.setNota(Integer.parseInt(etNota.getText().toString()));
        seriado.setAnoLancamento(Integer.parseInt(etAnoLancamento.getText().toString()));
        return seriado;
    }

    public void popularFormulario(Seriado seriado) {
        etTitulo.setText(seriado.getTitulo());
        etGenero.setText(seriado.getGenero());
        etTemporada.setText(String.valueOf(seriado.getTemporada()));
        etNota.setText(String.valueOf(seriado.getNota()));
        etAnoLancamento.setText(String.valueOf(seriado.getAnoLancamento()));

        this.seriado = seriado;
    }

    public void limparCampos() {
        etTitulo.setText("");
        etGenero.setText("");
        etTemporada.setText("");
        etNota.setText("");
        etAnoLancamento.setText("");
        seriado = new Seriado();
    }

    public String validarCampos() {
        String msgRetorno = "";

        if(etTitulo.getText().toString().equals("")){
            msgRetorno = "O Titulo é obrigatório!";
        }
        if(etGenero.getText().toString().equals("")){
            msgRetorno += "\nO Genero é obrigatório!";
        }
        if(etTemporada.getText().toString().equals("")){
            msgRetorno += "\nA Temporada é obrigatória!";
        }
        if(etNota.getText().toString().equals("")){
            msgRetorno += "\nA Nota é obrigatória!";
        }
        if(etAnoLancamento.getText().toString().equals("")){
            msgRetorno += "\nO Ano de Lançamento é obrigatório!";
        }

        return msgRetorno;
    }
}
