package br.senai.sp.api.resource;

import java.util.Date;
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

import br.senai.sp.api.model.Preco;
import br.senai.sp.api.repository.PrecoRepository;
import br.senai.sp.api.utils.ConverterDates;

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

//	@PostMapping
//	private ResponseEntity<Preco> cadastrarPreco(@RequestBody Preco preco,
//			HttpServletResponse response){
//		Preco precoSalvo =  
//		
//		return null;
//	}

	@PutMapping("/fimPreco/{id}")
	private ResponseEntity<Preco> finalizarPreco(@RequestBody Preco preco,
			@PathVariable Long id){
		
		Preco precoFim = precoRepository.findById(id).get();
		
		ConverterDates dateString = new ConverterDates();
		String data = dateString.dataAtualString();
		
		precoFim.setDataFim(data);
		
		BeanUtils.copyProperties(preco, precoFim, "id");
		preco.setDataFim(precoFim.getDataFim());
		
		//precoRepository.save(preco);
		
		System.out.println(precoFim.getDataFim() + " -----  data fim");
		return null;
	}
	
	
	
	
}
