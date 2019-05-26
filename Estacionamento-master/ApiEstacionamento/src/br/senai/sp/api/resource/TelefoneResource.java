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

import br.senai.sp.api.model.Telefone;
import br.senai.sp.api.repository.TelefoneRepository;

@RestController
@RequestMapping("/telefone")
public class TelefoneResource {
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	
	//PEGANDO TODOS OS TELEFONES
	@GetMapping
	private List<Telefone> getTelefone(){
		return telefoneRepository.findAll();
	}
	
	//PEGANDO UM USUARIO
	@GetMapping("/{id}")
	private Telefone visualizarTelene(@PathVariable Long id) {
		return telefoneRepository.findById(id).get();
	}
	
	
	//CADASTRANDO TELEFONE
	@PostMapping
	private ResponseEntity<Telefone> cadastrarTelefone(@RequestBody Telefone telefone,
			HttpServletResponse response){
		
		Telefone telefoneSalvo = telefoneRepository.save(telefone);
		
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{id}")
					.buildAndExpand(telefone.getCodTelefone())
					.toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(telefoneSalvo);
		
	}
	
	//Atulizar telefone
	@PutMapping("/{id}")
	private ResponseEntity<Telefone> atualizarTelefone(@RequestBody Telefone telefone,
			@PathVariable Long id){
		
		Telefone telefoneAtualizado = telefoneRepository.findById(id).get();
		
		BeanUtils.copyProperties(telefone, telefoneAtualizado, "id");
		
		telefoneRepository.save(telefone);
		
		return ResponseEntity.ok(telefone);
	}
	
}
