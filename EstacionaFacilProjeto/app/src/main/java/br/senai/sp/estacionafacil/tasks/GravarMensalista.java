package br.senai.sp.estacionafacil.tasks;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.utils.CriarJsons;

public class GravarMensalista extends AsyncTask {

    private Mensalista mensalista;

    public GravarMensalista(Mensalista mensalista) {
        this.mensalista = mensalista;
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        CriarJsons jsonMensalista = new CriarJsons();

        jsonMensalista.criarJsonMensalista(mensalista);

        try {
            URL url = new URL("http://192.168.15.8:8080/mensalista");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsonMensalista);
            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
