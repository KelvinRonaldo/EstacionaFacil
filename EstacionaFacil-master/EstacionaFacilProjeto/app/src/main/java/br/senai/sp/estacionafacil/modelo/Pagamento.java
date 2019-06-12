package br.senai.sp.estacionafacil.modelo;

public class Pagamento {

    private Integer codPagamento;
    private String dataPago;
    private Double valorPago;

    public Integer getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(Integer codPagamento) {
        this.codPagamento = codPagamento;
    }

    public String getDataPago() {
        return dataPago;
    }

    public void setDataPago(String dataPago) {
        this.dataPago = dataPago;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

}
