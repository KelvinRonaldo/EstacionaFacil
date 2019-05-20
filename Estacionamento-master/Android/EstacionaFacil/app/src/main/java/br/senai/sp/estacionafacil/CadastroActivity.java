package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.tasks.GravarMovimento;

public class CadastroActivity extends AppCompatActivity {
    private EditText txtPlaca;
    private EditText txtModelo;
    private Button btnGravar;
    private Movimentacao movimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Sincronizando variaveis
        txtPlaca = findViewById(R.id.txt_placa);
        txtModelo = findViewById(R.id.txt_modelo);
        btnGravar = findViewById(R.id.gravar_carro);

        Intent pegarMovimento = getIntent();
        movimento = (Movimentacao) pegarMovimento.getSerializableExtra("movimento");

        if(movimento == null){
            movimento = new Movimentacao();
        }

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movimento.setPlaca(txtPlaca.getText().toString());
                movimento.setModeloCarro(txtModelo.getText().toString());

                if(movimento.getCodMovimento() == 0){
                    GravarMovimento gravarMovimento = new GravarMovimento(movimento);
                    gravarMovimento.execute();
                    finish();
                }




            }
        });






    }
}
