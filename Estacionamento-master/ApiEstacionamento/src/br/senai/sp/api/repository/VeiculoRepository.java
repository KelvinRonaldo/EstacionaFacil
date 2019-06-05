package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.api.model.Movimentacao;
import br.senai.sp.api.model.Veiculo;

public interface VeiculoRepository 
	extends JpaRepository<Veiculo, Long>{

	@Query("select v from Veiculo v where v.placa = ?1")
	Veiculo findByPlaca(String placa);
}
