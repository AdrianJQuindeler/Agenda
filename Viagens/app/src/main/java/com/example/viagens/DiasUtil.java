package com.example.viagens;

import androidx.annotation.NonNull;

public class DiasUtil {
    @NonNull
    public static String formataEmTexto(int quantidadeDias) {;
        String diasEmTexto = quantidadeDias + " Dias";
        if (quantidadeDias == 1){ diasEmTexto = quantidadeDias + " Dia"; }
        return diasEmTexto;
    }
}
