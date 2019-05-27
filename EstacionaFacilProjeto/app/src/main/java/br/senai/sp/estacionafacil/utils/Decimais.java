package br.senai.sp.estacionafacil.utils;

import java.text.DecimalFormat;

public class Decimais {

    public static String generateDecimal(Double numero){

        DecimalFormat df = new DecimalFormat();
        df.applyPattern("R$ #,##0.00");

        String novoNumero = df.format(numero);

        return novoNumero;
    }
}
