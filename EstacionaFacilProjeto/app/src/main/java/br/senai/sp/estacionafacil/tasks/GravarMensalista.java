package br.senai.sp.estacionafacil.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.estacionafacil.MainActivity;
import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.utils.CriarJsons;

public class GravarMensalista extends AsyncTask {

    private Mensalista mensalista;
    public String dados;

    public GravarMensalista(Mensalista mensalista) {
        this.mensalista = mensalista;
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        CriarJsons jsonMensalista = new CriarJsons();

        JSONStringer stringerMensalista = jsonMensalista.criarJsonMensalista(mensalista);

        try {
            URL url = new URL("http://" + MainActivity.ipServidor + ":8080/mensalista");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(stringerMensalista);
            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

            Log.d("Resposta", resposta);

            dados = resposta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dados;
    }
}
