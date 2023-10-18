package codigo;

import java.time.LocalDateTime;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private double valorServicos;

	/**
	 * Construtor da classe UsoDeVaga.
	 *
	 * @param vaga A vaga de estacionamento a ser usada.
	 */
	public UsoDeVaga(Vaga vaga) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
	}

	/**
	 * Registra a saída do veículo da vaga e calcula o valor a ser pago com base no
	 * tempo de uso.
	 *
	 * @return O valor a ser pago pelo uso da vaga.
	 */
	public double sair() {
		if (vaga.sair()) {
			this.saida = LocalDateTime.now();
			long tempoEmHoras = entrada.until(saida, java.time.temporal.ChronoUnit.HOURS);
			valorPago = Math.min(tempoEmHoras * VALOR_FRACAO, VALOR_MAXIMO);
			return valorPago;
		} else {
			return 0;
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
	public double getValorPago() {
		return valorPago;
	}

	/**
	 * Calcula o custo de um serviço levando em consideração o serviço e a duração
	 * da estadia.
	 *
	 * @param servico          O objeto de serviço contendo informações sobre o
	 *                         serviço
	 * @param tempoPermanencia duração de tempo em minutos
	 * @return o preço total do serviço escolhido
	 */
	public static double servico(servicos servico, double tempoPermanencia) {
		double valorAdicional = 0.0;

		if (tempoPermanencia < servico.getMinPermanencia()) {
			valorAdicional = (servico.getMinPermanencia() - tempoPermanencia) * 10.0;
		}

		return servico.getValue() + valorAdicional;
	}
}