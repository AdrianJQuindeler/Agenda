package com.example.viagens.util;

import androidx.annotation.NonNull;
import com.example.viagens.ui.model.Pacote;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {

    @NonNull
    public static String periodoEmTexto(Pacote pacote) {
        Calendar dataIda = Calendar.getInstance();
        Calendar dataVolta = Calendar.getInstance();
        dataVolta.add(Calendar.DATE, pacote.getDias());
        SimpleDateFormat sdfBrasil = new SimpleDateFormat("dd/MM");
        String dataIdaFormatada = sdfBrasil.format(dataIda.getTime());
        String dataVoltaFormatada = sdfBrasil.format(dataVolta.getTime());
        String dataFormatadaViagem = dataIdaFormatada + " - " + dataVoltaFormatada + " de " + dataVolta.get(Calendar.YEAR);
        return dataFormatadaViagem;
    }
}
