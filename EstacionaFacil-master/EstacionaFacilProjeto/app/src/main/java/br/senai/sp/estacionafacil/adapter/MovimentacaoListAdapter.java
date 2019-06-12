package br.senai.sp.estacionafacil.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import br.senai.sp.estacionafacil.CadastroMovimentoActivity;
import br.senai.sp.estacionafacil.EstacionadosActivity;
import br.senai.sp.estacionafacil.R;
import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.tasks.AtualizarMovimento;
import br.senai.sp.estacionafacil.tasks.SaidaMovimento;
import br.senai.sp.estacionafacil.utils.Datas;

public class MovimentacaoListAdapter extends BaseAdapter {

    private EstacionadosActivity estacionadosActivity;
    private List<Movimentacao> movimentacoes;

    public MovimentacaoListAdapter(EstacionadosActivity estacionadosActivity, List<Movimentacao> movimentacoes){
        this.estacionadosActivity = estacionadosActivity;
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
        LayoutInflater inflater = LayoutInflater.from(estacionadosActivity);
        View view =  inflater.inflate(R.layout.lista_entrada, null);

        LinearLayout layoutEditar = view.findViewById(R.id.layout_editar);
        layoutEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intentAtulizar = new Intent(estacionadosActivity, CadastroMovimentoActivity.class);
                intentAtulizar.putExtra("movimento", movimentacao);
                 estacionadosActivity.startActivity(intentAtulizar);
            }
        });


        //botao de saida
        ImageButton imageButton = view.findViewById(R.id.botao_saida);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaidaMovimento saidaMovimento = new SaidaMovimento(movimentacao, estacionadosActivity);
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
        String stringDataEntrada = datas.dataToBrString(dataEntrada);

//        String dataHora = datas.formatarComSubString(movimentacao.getDataHoraEntrada());

        Log.d("HORA", stringDataEntrada);


//        Toast.makeText(estacionadosActivity, dataHora+" <--AQUI", Toast.LENGTH_LONG).show();
        view_hora_entrada.setText(stringDataEntrada);
//        view_hora_entrada.setText(dataHora);

        TextView view_tipo = view.findViewById(R.id.tipo_veiculo_lista);
        view_tipo.setText(movimentacao.getTipo());



        return view;
    }
}