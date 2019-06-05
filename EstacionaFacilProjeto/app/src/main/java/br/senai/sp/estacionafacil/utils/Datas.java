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

            e.printStackTrace();
        }
        return stringData;
    }


    public String formatarComSubString(String data) {

        String ano = data.substring(0,4);
        String dia = data.substring(8,10);
        String mes = data.substring(5,7);
        String hora = data.substring(11,16);

        return dia + "/"+ mes +"/" + ano + " " + hora;
    }
}
