package entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import entities.Enums.Servicos;
import entities.Enums.ETurnos;

/**
 * Classe abstrata que representa o uso de uma vaga de estacionamento.
 */
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

	/**
	 * Verifica se o uso da vaga de estacionamento é de um mês específico.
	 * 
	 * @param mes O mês a ser verificado
	 * @return true se o uso da vaga for do mês especificado, caso contrário, false
	 */

	public boolean ehDoMes(int mes) {
		return this.entrada.getMonthValue() == mes;
	}

	/**
	 * Retorna o total de usos de vaga no mês especificado.
	 *
	 * @param usos o stream de usos de vaga
	 * @param mes o mês para o qual se deseja obter o total de usos
	 * @return o total de usos de vaga no mês especificado
	 */
	public int totalDeUsosNoMes(Stream<UsoDeVaga> usos, int mes) {
		return (int) usos.filter(u -> u.ehDoMes(mes)).count();
	}

	/**
	 * Calcula o valor a ser pago pelo uso da vaga de estacionamento.
	 * 
	 * @return o valor a ser pago pelo uso da vaga de estacionamento.
	 * @throws IllegalArgumentException se a entrada ou saída forem nulas.
	 */
	public double valorPago() {
		if (entrada == null || saida == null) {
			throw new IllegalArgumentException("Entrada and Saida cannot be null");
		}

		int calcTempo = (int) entrada.until(saida, ChronoUnit.MINUTES);
		if (calcTempo == 0)
			calcTempo = 1;
		int quantidadeFracoesTempo = (int) Math.ceil(calcTempo / 15.0);
		double valorPago = (quantidadeFracoesTempo * VALOR_FRACAO);

		if (servicos != null)
			valorPago += servicos.getValor();

		if (valorPago > VALOR_MAXIMO)
			valorPago = VALOR_MAXIMO;

		return valorPago;
	}

	/**
	 * Contrata um serviço para o uso de vaga.
	 *
	 * @param servico O serviço a ser contratado.
	 * @return O serviço contratado.
	 */
	public Servicos contratarServico(Servicos servico) {
		this.servicos = servico;
		return servico;
	}
}