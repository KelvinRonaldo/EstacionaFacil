package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
