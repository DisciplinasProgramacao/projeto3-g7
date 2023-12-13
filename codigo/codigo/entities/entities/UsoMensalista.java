package entities;

import java.time.LocalDateTime;

import entities.Enums.Servicos;

/**
 * Representa a utilização mensalista de uma vaga de estacionamento.
 * Essa classe estende a classe UsoDeVaga e adiciona funcionalidades específicas
 * para mensalistas.
 */
public class UsoMensalista extends UsoDeVaga {

	private double valorPago = 0.0;

	/**
	 * Cria uma instância de UsoMensalista com a vaga especificada.
	 *
	 * @param vaga A vaga de estacionamento utilizada pelo mensalista.
	 */
	public UsoMensalista(Vaga vaga) {
		super(vaga);
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
	}

	/**
	 * Cria uma instância de UsoMensalista com a vaga e os serviços especificados.
	 *
	 * @param vaga     A vaga de estacionamento utilizada pelo mensalista.
	 * @param servicos Os serviços adicionais utilizados pelo mensalista.
	 */
	public UsoMensalista(Vaga vaga, Servicos servicos) {
		super(vaga);
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
		this.servicos = servicos;
	}

	/**
	 * Calcula o valor a ser pago pela permanência no local.
	 *
	 * @return O valor a ser pago pela permanência, com base nos registros de
	 *         entrada e saída.
	 * @throws IllegalArgumentException Se entrada ou saída estiverem nulas.
	 */
	@Override
	public double valorPago() {
		if (entrada == null || saida == null) {
			throw new IllegalArgumentException("Entrada and Saida cannot be null");
		}
		if (servicos != null) {
			valorPago += servicos.getValor();
		}
		return valorPago;
	}
}