package br.edu.faculdadedelta.exercicio06eldern2;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.faculdadedelta.exercicio06eldern2.modelo.VeiculoElder;

public class FormularioHelper {

    private TextInputEditText etPlaca;
    private TextInputEditText etMarca;
    private TextInputEditText etModelo;
    private TextInputEditText etDtFabricacao;

    private TextInputLayout ilPlaca;
    private TextInputLayout ilMarca;
    private TextInputLayout ilModelo;
    private TextInputLayout ilDtFabricacao;

    private VeiculoElder veiculo;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public FormularioHelper(FormularioActivity activity){
        etPlaca = activity.findViewById(R.id.formPlaca);
        etMarca = activity.findViewById(R.id.formMarca);
        etModelo = activity.findViewById(R.id.formModelo);
        etDtFabricacao = activity.findViewById(R.id.formDtFabricacao);
        ilPlaca = activity.findViewById(R.id.hintPlaca);
        ilMarca = activity.findViewById(R.id.hintMarca);
        ilModelo = activity.findViewById(R.id.hintModelo);
        ilDtFabricacao = activity.findViewById(R.id.hintDtFabricacao);
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

    public void popularFormulario(VeiculoElder veiculo){
        etMarca.setText(veiculo.getMarca());
        etModelo.setText(veiculo.getModelo());
        etPlaca.setText(veiculo.getPlaca());
        etDtFabricacao.setText(sdf.format(veiculo.getDataFabricacao()));
        this.veiculo = veiculo;
    }

    public void limparCampos(){
        etMarca.setText("");
        etModelo.setText("");
        etPlaca.setText("");
        etDtFabricacao.setText("");
        ilMarca.setError(null);
        ilModelo.setError(null);
        ilPlaca.setError(null);
        ilDtFabricacao.setError(null);
        veiculo = new VeiculoElder();
    }

    public String validarCampos(){
        String msgRetorno = "";

        if(TextUtils.isEmpty(etPlaca.getText().toString())){
            ilPlaca.setError(" ");
            msgRetorno = "A Placa é obrigatória!";
        }
        if(TextUtils.isEmpty(etModelo.getText().toString())){
            ilModelo.setError(" ");
            msgRetorno += "\nO Modelo é obrigatório!";
        }
        if(TextUtils.isEmpty(etMarca.getText().toString())){
            ilMarca.setError(" ");
            msgRetorno += "\nA Marca é obrigatória!";
        }
        if(TextUtils.isEmpty(etDtFabricacao.getText().toString())){
            ilDtFabricacao.setError(" ");
            msgRetorno += "\nA Data de Fabricação é obrigatória!";
        }else{
            try {
                Date dataMin = sdf.parse("02/01/1900");
                Date dataConvertida = sdf.parse(etDtFabricacao.getText().toString());
                Date agora = new Date();
                if(dataConvertida.before(dataMin) || dataConvertida.after(agora)){
                    ilDtFabricacao.setError(" ");
                    msgRetorno += String.format("\nA Data de Fabricação deve de %s a %s!", sdf.format(dataMin) ,sdf.format(agora));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return msgRetorno;
    }
}
