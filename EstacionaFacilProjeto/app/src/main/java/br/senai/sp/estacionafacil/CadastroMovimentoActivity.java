package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.tasks.AtualizarMovimento;
import br.senai.sp.estacionafacil.tasks.BuscarVeiculoMensalista;
import br.senai.sp.estacionafacil.tasks.GravarMovimento;

public class CadastroMovimentoActivity extends AppCompatActivity {
    private EditText txtPlaca;
    private EditText txtModelo;
    private Button btnGravar;
    private Movimentacao movimento;

    public static String placa;

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
        }else{
            txtPlaca.setText(movimento.getPlaca().toString());
            txtModelo.setText(movimento.getModeloCarro().toString());
        }

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movimento.setPlaca(txtPlaca.getText().toString());
                movimento.setModeloCarro(txtModelo.getText().toString());

                BuscarVeiculoMensalista buscaVeiculo = new BuscarVeiculoMensalista(movimento.getPlaca(), CadastroMovimentoActivity.this);
                buscaVeiculo.execute();

                try {
                    placa = (String) buscaVeiculo.get();

                    if(placa != null){
                        movimento.setTipo("Mensalista");
                    }else{
                        movimento.setTipo("Avulso");
                    }

                    if(movimento.getCodMovimento() == 0){
                        GravarMovimento gravarMovimento = new GravarMovimento(movimento);
                        gravarMovimento.execute();
                        finish();
                    }else{
//                        Toast.makeText(CadastroMovimentoActivity.this, movimento.getPlaca(), Toast.LENGTH_LONG).show();
                        AtualizarMovimento atualizarMovimento = new AtualizarMovimento(movimento, CadastroMovimentoActivity.this);
                        atualizarMovimento.execute();
                        finish();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });






    }
}
