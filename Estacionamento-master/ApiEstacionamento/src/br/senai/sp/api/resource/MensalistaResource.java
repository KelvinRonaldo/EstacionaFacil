package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.repository.MensalistaRepository;

@RestController
@RequestMapping("/mensalistas")
public class MensalistaResource {
	@Autowired
	private MensalistaRepository mensalistaRepository;
	
	@GetMapping
	private List<Mensalista> getMensalista(){
			return mensalistaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private Mensalista visualizarMensalista(@PathVariable Long id) {
			return mensalistaRepository.findById(id).get();
	}
	
	
//	@PostMapping
//	public void salvar(@RequestBody Mensalista m) {
//		mensalistaRepository.save(m); 
//	}
	
	//CADASTRO DE UM MENSALISTA
	@PostMapping
	public ResponseEntity<Mensalista> salvarMensalista(@RequestBody Mensalista mensalista, 
			HttpServletResponse response) {
		
		Mensalista mensalistaSalvo = mensalistaRepository.save(mensalista);
		
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{id}")
					.buildAndExpand(mensalista.getCodMensalista())
					.toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(mensalistaSalvo);
	}
	
	
	//ATUALIZAR MENSALISTA
	@PutMapping("/{id}")
	private ResponseEntity<Mensalista> atualizarMensalista(
			@RequestBody Mensalista mensalista, @PathVariable Long id){
		
		Mensalista mensalistaAtualizado = mensalistaRepository.findById(id).get();
		
		BeanUtils.copyProperties(mensalista, mensalistaAtualizado, "id");
		
		mensalistaRepository.save(mensalista);
		
		return ResponseEntity.ok(mensalistaAtualizado);
	}
	
}
