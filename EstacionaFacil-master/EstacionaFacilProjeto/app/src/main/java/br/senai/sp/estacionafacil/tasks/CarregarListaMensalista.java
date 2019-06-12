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

import br.senai.sp.estacionafacil.MainActivity;
import br.senai.sp.estacionafacil.MensalistasActivity;
import br.senai.sp.estacionafacil.PagamentoActivity;
import br.senai.sp.estacionafacil.R;
import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.modelo.Pagamento;

public class CarregarListaMensalista extends AsyncTask {

    private String dados = "";
    private List<Mensalista> mensalistas;
    private ProgressDialog progressDialog;
    private MensalistasActivity mensalistaActivity;
    private PagamentoActivity pagamentoActivity;
    private ArrayAdapter<Mensalista> adapterLista;


    public CarregarListaMensalista(MensalistasActivity mensalistaActivity) {
        this.mensalistaActivity = mensalistaActivity;
    }
    public CarregarListaMensalista(PagamentoActivity pagamentoActivity) {
        this.pagamentoActivity = pagamentoActivity;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/mensalistas");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream dadosStream = conexao.getInputStream();
            InputStreamReader leitorStream = new InputStreamReader(dadosStream);
            BufferedReader bufferedReader = new BufferedReader(leitorStream);

            String registro = "";

            while(registro != null){
                registro = bufferedReader.readLine();
                dados += registro;
            }

            Mensalista mensalista;
            mensalistas = new ArrayList<>();

            try {
                JSONArray dadosArray = new JSONArray(dados);

                for(int cont = 0; cont < dadosArray.length(); cont++){
                    JSONObject jsonMensalista = (JSONObject) dadosArray.get(cont);

                    mensalista = new Mensalista();
                    mensalista.setCodMensalista(jsonMensalista.getInt("codMensalista"));
                    mensalista.setNome(jsonMensalista.getString("nome"));
                    mensalista.setCpf(jsonMensalista.getString("cpf"));
                    mensalista.setEmail(jsonMensalista.getString("email"));
                    mensalistas.add(mensalista);
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
        if (mensalistaActivity != null){
            progressDialog = new ProgressDialog(mensalistaActivity);
        }else if(pagamentoActivity != null){
            progressDialog = new ProgressDialog(pagamentoActivity);
        }
        progressDialog.setTitle("Em Processo...");
        progressDialog.setMessage("Seu dados estão sendo carregados. Espere um minuto.");
        progressDialog.show();
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if(mensalistaActivity != null){
            adapterLista = new ArrayAdapter<>(mensalistaActivity, android.R.layout.simple_list_item_1, mensalistas);
            MensalistasActivity.visualizarMensalista.setAdapter(adapterLista);
        }else if(pagamentoActivity != null){
            adapterLista = new ArrayAdapter<Mensalista>(pagamentoActivity, android.R.layout.simple_spinner_item, mensalistas);
            adapterLista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            PagamentoActivity.spnMensalistas.setAdapter(adapterLista);
        }

        progressDialog.dismiss();
    }


}
