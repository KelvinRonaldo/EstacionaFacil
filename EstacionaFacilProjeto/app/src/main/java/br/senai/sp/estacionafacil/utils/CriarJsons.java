package br.senai.sp.estacionafacil.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONStringer;

import java.security.acl.LastOwnerException;

import br.senai.sp.estacionafacil.modelo.Endereco;
import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.modelo.Preco;
import br.senai.sp.estacionafacil.modelo.Telefone;

public class CriarJsons {


    public JSONStringer criarJsonSaidaMovimento(Movimentacao movimento, String modo){
        JSONStringer jsonMovimento = new JSONStringer();

        try {
            jsonMovimento.object();
            if(modo.equals("atualizar")){
                jsonMovimento.key("codMovimento").value(movimento.getCodMovimento());
            }
            jsonMovimento.key("placa").value(movimento.getPlaca());
            jsonMovimento.key("modeloCarro").value(movimento.getModeloCarro());
            jsonMovimento.key("dataHoraEntrada").value(movimento.getDataHoraEntrada());
            jsonMovimento.key("tipo").value(movimento.getTipo());
            jsonMovimento.key("tempoPermanencia").value(0);
            jsonMovimento.key("valorPago").value(0);
            jsonMovimento.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonMovimento;
    }

    public JSONStringer criarJsonSaidaMovimento(Movimentacao movimento){
        JSONStringer jsonMovimento = new JSONStringer();

        try {
            jsonMovimento.object();
            jsonMovimento.key("codMovimento").value(movimento.getCodMovimento());
            jsonMovimento.key("placa").value(movimento.getPlaca());
            jsonMovimento.key("modeloCarro").value(movimento.getModeloCarro());
            jsonMovimento.key("dataHoraEntrada").value(movimento.getDataHoraEntrada());
            jsonMovimento.key("dataHoraSaida").value(movimento.getDataHoraSaida());
            jsonMovimento.key("tempoPermanencia").value(movimento.getTempoPermanecia());
            jsonMovimento.key("valorPago").value(movimento.getValorPago());
            jsonMovimento.key("tipo").value(movimento.getTipo());
            jsonMovimento.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMovimento;
    }



    public JSONStringer criarJsonMensalista(Mensalista mensalista){
        JSONStringer jsonMensalista = new JSONStringer();

        try {
            jsonMensalista.object();
            jsonMensalista.key("nome").value(mensalista.getNome().toString());
            jsonMensalista.key("email").value(mensalista.getEmail().toString());
            jsonMensalista.key("cpf").value(mensalista.getCpf().toString());
            jsonMensalista.endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMensalista;
    }


    public JSONStringer criarJsonPrecos(Preco preco){
        JSONStringer jsonPreco = new JSONStringer();

        try {
            jsonPreco.object();
            jsonPreco.key("valorPrimeiraHora").value(preco.getValorPrimeiraHora());
            jsonPreco.key("valorDemaisHoras").value(preco.getValorDemaisHoras());
            jsonPreco.key("valorMensal").value(preco.getValorMensal());
            jsonPreco.key("tempoTolerancia").value(preco.getTempoTolerancia());
            jsonPreco.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonPreco;
    }

    public JSONStringer criarJsonEndereco(Endereco endereco){
        JSONStringer jsonEndereco = new JSONStringer();

        try {
            jsonEndereco.object();
            jsonEndereco.key("logradouro").value(endereco.getLogradouro());
            jsonEndereco.key("numero").value(endereco.getNumero());
            jsonEndereco.key("cep").value(endereco.getCep());
            jsonEndereco.key("cidade").value(endereco.getCidade());
            jsonEndereco.key("estado").value(endereco.getEstado());
            jsonEndereco.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonEndereco;
    }

    public JSONStringer criarJsonMensalistaEndereco(int codMensalita, int codEndereco){
        JSONStringer jsonMensalistEndereco = new JSONStringer();

        try {
            jsonMensalistEndereco.object();
            jsonMensalistEndereco.key("codMensalista").value(codMensalita);
            jsonMensalistEndereco.key("codEndereco").value(codEndereco);
            jsonMensalistEndereco.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMensalistEndereco;
    }

    public JSONStringer criarJsonMensalistaTelefone(int codMensalita, int codTelefone){
        JSONStringer jsonMensalistaTelefone = new JSONStringer();

        try {
            jsonMensalistaTelefone.object();
            jsonMensalistaTelefone.key("codMensalista").value(codMensalita);
            jsonMensalistaTelefone.key("codTelefone").value(codTelefone);
            jsonMensalistaTelefone.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMensalistaTelefone;
    }

    public JSONStringer criarJsonTelefone(Telefone telefone){
        JSONStringer jsonTelefone = new JSONStringer();

        try {
            jsonTelefone.object();
            jsonTelefone.key("telefone").value(telefone.getTelefone());
            jsonTelefone.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonTelefone;
    }

}
