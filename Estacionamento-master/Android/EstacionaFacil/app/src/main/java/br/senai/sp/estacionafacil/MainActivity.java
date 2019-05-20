package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.tasks.CarregarListaMovimentos;
import br.senai.sp.estacionafacil.tasks.SaidaMovimento;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnEnviarCadastro;
    public static ListView listaEstacionamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sincronizando valiaveis
        btnEnviarCadastro = findViewById(R.id.botao_cadastrar);
        listaEstacionamento = findViewById(R.id.lista_estacionamento_inicial);


        //Criando intenção para o cadastro
        btnEnviarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(abrirCadastro);
            }
        });

//        Fazerndo a saida no adapter
//         listaEstacionamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Movimentacao movimento = (Movimentacao) listaEstacionamento.getItemAtPosition(position);
//                SaidaMovimento saidaMovimento = new SaidaMovimento(movimento, MainActivity.this);
//                saidaMovimento.execute();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CarregarListaMovimentos carregarLista = new CarregarListaMovimentos(this);
        carregarLista.execute();
    }


}
