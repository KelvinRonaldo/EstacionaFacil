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

import br.senai.sp.estacionafacil.modelo.Movimentacao;

public class AtualizarMovimento extends AsyncTask {

    private Movimentacao movimento;
    private String resposta = "";

    public AtualizarMovimento(Movimentacao movimento) {
        this.movimento = movimento;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsonContato = new JSONStringer();

        try {
            jsonContato.object();
            jsonContato.key("codMovimento").value(movimento.getCodMovimento());
            jsonContato.key("placa").value(movimento.getPlaca());
            jsonContato.key("modeloCarro").value(movimento.getModeloCarro());
            jsonContato.key("dataHoraEntrada").value(movimento.getDataHoraEntrada());
            jsonContato.key("dataHoraSaida").value(movimento.getDataHoraSaida());
            jsonContato.key("tempoPermanencia").value(movimento.getTempoPermanecia());
            jsonContato.key("valorPago").value(movimento.getValorPago());
            jsonContato.key("tipo").value(movimento.getTipo());
            jsonContato.endObject();

            URL url = new URL("http://192.168.15.8:8080/movimentacoes/saida/"+movimento.getCodMovimento());
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("PUT");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsonContato);
            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            resposta = scanner.nextLine();

            return null;

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
