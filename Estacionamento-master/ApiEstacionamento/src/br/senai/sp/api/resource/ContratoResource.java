package br.senai.sp.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Contrato;
import br.senai.sp.api.repository.ContratoRepository;

@RestController
@RequestMapping("/contrato")
public class ContratoResource {
	@Autowired
	private ContratoRepository contratoRepository;
	
	//pegando todos os contratos
	@GetMapping
	private List<Contrato> getContrato(){
		return contratoRepository.findAll();
	}
	
	//pegando um Contrato
	@GetMapping("/{id}")
	private Contrato visualizarContrato(@PathVariable Long id) {
		return contratoRepository.findById(id) .get();
	}
	
	@PostMapping
	private ResponseEntity<Contrato> salvarEndereco(
			@RequestBody Contrato contrato,
			HttpServletResponse response){
		return null;
	}
	
	
	
	
}










