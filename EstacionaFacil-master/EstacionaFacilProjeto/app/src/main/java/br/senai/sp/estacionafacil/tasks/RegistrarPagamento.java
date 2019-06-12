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
import br.senai.sp.estacionafacil.modelo.Contrato;
import br.senai.sp.estacionafacil.modelo.Pagamento;
import br.senai.sp.estacionafacil.utils.CriarJsons;

public class RegistrarPagamento extends AsyncTask {

    private Pagamento pagamento;
    private Contrato contrato;

    public RegistrarPagamento(Pagamento pagamento, Contrato contrato) {
        this.pagamento = pagamento;
        this.contrato = contrato;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        CriarJsons jsonPagamento = new CriarJsons();
        JSONStringer stringerPagemanto;

        stringerPagemanto = jsonPagamento.criarJsonPagamento(pagamento, contrato);

        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/pagamentos");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(stringerPagemanto);
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
