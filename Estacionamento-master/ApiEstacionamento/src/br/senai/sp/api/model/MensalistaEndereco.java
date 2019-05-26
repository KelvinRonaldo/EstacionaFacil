package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_mensalista_endereco")
public class MensalistaEndereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codMensalistaEndereco;
	@ManyToOne
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco;
	@ManyToOne
	@JoinColumn(name = "cod_mensalista")
	private Mensalista mensalista;
	
	public Long getCodMensalistaEndereco() {
		return codMensalistaEndereco;
	}
	public void setCodMensalistaEndereco(Long codMensalistaEndereco) {
		this.codMensalistaEndereco = codMensalistaEndereco;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Mensalista getMensalista() {
		return mensalista;
	}
	public void setMensalista(Mensalista mensalista) {
		this.mensalista = mensalista;
	}
	
	
}
