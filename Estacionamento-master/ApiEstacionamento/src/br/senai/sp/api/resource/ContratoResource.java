package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Contrato;
import br.senai.sp.api.repository.ContratoRepository;

@RestController
@RequestMapping("/contratos")
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
		return contratoRepository.findById(id).get();
	}
	
	//gravando um contrato
	@PostMapping
	private ResponseEntity<Contrato> salvarEndereco(
			@RequestBody Contrato contrato,
			HttpServletResponse response){

		Contrato contratoSalvo = contratoRepository.save(contrato);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(contrato.getCodContrato())
				.toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(contrato);
	
	}
	
	//atualizar contrato
	@PutMapping("/{id}")
	private ResponseEntity<Contrato> atualizarContrato(@RequestBody Contrato contrato,
			@PathVariable Long id){
		
		Contrato contratoAtualizado = contratoRepository.findById(id).get();
		
		BeanUtils.copyProperties(contrato, contratoAtualizado, "id");
		
		contratoRepository.save(contrato);
		
		return ResponseEntity.ok(contratoAtualizado);
	}
	
	
}










