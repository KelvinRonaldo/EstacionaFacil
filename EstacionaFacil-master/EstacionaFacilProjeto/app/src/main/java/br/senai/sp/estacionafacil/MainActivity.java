package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import br.senai.sp.estacionafacil.modelo.Pagamento;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static String ipServidor = "10.107.144.27";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        INSTANCIANDO TOOLBAR
        toolbar = findViewById(R.id.toolbar);
//        SETANDO ELA NA ACTIVITY
        setSupportActionBar(toolbar);

//        INSTANCIANDO DRAWER LAYOUT
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggleDrawer =
                new ActionBarDrawerToggle(this,
                        drawerLayout, toolbar,
                        R.string.open_drawer,
                        R.string.close_drawer);

        drawerLayout.addDrawerListener(toggleDrawer);

        toggleDrawer.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.menu_movimentacoes:
                Intent movimentos = new Intent(MainActivity.this, EstacionadosActivity.class);
                this.startActivity(movimentos);
                break;
            case R.id.cadastrar_mensalista:
                Intent mensalista = new Intent(MainActivity.this, CadastroMensalistaActivity.class);
                this.startActivity(mensalista);
                break;
            case R.id.visualizar_mensalista:
                Intent viewMensalista = new Intent(MainActivity.this, MensalistasActivity.class);
                this.startActivity(viewMensalista);
                break;
            case R.id.pagamento_mensalista:
                Intent pagamentoMensalista = new Intent(MainActivity.this, PagamentoActivity.class);
                startActivity(pagamentoMensalista);
                break;
            case R.id.regitrar_precos:
                Intent precos = new Intent(MainActivity.this, CadastroPrecosActivity.class);
                startActivity(precos);
                break;
            default:
                Toast.makeText(this, "Nenhum", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }
}
