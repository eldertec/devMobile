package br.edu.faculdadedelta.exercicio06eldern2;

import android.support.design.widget.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.edu.faculdadedelta.exercicio06eldern2.modelo.VeiculoElder;

public class FormularioHelper {

    private TextInputEditText etPlaca;
    private TextInputEditText etMarca;
    private TextInputEditText etModelo;
    private TextInputEditText etDtFabricacao;

    private VeiculoElder veiculo;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public FormularioHelper(FormularioActivity activity){
        etPlaca = activity.findViewById(R.id.formPlaca);
        etMarca = activity.findViewById(R.id.formMarca);
        etModelo = activity.findViewById(R.id.formModelo);
        etDtFabricacao = activity.findViewById(R.id.formDtFabricacao);
        veiculo = new VeiculoElder();
    }

    public VeiculoElder popularModelo(){
        veiculo.setPlaca(etPlaca.getText().toString());
        veiculo.setMarca(etMarca.getText().toString());
        veiculo.setModelo(etModelo.getText().toString());

        try {
            veiculo.setDataFabricacao(sdf.parse(etDtFabricacao.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return veiculo;
    }
}
