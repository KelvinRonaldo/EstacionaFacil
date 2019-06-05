package br.senai.sp.estacionafacil.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.senai.sp.estacionafacil.CadastroMovimentoActivity;
import br.senai.sp.estacionafacil.MainActivity;

public class BuscarVeiculoMensalista extends AsyncTask {

    private String placa;
    private String dados;
    private CadastroMovimentoActivity activity;

    public BuscarVeiculoMensalista(String placa, CadastroMovimentoActivity activity) {
        this.placa = placa;
        this.activity = activity;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/veiculo/cadastrado/"+placa);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream dadosStream = conexao.getInputStream();
            InputStreamReader leitorStream = new InputStreamReader(dadosStream);
            BufferedReader bufferedReader = new BufferedReader(leitorStream);

            String registro = "";

            if (registro != null) {
                registro = bufferedReader.readLine();
                dados = registro;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dados;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
//        Toast.makeText(activity, dados, Toast.LENGTH_LONG).show();
//        if(dados != null){
//            CadastroMovimentoActivity.placa = dados;
//        }else{
//            CadastroMovimentoActivity.placa = null;
//        }

    }
}
