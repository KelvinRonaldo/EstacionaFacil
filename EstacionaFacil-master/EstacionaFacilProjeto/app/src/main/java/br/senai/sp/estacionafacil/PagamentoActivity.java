package br.senai.sp.estacionafacil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.concurrent.ExecutionException;

import br.senai.sp.estacionafacil.modelo.Contrato;
import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.modelo.Pagamento;
import br.senai.sp.estacionafacil.tasks.BuscarContrato;
import br.senai.sp.estacionafacil.tasks.CarregarListaMensalista;
import br.senai.sp.estacionafacil.tasks.RegistrarPagamento;

public class PagamentoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static Spinner spnMensalistas;
    private EditText txtValorPagoMensalista;
    private Button btnRegistroPagamento;
    public static Contrato contrato;
    private Pagamento pagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);


        spnMensalistas = findViewById(R.id.spn_mensalistas);
        txtValorPagoMensalista = findViewById(R.id.txt_valor_pago_mensalista);
        btnRegistroPagamento = findViewById(R.id.btn_registro_pagamento);

        spnMensalistas.setOnItemSelectedListener(this);
        pagamento = new Pagamento();

        btnRegistroPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contrato.getCodContrato();
                pagamento.setValorPago(Double.valueOf(txtValorPagoMensalista.getText().toString()));

                RegistrarPagamento registrarPagamento = new RegistrarPagamento(pagamento, contrato);
                registrarPagamento.execute();
                finish();

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        CarregarListaMensalista carregarSpinnerMensalista = new CarregarListaMensalista(this);
        carregarSpinnerMensalista.execute();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Mensalista mensalista = (Mensalista) parent.getItemAtPosition(position);

        BuscarContrato buscarContrato = new BuscarContrato(mensalista);
        buscarContrato.execute();

        try {
            buscarContrato.get();

            txtValorPagoMensalista.setText(contrato.getValorMensalista().toString());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
