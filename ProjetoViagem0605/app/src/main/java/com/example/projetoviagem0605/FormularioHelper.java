package com.example.projetoviagem0605;

import android.text.TextUtils;
import android.widget.EditText;

import com.example.projetoviagem0605.modelo.Viagem;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormularioHelper {

    private EditText etDestino;
    private EditText etDtSaida;
    private EditText etDtChegada;
    private EditText etItinerario;
    private EditText etFuncionario;
    private EditText etValor;

    private Viagem viagem;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public FormularioHelper(FormularioActivity activity){
        etDestino = activity.findViewById(R.id.etDestino);
        etDtSaida = activity.findViewById(R.id.etDtSaida);
        etDtChegada = activity.findViewById(R.id.etDtChegada);
        etItinerario = activity.findViewById(R.id.etItinerario);
        etFuncionario = activity.findViewById(R.id.etFuncionario);
        etValor = activity.findViewById(R.id.etValor);

        viagem = new Viagem();
    }

    public Viagem popularModelo(){
        viagem.setDestino(etDestino.getText().toString());
        try {
            viagem.setDataSaida(sdf.parse(etDtSaida.getText().toString()));
            viagem.setDataChegada(sdf.parse(etDtChegada.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viagem.setItinerario(etItinerario.getText().toString());
        viagem.setFuncionario(etFuncionario.getText().toString());
        viagem.setValor(Double.parseDouble(etValor.getText().toString()));

        return viagem;
    }

    public void popularFormulario(Viagem viagem){
        etDestino.setText(viagem.getDestino());
        etDtSaida.setText(sdf.format(viagem.getDataSaida()));
        etDtChegada.setText(sdf.format(viagem.getDataChegada()));
        etItinerario.setText(viagem.getItinerario());
        etFuncionario.setText(viagem.getFuncionario());
        etValor.setText(String.valueOf(viagem.getValor()));

        this.viagem = viagem;
    }

    public void limparCampos(){
        etDtChegada.setText("");
        etDestino.setText("");
        etDtSaida.setText("");
        etItinerario.setText("");
        etFuncionario.setText("");
        etValor.setText("");
        viagem = new Viagem();
    }

    public String validarCampos(){
        String msgRetorno = "";

        if(TextUtils.isEmpty(etDestino.getText().toString())){
            msgRetorno = "O Destino é obrigatório";
        }
        if(TextUtils.isEmpty(etDtSaida.getText().toString())){
            msgRetorno += "\nA Data de Saída é obrigatória";
        }
        if(TextUtils.isEmpty(etDtChegada.getText().toString())){
            msgRetorno += "\nA Data de Chegada é obrigatória";
        }
        if(TextUtils.isEmpty(etItinerario.getText().toString())){
            msgRetorno += "\nO Itinerário é obrigatório";
        }
        if(TextUtils.isEmpty(etFuncionario.getText().toString())){
            msgRetorno += "\nO Funcionário é obrigatório";
        }
        if(TextUtils.isEmpty(etValor.getText().toString())){
            msgRetorno += "\nO Valor é obrigatório";
        }

        return msgRetorno;
    }


}
