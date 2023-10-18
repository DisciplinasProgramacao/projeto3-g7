/**
 * A classe Veiculo representa um veículo que pode estacionar em vagas de
 * estacionamento.
 * Cada veículo é identificado por sua placa e pode registrar seu uso de vagas.
 */
public class Veiculo {

	private String placa; // A placa do veículo
	private UsoDeVaga[] usos; // Array de usos de vagas associado ao veículo
	private int mes = 0; // Mês atual para cálculos
	private int totalDeUsos = 0; // Total de usos de vagas registrados para o veículo
	private double arrecadadoNoMes = 0; // Valor arrecadado no mês atual
	private double totalArrecadado = 0; // Valor total arrecadado pelo veículo

	/**
	 * Construtor da classe Veiculo.
	 *
	 * @param placa A placa do veículo.
	 * @param string
	 */
	public Veiculo(String placa, String string) {
		this.placa = placa;
	}

	public Veiculo(String string) {
    }

    /**
	 * Estaciona o veículo em uma vaga especificada.
	 *
	 * @param vaga A vaga em que o veículo será estacionado.
	 */
	public void estacionar(Vaga vaga) {

		if (vaga.disponivel() == true) {
			vaga.estacionar();
			usos[indiceDeVaga] = new UsoDeVaga(vaga);
			indiceDeVaga++;
		}
	}

	/**
	 * Registra a saída do veículo da vaga e calcula o valor pago.
	 *
	 * @return O valor pago pelo uso da vaga.
	 */
	public double sair() {
		return usos[indiceDeVaga].sair();
	}

	/**
	 * Calcula o valor total arrecadado pelo veículo até o momento.
	 *
	 * @return O valor total arrecadado pelo veículo.
	 */
	public double totalArrecadado() {

		totalArrecadado += usos[indiceDeVaga].valorPago();
		return totalArrecadado;

	}

	/**
	 * Calcula o valor arrecadado pelo veículo no mês especificado.
	 *
	 * @param mes O mês para o qual se deseja calcular o valor arrecadado.
	 * @return O valor arrecadado pelo veículo no mês especificado.
	 */
	public double arrecadadoNoMes(int mes) {

		this.mes = mes;

		if (usos[indiceDeVaga].ehDoMes(mes) == true) {
			return arrecadadoNoMes += usos[indiceDeVaga].valorPago();
		}
	}

	/**
	 * Obtém o número total de usos de vagas registrados para o veículo.
	 *
	 * @return O número total de usos de vagas registrados para o veículo.
	 */
	public int totalDeUsos() {

		if (usos[indiceDeVaga] != null) {
			totalDeUsos++;
		}
		return totalDeUsos;
	}
}
