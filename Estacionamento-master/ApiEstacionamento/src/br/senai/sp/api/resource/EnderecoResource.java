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

import br.senai.sp.api.model.Endereco;
import br.senai.sp.api.repository.EnderecoRepository;
@RestController
@RequestMapping("/endereco")
public class EnderecoResource {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//pegando todos os enderecos
	@GetMapping
	private List<Endereco> getEndereco(){
		return enderecoRepository.findAll();
	}
	
	//pegando um endereco
	@GetMapping("/{id}")
	private Endereco  visualizarFabricante(@PathVariable Long id) {
		return enderecoRepository.findById(id).get();
	}
	
	//CADASTRAR endereco
	@PostMapping
	private ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco,
			HttpServletResponse response){
		
		Endereco menderecoSalvo = enderecoRepository.save(endereco);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(endereco.getCodEndereco())
				.toUri();
		
		response.addHeader("Location", uri.toASCIIString());	
		
		return ResponseEntity.created(uri).body(endereco);
	}
	
	//ATUALIZAR ENDERECO
	@PutMapping("/{id}")
	private ResponseEntity<Endereco> atualizarEndereco(@RequestBody Endereco endereco,
			@PathVariable Long id){
		
		Endereco enderecoAtualizado = enderecoRepository.findById(id).get();
		
		BeanUtils.copyProperties(endereco, enderecoAtualizado, "id");
		
		enderecoRepository.save(endereco);
		
		return ResponseEntity.ok(enderecoAtualizado);
		
	}
	
	
	
}
