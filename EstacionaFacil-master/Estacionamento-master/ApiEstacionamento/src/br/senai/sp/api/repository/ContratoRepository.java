package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Contrato;

public interface ContratoRepository 
	extends JpaRepository<Contrato, Long> {
	
	@Query("select contrato from Contrato contrato where contrato.mensalista.codMensalista = ?1")
	Contrato findByMensalista(Long codMensalista);
	

}
