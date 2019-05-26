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

import br.senai.sp.api.model.MensalistaEndereco;
import br.senai.sp.api.repository.MensalistaEnderecoRepository;

@RestController
@RequestMapping("/mensalista/endereco")
public class MensalistaEnderecoResource {
	
	@Autowired
	private MensalistaEnderecoRepository mensalistaEnderecoRepository;
	
	@GetMapping
	private List<MensalistaEndereco> getmensalistaEndereco(){
		return mensalistaEnderecoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private MensalistaEndereco visualizarMensalistaEndereco(@PathVariable Long id){
		return mensalistaEnderecoRepository.findById(id).get();
	}
	
	//CADASTRAR UM RELACIONAMENTO DE ENDERECO E MENSALISTA
	@PostMapping
	private ResponseEntity<MensalistaEndereco> relacionarMensalistaEEndereco(
			@RequestBody MensalistaEndereco mensalistaEndereco, HttpServletResponse response){
		
		MensalistaEndereco mensalistaEnderecoSalvo = mensalistaEnderecoRepository
				.save(mensalistaEndereco);
		
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequestUri()
				  .path("/{id}")
				  .buildAndExpand(mensalistaEndereco.getCodMensalistaEndereco())
				  .toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(mensalistaEnderecoSalvo);
	}
	
	
	//ATUALIZAR RELACIONAMENTO
	@PutMapping("/{id}")
	private ResponseEntity<MensalistaEndereco> atualizarRelacionamentoMensalistaEndereco(
			@RequestBody MensalistaEndereco mensalistaEndereco, @PathVariable Long id ){
		
		MensalistaEndereco mensalistaEnderecoAtualizado = mensalistaEnderecoRepository.findById(id).get();
		
		BeanUtils.copyProperties(mensalistaEndereco, mensalistaEnderecoAtualizado, "id");
		
		mensalistaEnderecoRepository.save(mensalistaEndereco);
		
		return ResponseEntity.ok(mensalistaEndereco);
	}
	
	
}
