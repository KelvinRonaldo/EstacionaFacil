package br.senai.sp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{
	
	@Query("select m from Movimentacao m where m.dataHoraSaida is Null")
	List<Movimentacao> findByDataHoraSaidaIsNull();
	
}
