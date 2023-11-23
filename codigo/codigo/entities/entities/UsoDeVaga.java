package entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import entities.Enums.Servicos;
import entities.Enums.Turnos;

public abstract class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;

	private double valorPago;
	private Servicos servicos;
	private Turnos turno;
	private boolean saiu;

	public Vaga getVaga() {
		return this.vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public LocalDateTime getEntrada() {
		return this.entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return this.saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public double getValorPago() {
		return this.valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public Servicos getServicos() {
		return this.servicos;
	}

	public void setServicos(Servicos servicos) {
		this.servicos = servicos;
	}

	public boolean isSaiu() {
		return this.saiu;
	}

	public boolean getSaiu() {
		return this.saiu;
	}

	public void setSaiu(boolean saiu) {
		this.saiu = saiu;
	}

	public Turnos getTurno() {
		return this.turno;
	}

	public UsoDeVaga(Vaga vaga) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
	}

	public UsoDeVaga(LocalDateTime entrada2, LocalDateTime saida2) {
		this.entrada = entrada2;
		this.saida = saida2;
	}

	public double sair() {
		this.saida = LocalDateTime.now();
		int tempoPermanenciaMinutos = (int) entrada.until(saida, ChronoUnit.MINUTES);
		if (servicos != null) {
			if (tempoPermanenciaMinutos >= servicos.getTempo()) {
				return valorPago() + servicos.getValor();
			}
		}
		return valorPago();
	}

	public boolean ehDoMes(int mes) {
		return this.entrada.getMonthValue() == mes;
	}

	public double valorPago() {
		if (entrada == null || saida == null) {
			throw new IllegalArgumentException("Entrada and Saida cannot be null");
		}

		int calcTempo = (int) entrada.until(saida, ChronoUnit.MINUTES);
		if (calcTempo == 0)
			calcTempo = 1;
		int quantidadeFracoesTempo = (int) Math.ceil(calcTempo / 15.0);
		double valorPago = quantidadeFracoesTempo * VALOR_FRACAO;

		if (valorPago > VALOR_MAXIMO)
			valorPago = VALOR_MAXIMO;

		return valorPago;
	}

	public void contratarServico(Servicos servico) {
		this.servicos = servico;
	}

	// public void setSaida(LocalDateTime saida) {
	// this.saida = saida;
	// }
}