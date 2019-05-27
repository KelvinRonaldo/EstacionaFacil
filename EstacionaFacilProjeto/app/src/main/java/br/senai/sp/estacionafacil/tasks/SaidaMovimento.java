package br.senai.sp.estacionafacil.tasks;

import android.content.Intent;
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
import java.net.ProtocolException;
import java.net.URL;

import br.senai.sp.estacionafacil.EstacionadosActivity;
import br.senai.sp.estacionafacil.VisualizarActivity;
import br.senai.sp.estacionafacil.modelo.Movimentacao;

public class SaidaMovimento extends AsyncTask {

    private Movimentacao movimento;
    private String resposta = "";
    private EstacionadosActivity estacionadosActivity;

    public SaidaMovimento(Movimentacao movimento, EstacionadosActivity estacionadosActivity) {
        this.movimento = movimento;
        this.estacionadosActivity = estacionadosActivity;
    }

    @Override
    protected Object doInBackground(Object[] objects) {


        try {
            URL url = new URL("http://10.107.134.8:8080/movimentacoes/saida/"+movimento.getCodMovimento());
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            InputStream dadosStream = conexao.getInputStream();
            InputStreamReader leitorStream = new InputStreamReader(dadosStream);
            BufferedReader bufferedReader = new BufferedReader(leitorStream);

            String registro = "";

            if(registro != null){
                registro = bufferedReader.readLine();
                resposta += registro;
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        Log.d("RESPOSTA", resposta);

        try {
            JSONObject jsonMovimento = new JSONObject(resposta);
            movimento.setCodMovimento(jsonMovimento.getInt("codMovimento"));
            movimento.setPlaca(jsonMovimento.getString("placa"));
            movimento.setModeloCarro(jsonMovimento.getString("modeloCarro"));
            movimento.setDataHoraEntrada(jsonMovimento.getString("dataHoraEntrada"));
            movimento.setDataHoraSaida(jsonMovimento.getString("dataHoraSaida"));
            movimento.setTempoPermanecia(jsonMovimento.getInt("tempoPermanencia"));
            movimento.setValorPago(jsonMovimento.getDouble("valorPago"));
            movimento.setTipo(jsonMovimento.getString("tipo"));

            Intent viewSaidaMovimento = new Intent(estacionadosActivity, VisualizarActivity.class);
            viewSaidaMovimento.putExtra("movimento", movimento);
            estacionadosActivity.startActivity(viewSaidaMovimento);
            
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
