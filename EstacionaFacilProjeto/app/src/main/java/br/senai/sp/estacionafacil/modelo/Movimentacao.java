package br.senai.sp.estacionafacil.modelo;

import java.io.Serializable;

public class Movimentacao implements Serializable {

    private int codMovimento;
    private String placa;
    private String dataHoraEntrada;
    private String dataHoraSaida;
    private String modeloCarro;
    private String tipo;
    private Integer tempoPermanecia;
    private Double valorPago;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodMovimento() {
        return codMovimento;
    }

    public void setCodMovimento(int codMovimento) {
        this.codMovimento = codMovimento;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(String dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public String getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(String dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public Integer getTempoPermanecia() {
        return tempoPermanecia;
    }

    public void setTempoPermanecia(Integer tempoPermanecia) {
        this.tempoPermanecia = tempoPermanecia;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    @Override
    public String toString() {
        return codMovimento + " - " + placa + " - " + modeloCarro;
    }
}
