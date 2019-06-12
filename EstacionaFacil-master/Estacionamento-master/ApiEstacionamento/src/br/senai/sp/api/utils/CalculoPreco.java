package br.senai.sp.api.utils;

import org.springframework.beans.factory.annotation.Autowired;

import br.senai.sp.api.model.Preco;
import br.senai.sp.api.repository.PrecoRepository;

public class CalculoPreco {
	
	private Integer tempoMinutos;	
	private Preco precos;

	public CalculoPreco(Integer tempoMinutos, Preco precos) {
		this.tempoMinutos = tempoMinutos;
		this.precos = precos;
	}

	public Double calcularPreco() {
		Double precoHora1 = precos.getValorPrimeiraHora();
		Double precoDemaisHoras = precos.getValorDemaisHoras();
		Integer tempoTolerancia = precos.getTempoTolerancia();
		Double valorAPagar = 0.00;
		
		if(tempoMinutos > tempoTolerancia) {
			valorAPagar = precoHora1;
			tempoMinutos -= 60;
//			tempoTolerancia += 60;
			for(int i = tempoTolerancia; i < tempoMinutos; i += 60) {
				valorAPagar += precoDemaisHoras;
			}
		}		
		return valorAPagar;
	}

}
