package br.senai.sp.estacionafacil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.concurrent.ExecutionException;

import br.senai.sp.estacionafacil.modelo.Contrato;
import br.senai.sp.estacionafacil.modelo.Endereco;
import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.modelo.Telefone;
import br.senai.sp.estacionafacil.tasks.GravarContrato;
import br.senai.sp.estacionafacil.tasks.GravarEndereco;
import br.senai.sp.estacionafacil.tasks.GravarMensalista;
import br.senai.sp.estacionafacil.tasks.GravarMensalistaEndereco;
import br.senai.sp.estacionafacil.tasks.GravarMensalistaTelefone;
import br.senai.sp.estacionafacil.tasks.GravarTelefone;

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
    private EditText txtQtdVagasMensalista;
    private EditText txtDiaPgtoMensalista;
    private Button btnCadastroMensalista;

    private Mensalista mensalista;
    private Telefone telefone;
    private Endereco endereco;
    private Contrato contrato;


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
        txtQtdVagasMensalista = findViewById(R.id.txt_mensalista_vagas);
        txtDiaPgtoMensalista = findViewById(R.id.txt_mensalista_dia_pagamento);

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

        if(contrato == null){
            contrato = new Contrato();
        }

        btnCadastroMensalista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensalista.setNome(txtNomeMensalista.getText().toString());
                mensalista.setEmail(txtEmailMensalista.getText().toString());
                mensalista.setCpf(txtCpfMensalista.getText().toString());
                telefone.setTelefone(txtTelefoneMensalista.getText().toString());
                endereco.setLogradouro(txtLogradouroMensalista.getText().toString());
                endereco.setNumero(txtNumeroMensalista.getText().toString());
                endereco.setBairro(txtBairroMensalista.getText().toString());
                endereco.setCep(txtCepMensalista.getText().toString());
                endereco.setCidade(txtCidadeMensalista.getText().toString());
                endereco.setEstado(txtEstadoMensalista.getText().toString());

                contrato.setQuantidadeVagas(Integer.valueOf(txtQtdVagasMensalista.getText().toString()));
                contrato.setDiaPagar(Integer.valueOf(txtDiaPgtoMensalista.getText().toString()));

                try {
                    if (mensalista.getCodMensalista() == 0) {
                        GravarMensalista gravarMensalista = new GravarMensalista(mensalista);
                        gravarMensalista.execute();

                        mensalista = (Mensalista) gravarMensalista.get();

                        GravarEndereco gravarEndereco = new GravarEndereco(endereco);
                        gravarEndereco.execute();
                        endereco = (Endereco) gravarEndereco.get();

                        GravarTelefone gravarTelefone = new GravarTelefone(telefone);
                        gravarTelefone.execute();
                        telefone = (Telefone) gravarTelefone.get();

                        GravarMensalistaEndereco gravarMensalistaEndereco = new GravarMensalistaEndereco(mensalista, endereco);
                        gravarMensalistaEndereco.execute();

                        GravarMensalistaTelefone gravarMensalistaTelefone = new GravarMensalistaTelefone(mensalista, telefone);
                        gravarMensalistaTelefone.execute();

                        GravarContrato gravarContrato = new GravarContrato(contrato, mensalista);
                        gravarContrato.execute();

                        Intent abrirMensalistas = new Intent(CadastroMensalistaActivity.this, MensalistasActivity.class);
                        startActivity(abrirMensalistas);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
