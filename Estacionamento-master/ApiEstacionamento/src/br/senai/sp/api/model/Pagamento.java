package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pagamento")
public class Pagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codPagamento;
	private String dataPago;
	private Double valorPago;
	@ManyToOne
	@JoinColumn(name = "cod_pagamento")
	private Contrato codContrato;

	public Long getCodPagamento() {
		return codPagamento;
	}

	public void setCodPagamento(Long codPagamento) {
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

	public Contrato getCodContrato() {
		return codContrato;
	}

	public void setCodContrato(Contrato codContrato) {
		this.codContrato = codContrato;
	}

}
