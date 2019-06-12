package br.senai.sp.estacionafacil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.senai.sp.estacionafacil.modelo.Preco;
import br.senai.sp.estacionafacil.tasks.GravarPreco;

public class CadastroPrecosActivity extends AppCompatActivity {

    private EditText txtPrecoPrimeiraHora;
    private EditText txtPrecoDemaisHoras;
    private EditText txtPrecoMensal;
    private EditText txtTempoTolerencia;
    private Button btnEnviarPreco;
    private Preco preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_precos);

        txtPrecoPrimeiraHora = findViewById(R.id.txt_preco_primeira_hora);
        txtPrecoDemaisHoras = findViewById(R.id.txt_preco_demais_horas);
        txtPrecoMensal = findViewById(R.id.txt_preco_mensal);
        txtTempoTolerencia = findViewById(R.id.txt_tempo_tolerancia);
        btnEnviarPreco = findViewById(R.id.btn_cadastro_preco);

        preco = new Preco();

        btnEnviarPreco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double primeiraHora = Double.parseDouble(txtPrecoPrimeiraHora.getText().toString());
                Double demaisHoras = Double.parseDouble(txtPrecoDemaisHoras.getText().toString());
                Double valorMensal = Double.parseDouble(txtPrecoMensal.getText().toString());
                Integer tempoTolerancia = Integer.parseInt(txtTempoTolerencia.getText().toString());

                preco.setValorPrimeiraHora(primeiraHora);
                preco.setValorDemaisHoras(demaisHoras);
                preco.setValorMensal(valorMensal);
                preco.setTempoTolerancia(tempoTolerancia);

                GravarPreco gravarPreco = new GravarPreco(preco);
                gravarPreco.execute();
                finish();
            }
        });

    }
}
