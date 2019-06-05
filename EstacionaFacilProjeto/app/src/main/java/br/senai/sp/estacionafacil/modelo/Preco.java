package br.senai.sp.estacionafacil.modelo;

public class Preco {

    private int codPreco;
    private Double valorPrimeiraHora;
    private Double valorDemaisHoras;
    private Double valorMensal;
    private Integer tempoTolerancia;

    public int getCodPreco() {
        return codPreco;
    }

    public void setCodPreco(int codPreco) {
        this.codPreco = codPreco;
    }

    public Double getValorPrimeiraHora() {
        return valorPrimeiraHora;
    }

    public void setValorPrimeiraHora(Double valorPrimeiraHora) {
        this.valorPrimeiraHora = valorPrimeiraHora;
    }

    public Double getValorDemaisHoras() {
        return valorDemaisHoras;
    }

    public void setValorDemaisHoras(Double valorDemaisHoras) {
        this.valorDemaisHoras = valorDemaisHoras;
    }

    public Double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(Double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public Integer getTempoTolerancia() {
        return tempoTolerancia;
    }

    public void setTempoTolerancia(Integer tempoTolerancia) {
        this.tempoTolerancia = tempoTolerancia;
    }

    @Override
    public String toString() {
        return "Preco{" +
                "codPreco=" + codPreco +
                ", valorPrimeiraHora=" + valorPrimeiraHora +
                ", valorDemaisHoras=" + valorDemaisHoras +
                ", valorMensal=" + valorMensal +
                ", tempoTolerancia=" + tempoTolerancia +
                '}';
    }
}
