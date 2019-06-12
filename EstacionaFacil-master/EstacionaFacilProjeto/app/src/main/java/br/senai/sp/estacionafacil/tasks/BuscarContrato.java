package br.senai.sp.estacionafacil.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.senai.sp.estacionafacil.MainActivity;
import br.senai.sp.estacionafacil.PagamentoActivity;
import br.senai.sp.estacionafacil.modelo.Contrato;
import br.senai.sp.estacionafacil.modelo.Mensalista;

public class BuscarContrato extends AsyncTask {

    private Contrato contrato;
    private Mensalista mensalista;
    private String dados;

    public BuscarContrato(Mensalista mensalista) {
        this.mensalista = mensalista;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/contratos/mensalista/"+mensalista.getCodMensalista());
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream dadosStream = conexao.getInputStream();
            InputStreamReader leitorStream = new InputStreamReader(dadosStream);
            BufferedReader bufferedReader = new BufferedReader(leitorStream);

            String registro = "";

            if(registro != null){
                registro = bufferedReader.readLine();
                dados = registro;
            }

            JSONObject jsonContrato = new JSONObject(dados);
            contrato = new Contrato();
            contrato.setValorMensalista(jsonContrato.getDouble("valorMensalista"));
            PagamentoActivity.contrato = contrato;
            Log.d("TESTE", contrato.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
