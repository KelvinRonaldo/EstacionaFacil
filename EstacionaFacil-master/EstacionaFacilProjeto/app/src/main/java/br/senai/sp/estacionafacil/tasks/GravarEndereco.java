package br.senai.sp.estacionafacil.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.estacionafacil.MainActivity;
import br.senai.sp.estacionafacil.modelo.Endereco;
import br.senai.sp.estacionafacil.utils.CriarJsons;

public class GravarEndereco extends AsyncTask {

    private Endereco endereco;
    private String dados;

    public GravarEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        CriarJsons jsonEndereco = new CriarJsons();

        JSONStringer stringerEndereco = jsonEndereco.criarJsonEndereco(endereco);

        try {
            URL url = new URL("http://" + MainActivity.ipServidor + ":8080/enderecos");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(stringerEndereco);
            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

            Log.d("Resposta", resposta);
            JSONObject retornoEndereco = new JSONObject(resposta);
            endereco = new Endereco();
            endereco.setCodEndereco(retornoEndereco.getInt("codEndereco"));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return endereco;
    }
}
