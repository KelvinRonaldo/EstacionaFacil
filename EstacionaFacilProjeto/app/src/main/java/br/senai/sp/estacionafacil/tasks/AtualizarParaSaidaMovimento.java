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
import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.utils.CriarJsons;

public class AtualizarParaSaidaMovimento extends AsyncTask {

    private Movimentacao movimento;
    private String resposta = "";

    public AtualizarParaSaidaMovimento(Movimentacao movimento) {
        this.movimento = movimento;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        CriarJsons jsonMovimento = new CriarJsons();

        JSONStringer stringerMovimento;

        try {
            stringerMovimento = jsonMovimento.criarJsonSaidaMovimento(movimento);
            URL url;

            if(movimento.getTipo().equals("Mensalista")){
                url = new URL("http://"+ MainActivity.ipServidor+":8080/movimentacoes/saida/mensalista/"+movimento.getCodMovimento());
            }else{
                url = new URL("http://"+ MainActivity.ipServidor+":8080/movimentacoes/saida/"+movimento.getCodMovimento());
            }

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("PUT");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(stringerMovimento);
            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            resposta = scanner.nextLine();

            return null;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
