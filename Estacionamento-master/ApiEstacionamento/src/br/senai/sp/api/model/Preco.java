package br.senai.sp.api.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_preco")
public class Preco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codPreco;
	private Double valorPrimeiraHora;
	private Double valorDemaisHoras;
	private Double valorMensal;
	private Integer tempoTolerancia;
	private String dataFim;

	public Long getCodPreco() {
		return codPreco;
	}

	public void setCodPreco(Long codPreco) {
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

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public String toString() {
		return "Preco [codPreco=" + codPreco + ", valorHora1=" + valorPrimeiraHora + ", valorDemaisHoras="
				+ valorDemaisHoras + ", tempoTolerancia=" + tempoTolerancia + ", dataFim=" + dataFim + "]";
	}

}
