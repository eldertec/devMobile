package br.com.eldertec.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.eldertec.agenda.R;
import br.com.eldertec.agenda.dao.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();

        AlunoDAO dao = new AlunoDAO(context);
        if (dao.isAluno(telefone)) {
            Toast.makeText(context, "Chegou um SMS de um Aluno", Toast.LENGTH_SHORT).show();
            MediaPlayer soundSms = MediaPlayer.create(context, R.raw.msg);
            soundSms.start();
        }
        dao.close();
    }
}
