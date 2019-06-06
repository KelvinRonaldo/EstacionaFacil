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

import br.senai.sp.api.model.MensalistaTelefone;
import br.senai.sp.api.repository.MensalistaTelefoneRepository;

@RestController
@RequestMapping("/mensalistas/telefones")
public class MensalistaTelefoneResource {
	
	@Autowired
	private MensalistaTelefoneRepository mensalistaTelefoneRepository;
	
	//Pegando todos os telefones e mensalistas
	@GetMapping
	private List<MensalistaTelefone> getMensalistaTelefone(){
		return mensalistaTelefoneRepository.findAll();
	}
	
	//Pegando um telefone e mensalista
	@GetMapping("/{id}")
	private MensalistaTelefone vizualizartMensalistaTelefone(@PathVariable Long id){
		return mensalistaTelefoneRepository.findById(id).get();
	}
	
	//cadastrando um relacionamento entre mensalista e telefine
	@PostMapping
	private ResponseEntity<MensalistaTelefone> relacionarMensalistaTelefone(
			@RequestBody MensalistaTelefone mensalistaTelefone, HttpServletResponse response){

		MensalistaTelefone mensalistaTelefoneSalvo = mensalistaTelefoneRepository
				.save(mensalistaTelefone);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(mensalistaTelefone.getCodMensalistaTelefone())
				.toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(mensalistaTelefoneSalvo);
	}
	
	
	//Atualizar relacionamento 
	@PutMapping("/{id}")
	private ResponseEntity<MensalistaTelefone> atualizarRelacionamentoMensalistaTelefone(
			@RequestBody MensalistaTelefone mensalistaTelefone, @PathVariable Long id){
		
		MensalistaTelefone mensalistaTelefoneAtualizado = mensalistaTelefoneRepository
				.findById(id).get();
		
		BeanUtils.copyProperties(mensalistaTelefone, mensalistaTelefoneAtualizado);
		
		return ResponseEntity.ok(mensalistaTelefone);
	}
}






