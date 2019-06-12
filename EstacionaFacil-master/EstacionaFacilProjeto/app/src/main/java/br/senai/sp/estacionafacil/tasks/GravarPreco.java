package br.senai.sp.estacionafacil.tasks;

import android.os.AsyncTask;

import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.estacionafacil.MainActivity;
import br.senai.sp.estacionafacil.modelo.Preco;
import br.senai.sp.estacionafacil.utils.CriarJsons;

public class GravarPreco extends AsyncTask{

    private Preco preco;

    public GravarPreco(Preco preco) {
        this.preco = preco;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        CriarJsons jsonPreco = new CriarJsons();
        JSONStringer stringerPreco;

        stringerPreco = jsonPreco.criarJsonPrecos(preco);

        try {
            URL url = new URL("http://"+MainActivity.ipServidor+":8080/precos/");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(stringerPreco);
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
