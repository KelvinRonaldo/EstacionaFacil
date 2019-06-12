package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Movimentacao;
import br.senai.sp.api.model.Veiculo;
import br.senai.sp.api.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	//pegando todos os carros cadastrados
	@GetMapping
	public List<Veiculo> getVeiculo(){
		return veiculoRepository.findAll();
	}
	
	//pegando apenas um carro cadastrado
	@GetMapping("/{id}")
	public Veiculo visualizarVeiculo(@PathVariable Long id) {
		return veiculoRepository.findById(id).get();
	}
	
	@GetMapping("/cadastrado/{placa}")
	private Veiculo getVeiculoPlaca(@PathVariable String placa){
		return veiculoRepository.findByPlaca(placa);
	}
	
	
	//cadastrando um veiculo
	@PostMapping
	public ResponseEntity<Veiculo> cadastraVeiculo(@RequestBody Veiculo veiculo, 
			HttpServletResponse response){
		
		Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(veiculo.getCodVeiculo())
				.toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(veiculoSalvo);
	}	
	
	//Atualizar Veiculo
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> atualizarVeiculo(
			@RequestBody Veiculo veiculo,
			@PathVariable Long id){
		
		Veiculo veiculosalvo = veiculoRepository.findById(id).get();
		BeanUtils.copyProperties(veiculo, veiculosalvo, "id");
		
		veiculoRepository.save(veiculo);
		
		
		return ResponseEntity.ok(veiculosalvo);
	}
	
	//DELETANDO O VEICULO
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteContato(@PathVariable Long id){
		veiculoRepository.deleteById(id);
	}
	
	
}
