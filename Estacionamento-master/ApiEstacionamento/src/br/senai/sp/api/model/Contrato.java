package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_contrato")
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codContrato;
	private Integer quantidadeVagas;
	private Double valorMensalista;
	private Integer diaPagar;
	@ManyToOne
	@JoinColumn(name = "cod_mensalista")
	private Mensalista codMensalista;

	
	public Long getCodContrato() {
		return codContrato;
	}

	public void setCodContrato(Long codContrato) {
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

	public Mensalista getCodMensalista() {
		return codMensalista;
	}

	public void setCodMensalista(Mensalista codMensalista) {
		this.codMensalista = codMensalista;
	}

}
