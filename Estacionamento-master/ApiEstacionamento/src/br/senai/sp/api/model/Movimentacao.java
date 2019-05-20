package br.senai.sp.api.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "tbl_movimentacao")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codMovimento;
	@NotNull
	@Size(min = 8, max = 8)
	private String placa;
	@NotNull
	@Size(min = 1, max = 15)
	private String modeloCarro;
	private String dataHoraEntrada;
	private String dataHoraSaida;
	private String tipo;
	private Integer tempoPermanencia;
	private Double valorPago;

	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getCodMovimento() {
		return codMovimento;
	}

	public void setCodMovimento(Long codEntrada) {
		this.codMovimento = codEntrada;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
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

	public Integer getTempoPermanencia() {
		return tempoPermanencia;
	}

	public void setTempoPermanencia(Integer tempoPermanencia) {
		this.tempoPermanencia = tempoPermanencia;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	@Override
	public String toString() {
		return "Movimentacao [codEntrada=" + codMovimento + ", placa=" + placa + ", hora_entrada=" + dataHoraEntrada
				+ ", hora_saida=" + dataHoraSaida + ", tempoPermanencia=" + tempoPermanencia + ", valorPago=" + valorPago
				+ "]";
	}

}
