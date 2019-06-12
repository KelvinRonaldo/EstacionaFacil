package br.senai.sp.estacionafacil.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONStringer;

import java.security.acl.LastOwnerException;

import br.senai.sp.estacionafacil.modelo.Contrato;
import br.senai.sp.estacionafacil.modelo.Endereco;
import br.senai.sp.estacionafacil.modelo.Mensalista;
import br.senai.sp.estacionafacil.modelo.Movimentacao;
import br.senai.sp.estacionafacil.modelo.Pagamento;
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
            jsonEndereco.key("bairro").value(endereco.getBairro());
            jsonEndereco.key("cep").value(endereco.getCep());
            jsonEndereco.key("cidade").value(endereco.getCidade());
            jsonEndereco.key("estado").value(endereco.getEstado());
            jsonEndereco.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonEndereco;
    }

    public JSONStringer criarJsonMensalistaEndereco(Mensalista mensalista, Endereco endereco){
        JSONStringer jsonMensalistEndereco = new JSONStringer();

        try {
            jsonMensalistEndereco.object();
                jsonMensalistEndereco.key("mensalista").object()
                    .key("codMensalista").value(mensalista.getCodMensalista())
                .endObject();
            jsonMensalistEndereco.key("endereco").object()
                    .key("codEndereco").value(endereco.getCodEndereco())
                .endObject();
            jsonMensalistEndereco.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMensalistEndereco;
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

    public JSONStringer criarJsonMensalistaTelefone(Mensalista mensalista, Telefone telefone){
        JSONStringer jsonMensalistaTelefone = new JSONStringer();

        try {
            jsonMensalistaTelefone.object();
                jsonMensalistaTelefone.key("mensalista").object()
                    .key("codMensalista").value(mensalista.getCodMensalista())
                .endObject();
                jsonMensalistaTelefone.key("telefone").object()
                    .key("codTelefone").value(telefone.getCodTelefone())
                .endObject();
            jsonMensalistaTelefone.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMensalistaTelefone;
    }

    public JSONStringer criarJsonContrato(Contrato contrato, Mensalista mensalista){
        JSONStringer jsonContrato = new JSONStringer();

        try {
            jsonContrato.object();
            jsonContrato.key("quantidadeVagas").value(contrato.getQuantidadeVagas());
            jsonContrato.key("valorMensalista").value(0);
            jsonContrato.key("diaPagar").value(contrato.getDiaPagar());
            jsonContrato.key("mensalista").object()
                    .key("codMensalista").value(mensalista.getCodMensalista())
                    .endObject();
            jsonContrato.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonContrato;
    }
    public JSONStringer criarJsonPagamento(Pagamento pagamento, Contrato contrato){
        JSONStringer jsonPagamento = new JSONStringer();

        try {
            jsonPagamento.object();
            jsonPagamento.key("dataPago").value(null);
            jsonPagamento.key("valorPago").value(pagamento.getValorPago());
            jsonPagamento.key("contrato").object()
                    .key("codContrato").value(contrato.getCodContrato())
                    .endObject();
            jsonPagamento.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonPagamento;
    }

}
