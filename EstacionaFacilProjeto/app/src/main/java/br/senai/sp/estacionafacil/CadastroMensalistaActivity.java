package br.senai.sp.estacionafacil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.senai.sp.estacionafacil.modelo.Endereco;
import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.modelo.Telefone;
import br.senai.sp.estacionafacil.tasks.GravarMensalista;

public class CadastroMensalistaActivity extends AppCompatActivity {

    private EditText txtNomeMensalista;
    private EditText txtEmailMensalista;
    private EditText txtCpfMensalista;

    private EditText txtTelefoneMensalista;

    private EditText txtLogradouroMensalista;
    private EditText txtNumeroMensalista;
    private EditText txtBairroMensalista;
    private EditText txtCepMensalista;
    private EditText txtCidadeMensalista;
    private EditText txtEstadoMensalista;
    private Button btnCadastroMensalista;

    private Mensalista mensalista;
    private Telefone telefone;
    private Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mensalista);

        txtNomeMensalista = findViewById(R.id.txt_mensalista_nome);
        txtEmailMensalista = findViewById(R.id.txt_mensalista_email);
        txtCpfMensalista = findViewById(R.id.txt_mensalista_cpf);

        txtTelefoneMensalista = findViewById(R.id.txt_mensalista_telefone);

        txtLogradouroMensalista = findViewById(R.id.txt_mensalista_logradouro);
        txtNumeroMensalista = findViewById(R.id.txt_mensalista_numero);
        txtBairroMensalista = findViewById(R.id.txt_mensalista_bairro);
        txtCepMensalista = findViewById(R.id.txt_mensalista_cep);
        txtCidadeMensalista = findViewById(R.id.txt_mensalista_cidade);
        txtEstadoMensalista = findViewById(R.id.txt_mensalista_estado);
        btnCadastroMensalista = findViewById(R.id.btn_cadastro_mensalista);

        if(mensalista == null){
            mensalista = new Mensalista();
        }

        if(telefone == null){
            telefone = new Telefone();
        }

        if(endereco == null){
            endereco = new Endereco();
        }

        btnCadastroMensalista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensalista.setNome(txtNomeMensalista.getText().toString());
                mensalista.setEmail(txtEmailMensalista.getText().toString());
                mensalista.setCpf(txtCpfMensalista.getText().toString());

                if(mensalista.getCodMensalista() == 0){
                    GravarMensalista gravarMensalista = new GravarMensalista(mensalista);
                    gravarMensalista.execute();
                    finish();
                }

//                telefone.setTelefone(txtTelefoneMensalista.getText().toString());
//
//                endereco.setLogradouro(txtLogradouroMensalista.getText().toString());
//                endereco.setNumero(txtNumeroMensalista.getText().toString());
//                endereco.setBairro(txtBairroMensalista.getText().toString());
//                endereco.setCep(txtCepMensalista.getText().toString());
//                endereco.setCidade(txtCidadeMensalista.getText().toString());
//                endereco.setEstado(txtEstadoMensalista.getText().toString());
            }
        });


    }
}
