package entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import entities.Enums.Servicos;

public class UsoMensalista extends UsoDeVaga {

	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago = 0.0;
	private Servicos servicos;

    public UsoMensalista(Vaga vaga) {
		super(vaga);
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
    }

	/**
     * Registra a saída do local, calcula o valor a ser pago pela permanência,
     * considerando serviços adicionais contratados, se houver.
     *
     * @return O valor a ser pago pela permanência, incluindo serviços adicionais, se aplicável.
     */
	@Override
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

	/**
     * Verifica se o registro de entrada ocorreu no mês fornecido.
     *
     * @param mes O mês a ser verificado
     * @return true se a entrada foi registrada no mês fornecido, caso contrário, false.
     */
	@Override
	public boolean ehDoMes(int mes) {
		return this.entrada.getMonthValue() == mes;
	}

	 /**
     * Calcula o valor a ser pago pela permanência no local.
     *
     * @return O valor a ser pago pela permanência, com base nos registros de entrada e saída.
     * @throws IllegalArgumentException Se entrada ou saída estiverem nulas.
     */
	@Override
	public double valorPago() {
		if (entrada == null || saida == null) {
			throw new IllegalArgumentException("Entrada and Saida cannot be null");
		}

		return valorPago;
	}

	public void contratarServico(Servicos servico) {
		this.servicos = servico;
	}

    
}