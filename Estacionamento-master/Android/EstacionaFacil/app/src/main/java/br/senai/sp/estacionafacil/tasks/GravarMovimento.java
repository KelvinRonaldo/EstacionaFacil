package br.senai.sp.estacionafacil.tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.estacionafacil.R;
import br.senai.sp.estacionafacil.modelo.Movimentacao;

public class GravarMovimento extends AsyncTask {

    private Movimentacao movimento;

    public GravarMovimento(Movimentacao movimento) {
        this.movimento = movimento;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsonContato = new JSONStringer();

        try {
            jsonContato.object();
            jsonContato.key("placa").value(movimento.getPlaca());
            jsonContato.key("modeloCarro").value(movimento.getModeloCarro());
            jsonContato.key("tipo").value("A");
            jsonContato.key("tempoPermanencia").value(0);
            jsonContato.key("valorPago").value(0);
            jsonContato.endObject();

            URL url = new URL("http://10.107.134.8:8080/movimentacoes");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsonContato);
            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
