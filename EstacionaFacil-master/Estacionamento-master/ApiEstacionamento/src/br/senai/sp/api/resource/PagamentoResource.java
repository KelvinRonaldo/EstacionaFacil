package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

import javax.persistence.Entity;
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

import br.senai.sp.api.model.Pagamento;
import br.senai.sp.api.repository.PagamentoRepository;
import br.senai.sp.api.utils.ConverterDates;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoResource {
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	//pegando todos os pagamentos
	@GetMapping
	private List<Pagamento> getPagamento(){
		return pagamentoRepository.findAll();
	}
	
	//pegando um pagamento
	@GetMapping("/{id}")
	private Pagamento visualizarPagamento(@PathVariable Long id) {
		return pagamentoRepository.findById(id).get();
	}
	
	//cadastatar pagamento
	@PostMapping
	private ResponseEntity<Pagamento> salvarPagamento(@RequestBody Pagamento pagamento,
			HttpServletResponse response){
		
		ConverterDates data = new ConverterDates();
		String dataAtual;
		dataAtual = data.dataAtualString();
		
		Pagamento pagamentoSalvo = pagamento;
		pagamentoSalvo.setDataPago(dataAtual);
		
		pagamentoSalvo = pagamentoRepository.save(pagamentoSalvo);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(pagamento.getCodPagamento())
				.toUri();
			
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(pagamento);
	}
	
	//atualizar Pagamento 
	
	@PutMapping("/{id}")
	private ResponseEntity<Pagamento> atualizarPagamento(@RequestBody Pagamento pagamento,
			@PathVariable Long id){
		
		Pagamento pagamentoAtualizado = pagamentoRepository.findById(id).get();
		
		BeanUtils.copyProperties(pagamento, pagamentoAtualizado, "id");
		
		pagamentoRepository.save(pagamento);
		
		return ResponseEntity.ok(pagamentoAtualizado);
	}
	
}
