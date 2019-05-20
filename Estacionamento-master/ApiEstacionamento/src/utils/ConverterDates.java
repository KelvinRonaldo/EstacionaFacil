package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterDates {

	
	public String dataAtualString() {

		DateFormat dateToString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ConverterDates dataAtual = new ConverterDates();
		String dataFormatada = dateToString.format(dataAtual);

		return dataFormatada;
	}
	
	public Date stringToDate(String data) {
		Date novaData = null;
		try {
			SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			novaData = stringToDate.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return novaData;
	}
	
}
