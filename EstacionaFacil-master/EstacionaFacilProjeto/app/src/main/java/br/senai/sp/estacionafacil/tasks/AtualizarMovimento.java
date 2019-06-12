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

import br.senai.sp.estacionafacil.CadastroMovimentoActivity;
import br.senai.sp.estacionafacil.MainActivity;
import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.utils.CriarJsons;

public class AtualizarMovimento extends AsyncTask {
    private Movimentacao movimento;
    private CadastroMovimentoActivity cadastroMovimentoActivity;

    public AtualizarMovimento(Movimentacao movimento, CadastroMovimentoActivity cadastroMovimentoActivity) {
        this.movimento = movimento;
        this.cadastroMovimentoActivity = cadastroMovimentoActivity;
    }

    @Override
    protected Object doInBackground(Object[] objects) {


        CriarJsons jsonMovimento = new CriarJsons();
        JSONStringer stringerMovimento;

        try {
            stringerMovimento = jsonMovimento.criarJsonSaidaMovimento(movimento, "atualizar");

            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/movimentacoes/"+movimento.getCodMovimento());
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("PUT");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(stringerMovimento);
            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

            Log.d("------JSON-------", String.valueOf(stringerMovimento));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
