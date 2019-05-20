package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Preco;
import br.senai.sp.api.repository.PrecoRepository;

@RestController
@RequestMapping("/precos")
public class PrecoResource {

	@Autowired
	private PrecoRepository precoRepository;
	
	@GetMapping
	private List<Preco> getPrecos(){
		return precoRepository.findAll();
	}
	
	@GetMapping("/precoVigente")
	private Preco getPrecoVigante(){
		return precoRepository.findByDataFimIsNull();
	}
}
