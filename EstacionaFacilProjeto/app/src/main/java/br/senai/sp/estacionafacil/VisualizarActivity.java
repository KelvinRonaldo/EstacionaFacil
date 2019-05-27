package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Date;

import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.tasks.AtualizarMovimento;
import br.senai.sp.estacionafacil.tasks.SaidaMovimento;
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

        if(movimento == null){
            Toast.makeText(VisualizarActivity.this, "NULO", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(VisualizarActivity.this, "segundo " + movimento.getPlaca(), Toast.LENGTH_SHORT).show();
            Log.d("VALOR", movimento.getValorPago().toString());

            Datas dataString = new Datas();
            Datas dataBr = new Datas();
            Date data;

            data = dataString.stringToDate(movimento.getDataHoraEntrada());
            movimento.setDataHoraEntrada(dataBr.dataToBrString(data));
            data = dataString.stringToDate(movimento.getDataHoraSaida());
            movimento.setDataHoraSaida(dataBr.dataToBrString(data));
//
//            Integer tempoMinutos = movimento.getTempoPermanecia();
//            Integer tempoHoras = tempoMinutos60;
//            Integer tempoDias = tempoMinutos/24;
//            String permanencia = tempoDias+" dias "+tempoHoras+" horas "+tempoMinutos+" minutos";


            txtPlaca.setText(movimento.getPlaca());
            txtModeloCarro.setText(movimento.getModeloCarro());
            txtDataHoraEntrada.setText(movimento.getDataHoraEntrada());
            txtDataHoraSaida.setText(movimento.getDataHoraSaida());
//            txtTempoPermanencia.setText(permanencia);
            txtValorPago.setText(Decimais.generateDecimal(movimento.getValorPago()));

            btnSaida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                AtualizarMovimento atualizarMovimento = new AtualizarMovimento(movimento);
                atualizarMovimento.execute();
                finish();
                }
            });
        }
    }
}
