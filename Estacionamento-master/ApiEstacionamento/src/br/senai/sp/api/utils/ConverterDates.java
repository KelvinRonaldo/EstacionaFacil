package br.senai.sp.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterDates {

	
	public String dataAtualString() {

		DateFormat dateToString = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		Date dataAtual = new Date();
		String dataFormatada = dateToString.format(dataAtual);

		return dataFormatada;
	}
	
	public Date stringToDate(String data) {
		Date novaData = null;
		try {
			SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
			novaData = stringToDate.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return novaData;
	}
	
}
