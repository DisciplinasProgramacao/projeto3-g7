package entities;

import java.time.LocalDateTime;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private Servicos servico;
	private boolean saiu; 

	/**
	 * Construtor da classe UsoDeVaga.
	 *
	 * @param vaga A vaga de estacionamento a ser usada.
	 */
	public UsoDeVaga(Vaga vaga) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
		this.saida = null; // Inicializa a saída como nula
		this.saiu = false; // Inicializa o status de saída como falso

		
	}

	/**
	 * Registra a saída do veículo da vaga e calcula o valor a ser pago com base no
	 * tempo de uso.
	 *
	 * @return O valor a ser pago pelo uso da vaga.
	 */
	public double sair() {
		if (saiu) {
			throw new IllegalStateException("O veículo já saiu da vaga.");
		}

		this.saida = LocalDateTime.now();
		long minutosEstacionado = calcularMinutosEstacionado();
		double valorPago = calcularValorPago(minutosEstacionado);

		saiu = true; // Define o status de saída como verdadeiro

		return valorPago;
	}

	private long calcularMinutosEstacionado() {
		return entrada.until(saida, java.time.temporal.ChronoUnit.MINUTES);
	}

	private double calcularValorPago(long minutosEstacionado) {
		double valor = (minutosEstacionado * FRACAO_USO * VALOR_FRACAO);
		if (valor > VALOR_MAXIMO) {
			valor = VALOR_MAXIMO;
		}

		if (servico != null) {
			return servico.getValor();
		} else {
			return 0.0; // ou algum outro valor padrão se servico for nulo
		}
	}

	/**
	 * Verifica se o uso da vaga ocorreu no mês especificado.
	 *
	 * @param mes O mês a ser verificado (1 para janeiro, 2 para fevereiro, etc.).
	 * @return true se o uso da vaga ocorreu no mês especificado, false caso
	 *         contrário.
	 */
	public boolean ehDoMes(int mes) {
		return this.entrada.getMonthValue() == mes;
	}

	/**
	 * Obtém o valor pago pelo uso da vaga.
	 *
	 * @return O valor pago pelo uso da vaga.
	 */
	public double valorPago() {
		return valorPago;
	}

	public Servicos adicionarServico(Servicos servicos) {
		if (this.servico == null) {
			this.servico = servicos;
			return this.servico;
		} else {
			return servicos;
		}
	}
}