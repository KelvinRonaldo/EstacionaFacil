package br.senai.sp.estacionafacil.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.senai.sp.estacionafacil.MainActivity;
import br.senai.sp.estacionafacil.R;
import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.tasks.CarregarListaMovimentos;
import br.senai.sp.estacionafacil.tasks.SaidaMovimento;
import br.senai.sp.estacionafacil.utils.Datas;

public class MovimentacaoListAdapter extends BaseAdapter {

    private MainActivity mainActivity;
    private List<Movimentacao> movimentacoes;

    public MovimentacaoListAdapter(MainActivity mainActivity, List<Movimentacao> movimentacoes){
        this.mainActivity = mainActivity;
        this.movimentacoes = movimentacoes;
    }


    @Override
    public int getCount() {
        return movimentacoes.size();
    }

    @Override
    public Object getItem(int position) {
        return movimentacoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movimentacoes.get(position).getCodMovimento();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //pegando a movimentação
        final Movimentacao movimentacao = movimentacoes.get(position);

        //atribuindo ao layout
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View view =  inflater.inflate(R.layout.lista_entrada, null);

        LinearLayout linearLayout = view.findViewById(R.id.layout_editar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        //botao de saida
        ImageButton imageButton = view.findViewById(R.id.botao_saida);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaidaMovimento saidaMovimento = new SaidaMovimento(movimentacao, mainActivity);
                saidaMovimento.execute();
            }
        });



        //preenchedo lista
        TextView view_placa = view.findViewById(R.id.placa_lista);
        view_placa.setText(movimentacao.getPlaca());

        TextView view_modelo = view.findViewById(R.id.modelo_lista);
        view_modelo.setText(movimentacao.getModeloCarro());

        TextView view_hora_entrada = view.findViewById(R.id.hora_entrada_lista);

        Datas datas = new Datas();
        Date dataEntrada = datas.stringToDate(movimentacao.getDataHoraEntrada());
        String stringEntrada = datas.dataToBrString(dataEntrada);

        Toast.makeText(mainActivity, stringEntrada+" <--AQUI", Toast.LENGTH_LONG).show();
        view_hora_entrada.setText(stringEntrada);

        TextView view_tipo = view.findViewById(R.id.tipo_veiculo_lista);
        view_tipo.setText(movimentacao.getTipo());



        return view;
    }
}