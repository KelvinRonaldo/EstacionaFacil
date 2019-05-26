package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Cidade;
import br.senai.sp.api.repository.CidadeRepository;

@RestController
@RequestMapping("/cidade")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	//pegando todas as cidades 
	@GetMapping
	private List<Cidade> getCidade(){		
		return cidadeRepository.findAll();
	}
	
	//pegando um cidade 
	@GetMapping("/{id}")
	private Cidade vizualizarCidade(@PathVariable Long id){		
		return cidadeRepository.findById(id).get();
	}
	
	
	
	
}
