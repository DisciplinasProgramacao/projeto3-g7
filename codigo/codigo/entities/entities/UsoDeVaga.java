package entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import entities.Enums.Servicos;
import entities.Enums.ETurnos;

public abstract class UsoDeVaga {

	protected static final double FRACAO_USO = 0.25;
	protected static final double VALOR_FRACAO = 4.0;
	protected static final double VALOR_MAXIMO = 50.0;
	protected Vaga vaga;
	protected LocalDateTime entrada;
	protected LocalDateTime saida;
	protected double valorPago;
	protected Servicos servicos;
	protected ETurnos turno;

	public UsoDeVaga(Vaga vaga) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
	}

	public UsoDeVaga(LocalDateTime entrada2, LocalDateTime saida2) {
		this.entrada = entrada2;
		this.saida = saida2;
	}

	public UsoDeVaga(Vaga vaga, Servicos servicos) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
		this.servicos = servicos;
	}

	public double sair() {
		this.saida = LocalDateTime.now();

		return valorPago();
	}

	public boolean ehDoMes(int mes) {
		return this.entrada.getMonthValue() == mes;
	}
	
	public int totalDeUsosNoMes(Stream<UsoDeVaga> usos, int mes) {
        return (int) usos.filter(u -> u.ehDoMes(mes)).count();
    }

	public double valorPago() {
		if (entrada == null || saida == null) {
			throw new IllegalArgumentException("Entrada and Saida cannot be null");
		}

		int calcTempo = (int) entrada.until(saida, ChronoUnit.MINUTES);
		if (calcTempo == 0)
			calcTempo = 1;
		int quantidadeFracoesTempo = (int) Math.ceil(calcTempo / 15.0);
		double valorPago = (quantidadeFracoesTempo * VALOR_FRACAO);
	
		if(servicos != null)
			valorPago += servicos.getValor();

		if (valorPago > VALOR_MAXIMO)
			valorPago = VALOR_MAXIMO;

		return valorPago;
	}

	public Servicos contratarServico(Servicos servico) {
		this.servicos = servico;
		return servico;
	}
}