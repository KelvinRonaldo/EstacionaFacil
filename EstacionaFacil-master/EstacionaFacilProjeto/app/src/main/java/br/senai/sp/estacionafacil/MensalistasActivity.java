package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.tasks.CarregarListaMensalista;

public class MensalistasActivity extends AppCompatActivity {
    public static ListView visualizarMensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensalistas);

        visualizarMensalista = findViewById(R.id.mensalista_view);

        visualizarMensalista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mensalista mensalista = (Mensalista) visualizarMensalista.getItemAtPosition(position);
                Intent intent = new Intent(MensalistasActivity.this, CarregarListaMensalista.class);
                intent.putExtra("mensalista", mensalista);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CarregarListaMensalista carregarListaMensalista = new CarregarListaMensalista(this);
        carregarListaMensalista.execute();
    }
}
