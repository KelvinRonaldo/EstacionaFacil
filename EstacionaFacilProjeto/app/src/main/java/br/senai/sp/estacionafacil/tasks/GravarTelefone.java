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
import br.senai.sp.estacionafacil.modelo.Telefone;
import br.senai.sp.estacionafacil.utils.CriarJsons;

public class GravarTelefone extends AsyncTask {

    private Telefone telefone;
    private String dados;

    public GravarTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        CriarJsons jsonTelefone = new CriarJsons();

        JSONStringer stringerTelefone = jsonTelefone.criarJsonTelefone(telefone);

        try {
            URL url = new URL("http://" + MainActivity.ipServidor + ":8080/telefones");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(stringerTelefone);
            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

            Log.d("Resposta", resposta);
            JSONObject retornoTelefone = new JSONObject(resposta);
            telefone = new Telefone();
            telefone.setCodTelefone(retornoTelefone.getInt("codTelefone"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return telefone;
    }
}
