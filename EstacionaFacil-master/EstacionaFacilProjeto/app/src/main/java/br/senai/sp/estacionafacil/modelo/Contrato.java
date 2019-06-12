package br.senai.sp.estacionafacil.modelo;

import java.io.Serializable;

public class Contrato implements Serializable {

    private Integer codContrato;
    private Integer quantidadeVagas;
    private Double valorMensalista;
    private Integer diaPagar;
//    private Mensalista mensalista;


    public Integer getCodContrato() {
        return codContrato;
    }

    public void setCodContrato(Integer codContrato) {
        this.codContrato = codContrato;
    }
    public Integer getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public void setQuantidadeVagas(Integer quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }

    public Double getValorMensalista() {
        return valorMensalista;
    }

    public void setValorMensalista(Double valorMensalista) {
        this.valorMensalista = valorMensalista;
    }

    public Integer getDiaPagar() {
        return diaPagar;
    }

    public void setDiaPagar(Integer diaPagar) {
        this.diaPagar = diaPagar;
    }
//
//    public Mensalista getMensalista() {
//        return mensalista;
//    }
//
//    public void setMensalista(Mensalista mensalista) {
//        this.mensalista = mensalista;
//    }

}
