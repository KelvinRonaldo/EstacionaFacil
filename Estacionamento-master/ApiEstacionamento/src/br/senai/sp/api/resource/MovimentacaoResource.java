package br.senai.sp.api.resource;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Cause;
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

import br.senai.sp.api.repository.MovimentacaoRepository;
import br.senai.sp.api.repository.PrecoRepository;
import br.senai.sp.api.utils.CalculoPreco;
import br.senai.sp.api.utils.ConverterDates;
//import br.senai.sp.api.repository.PrecoRepository;
import br.senai.sp.api.model.*;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoResource {
	
	@Autowired
	private MovimentacaoRepository moveRepository;
	@Autowired
	private PrecoRepository precoRepository;
	
	
	@GetMapping("/estacionados")
	private List<Movimentacao> getMovimentacoes(){
		return moveRepository.findByDataHoraSaidaIsNull();
	}
	
	@PostMapping
	private ResponseEntity<Movimentacao> salvarMovimentacao(
			@RequestBody Movimentacao movimento,
			HttpServletResponse response) {
		
		ConverterDates date = new ConverterDates();
		String dataAtual = date.dataAtualString();		
		movimento.setDataHoraEntrada(dataAtual);
		Movimentacao movimentoSalvo = moveRepository.save(movimento);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(movimento.getCodMovimento())
				.toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(movimentoSalvo);
	}
	
	@GetMapping("/saida/{id}")
	private Movimentacao visualizarMovimentacao(
			@PathVariable Long id){
		
		Movimentacao movimento = moveRepository.findById(id).get();		
		Preco precos = precoRepository.findByDataFimIsNull();
		
		ConverterDates date = new ConverterDates();
		
		String dataAtual = date.dataAtualString();
		movimento.setDataHoraSaida(dataAtual);

		String dataSaidaFormatada = movimento.getDataHoraSaida();
		String dataEntradaFormatada = movimento.getDataHoraEntrada();
		
		Date dateSaida = date.stringToDate(dataSaidaFormatada);
		Date dateEntrada = date.stringToDate(dataEntradaFormatada);
		
		Long tempoMillis = dateSaida.getTime() - dateEntrada.getTime();
		Integer tempoMinutos = (int) ((tempoMillis/1000)/60);
		Integer tempoHoras = (int) (tempoMinutos/60);

		Double valorAPagar = 0.00;		
		CalculoPreco calculo = new CalculoPreco(tempoMinutos, precos);
		valorAPagar = calculo.calcularPreco();

		movimento.setTempoPermanencia(tempoMinutos);
		movimento.setValorPago(valorAPagar);
		
		System.out.println("VALOR "+valorAPagar);
		
		return moveRepository.findById(id).get();
	}

	@PutMapping("/saida/{id}")
	public ResponseEntity<Movimentacao> saidaMovimentacao(
			@RequestBody Movimentacao movimento,
			@PathVariable Long id){
		
		Movimentacao movimentoSalvo = moveRepository.findById(id).get();
		System.out.println("---->"+movimentoSalvo.getCodMovimento());
		
		BeanUtils.copyProperties(movimento, movimentoSalvo, "id");
		
		moveRepository.save(movimento);
		
//		System.out.println("Valor "+movimentoSalvo.getValorPago());
		
		return ResponseEntity.ok(movimentoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Movimentacao> atualizarMovimentacao(
			@RequestBody Movimentacao movimento,
			@PathVariable Long id){
		
		Movimentacao movimentoSalvo = moveRepository.findById(id).get();

		BeanUtils.copyProperties(movimento, movimentoSalvo, "id");
		
		moveRepository.save(movimento);
		
		return ResponseEntity.ok(movimentoSalvo);
	}
}








