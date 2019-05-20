package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Preco;

public interface PrecoRepository extends JpaRepository<Preco, Long> {

	@Query("select preco from Preco preco where preco.dataFim is Null")
	Preco findByDataFimIsNull();
	
}
