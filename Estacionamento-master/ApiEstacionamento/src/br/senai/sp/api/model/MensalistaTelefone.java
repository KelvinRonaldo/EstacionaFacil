package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_mensalista_telefone")
public class MensalistaTelefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codMensalistaTelefone;
	
	@ManyToOne
	@JoinColumn(name = "cod_mensalista")
	private Long codMensalista;

	@ManyToOne
	@JoinColumn(name = "cod_telefone")
	private Long codTelefone;
	
}
