package br.senai.sp.estacionafacil.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datas {

    public String dataToBrString(Date data) {

        DateFormat dateToString = new SimpleDateFormat("dd/MM/yy HH:mm");
        String dataFormatada = dateToString.format(data);

        return dataFormatada;
    }

    public Date stringToDate(String data) {
        Date stringData = null;
        try {
            SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
            stringData = stringToDate.parse(data);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stringData;
    }
}
