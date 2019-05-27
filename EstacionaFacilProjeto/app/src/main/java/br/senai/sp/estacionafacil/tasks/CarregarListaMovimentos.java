package br.senai.sp.estacionafacil.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.estacionafacil.EstacionadosActivity;
import br.senai.sp.estacionafacil.adapter.MovimentacaoListAdapter;
import br.senai.sp.estacionafacil.modelo.Movimentacao;

public class CarregarListaMovimentos extends AsyncTask {

    private String dados = "";
    private List<Movimentacao> movimentos;
    private ProgressDialog progressDialog;
    private ArrayAdapter<Movimentacao> adapter;
    private EstacionadosActivity estacionadosActivity;
//    private Context context;

    public CarregarListaMovimentos(EstacionadosActivity estacionadosActivity) {
        this.estacionadosActivity = estacionadosActivity;
//        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            //URL url = new URL("http://"+R.string.ip_server+":8080/movimentacoes/estacionados");
            URL url = new URL("http://10.107.134.8:8080/movimentacoes/estacionados");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream dadosStream = conexao.getInputStream();
            InputStreamReader leitorStream = new InputStreamReader(dadosStream);
            BufferedReader bufferedReader = new BufferedReader(leitorStream);

            String registro = "";

            while(registro != null){
                registro = bufferedReader.readLine();
                dados += registro;
            }

            Movimentacao movimento;
            movimentos = new ArrayList<>();

            try {
                JSONArray dadosArray = new JSONArray(dados);

                for(int cont = 0; cont < dadosArray.length(); cont++){
                    JSONObject jsonMovimentos = (JSONObject) dadosArray.get(cont);

                    movimento = new Movimentacao();
                    movimento.setCodMovimento(jsonMovimentos.getInt("codMovimento"));
                    movimento.setPlaca(jsonMovimentos.getString("placa"));
                    movimento.setModeloCarro(jsonMovimentos.getString("modeloCarro"));
                    movimento.setDataHoraEntrada(jsonMovimentos.getString("dataHoraEntrada"));
                    //como o veiculo está estacionado nao nessecita trazer tudos pois estão nulos
//                    movimento.setDataHoraSaida(jsonMovimentos.getString("dataHoraSaida"));
//                    movimento.setTempoPermanecia(jsonMovimentos.getInt("tempoPermanencia"));
//                    movimento.setValorPago(jsonMovimentos.getDouble("valorPago"));
                    movimento.setTipo(jsonMovimentos.getString("tipo"));
                    movimentos.add(movimento);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(estacionadosActivity);
        progressDialog.setTitle("Em Processo...");
        progressDialog.setMessage("Seu dados estão sendo carregados. Espere um minuto.");
        progressDialog.show();
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        MovimentacaoListAdapter movimentacaoListAdapter = new MovimentacaoListAdapter(estacionadosActivity, movimentos);
        EstacionadosActivity.listaEstacionamento.setAdapter(movimentacaoListAdapter);
        progressDialog.dismiss();
    }
}
