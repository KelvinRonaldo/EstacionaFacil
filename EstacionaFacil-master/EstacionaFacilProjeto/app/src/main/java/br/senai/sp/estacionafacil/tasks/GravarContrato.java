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
import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.utils.CriarJsons;

public class GravarContrato extends AsyncTask{

    private Contrato contrato;
    private Mensalista mensalista;

    public GravarContrato(Contrato contrato, Mensalista mensalista) {
        this.contrato = contrato;
        this.mensalista = mensalista;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        CriarJsons jsonContrato = new CriarJsons();
        JSONStringer stringerContrato;

        stringerContrato = jsonContrato.criarJsonContrato(contrato, mensalista);

        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/contratos");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(stringerContrato);
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
