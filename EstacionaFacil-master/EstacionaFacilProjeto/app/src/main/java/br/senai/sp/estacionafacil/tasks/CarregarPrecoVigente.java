package br.senai.sp.estacionafacil.tasks;

import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;

import br.senai.sp.estacionafacil.MainActivity;
import br.senai.sp.estacionafacil.modelo.Preco;

public class CarregarPrecoVigente extends AsyncTask {

    private Preco preco;

    public CarregarPrecoVigente(Preco preco) {
        this.preco = preco;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/precos/precoVigente/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
