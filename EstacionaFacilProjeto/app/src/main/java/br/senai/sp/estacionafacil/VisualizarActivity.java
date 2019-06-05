package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.tasks.AtualizarParaSaidaMovimento;
import br.senai.sp.estacionafacil.utils.Datas;
import br.senai.sp.estacionafacil.utils.Decimais;

public class VisualizarActivity extends AppCompatActivity {

    private TextView txtPlaca;
    private TextView txtModeloCarro;
    private TextView txtDataHoraEntrada;
    private TextView txtDataHoraSaida;
    private TextView txtTempoPermanencia;
    private TextView txtValorPago;
    private Button btnSaida;
    private Movimentacao movimento;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        txtPlaca = findViewById(R.id.txt_placa_saida);
        txtModeloCarro = findViewById(R.id.txt_modelo_saida);
        txtDataHoraEntrada = findViewById(R.id.txt_data_hora_entrada);
        txtDataHoraSaida = findViewById(R.id.txt_data_hora_saida);
        txtTempoPermanencia = findViewById(R.id.txt_tempo_permanencia);
        txtValorPago = findViewById(R.id.txt_valor_pago);
        btnSaida = findViewById(R.id.btn_saida);

        Intent pegarMovimento = getIntent();
        movimento = (Movimentacao) pegarMovimento.getSerializableExtra("movimento");

        if(movimento != null){

            Datas transformData = new Datas();
            String dataEntrada;
            String dataSaida;
            Date dateEntrada;
            Date dateSaida;


            Toast.makeText(this, movimento.getDataHoraEntrada(), Toast.LENGTH_SHORT).show();

            /*PEGANDO STRING EM FORMATO DE DATA DO MOVIMENTO DE TRANSFORMANDO
            EM UM OBJETO DATE();↓↓↓↓*/
            dateEntrada = transformData.stringToDate(movimento.getDataHoraEntrada());
            /*↓↓↓↓PEGANDO DATE GERADO E MUDANDO SEU FORMATO PARA O PADRÃO PT-BR*/
            dataEntrada = transformData.dataToBrString(dateEntrada);

            dateSaida = transformData.stringToDate(movimento.getDataHoraSaida());
            dataSaida = transformData.dataToBrString(dateSaida);

            Integer iniMinutos = movimento.getTempoPermanecia();
            Double minutos = (double) iniMinutos;
            Double dias = (minutos/60)/24;
            Double horas = (dias % 1)*24;
            minutos = (horas % 1)*60;

            String numDias = Math.floor(dias)+" dias";
            String numHoras = Math.floor(horas)+" horas";
            String numMinutos = Math.floor(minutos)+" minutos";
            String permanencia = numDias+" "+numHoras+" "+numMinutos;

            txtPlaca.setText(movimento.getPlaca());
            txtModeloCarro.setText(movimento.getModeloCarro());
            txtDataHoraEntrada.setText(dataEntrada);
            txtDataHoraSaida.setText(dataSaida);
            txtTempoPermanencia.setText(permanencia);

            switch (movimento.getTipo().toString()){
                case "Avulso":
                    txtValorPago.setText(Decimais.generateDecimal(movimento.getValorPago()));
                    break;
                case "Mensalista":
                    txtValorPago.setText("Mensalista");
                    break;
            }

            btnSaida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AtualizarParaSaidaMovimento atualizarParaSaidaMovimento = new AtualizarParaSaidaMovimento(movimento);
                    atualizarParaSaidaMovimento.execute();
                    finish();
                }
            });
        }
    }
}
