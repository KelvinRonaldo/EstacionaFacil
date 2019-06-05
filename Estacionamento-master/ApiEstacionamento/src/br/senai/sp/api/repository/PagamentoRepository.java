package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.Pagamento;

public interface PagamentoRepository 
	extends JpaRepository<Pagamento, Long>{

}
