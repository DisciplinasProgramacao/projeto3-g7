package entities;

import java.util.LinkedList;

/**
 * A classe Veiculo representa um veículo que pode estacionar em vagas de
 * estacionamento.
 * Cada veículo é identificado por sua placa e pode registrar seu uso de vagas.
 */
public class Veiculo implements IDataToText {

	private String placa; // A placa do veículo
	private UsoDeVaga[] usos = new UsoDeVaga[10]; // Array de usos de vagas associado ao veículo
	private int mes = 0; // Mês atual para cálculos
	private int totalDeUsos = 0; // Total de usos de vagas registrados para o veículo
	private double arrecadadoNoMes = 0; // Valor arrecadado no mês atual
	private double totalArrecadado = 0; // Valor total arrecadado pelo veículo
	private int indiceDeVaga;
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getPlaca() {
		return placa;
	}

	public int getIndiceDeVaga() {
		return this.indiceDeVaga;
	}

	public void setIndiceDeVaga(int indiceDeVaga) {
		this.indiceDeVaga = indiceDeVaga;
	}

	/**
	 * Construtor da classe Veiculo.
	 *
	 * @param placa A placa do veículo.
	 */
	public Veiculo(String placa) {
		this.placa = placa;

	}

	/**
	 * Estaciona o veículo em uma vaga especificada.
	 *
	 * @param vaga A vaga em que o veículo será estacionado.
	 */
	public void estacionar(Vaga vaga) {

		if (vaga.disponivel() == true) {
			vaga.estacionar();
			if(vaga.estacionar() == true)
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

		for (int i = 0; i < indiceDeVaga; i++) {
			totalArrecadado += usos[i].valorPago();
		}
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
		
		for (int i = 0; i < indiceDeVaga; i++) {
			if (usos[indiceDeVaga].ehDoMes(mes)) {
				arrecadadoNoMes += usos[indiceDeVaga].valorPago();
			}
		}
		return arrecadadoNoMes;
	}

	/**
	 * Obtém o número total de usos de vagas registrados para o veículo.
	 *
	 * @return O número total de usos de vagas registrados para o veículo.
	 */
	public int totalDeUsos() {

		for (int i = 0; i < indiceDeVaga; i++) {
			if (usos[indiceDeVaga] != null) {
				totalDeUsos++;
			}
		}
		return totalDeUsos;
	}

	


	LinkedList<Veiculo> veiculos = new LinkedList<>();
	@Override
	public String dataToText() {
		double totalArrecadado =  veiculos.stream()
		.mapToDouble(veiculo -> veiculo.totalArrecadado())
		.sum();

		double totalDeUsos = veiculos.stream()
		.mapToDouble(veiculo -> veiculo.totalDeUsos())
		.sum();

		StringBuilder string = new StringBuilder();
		string.append("Placa: ").append(placa).append("\n").append("Total de Usos: ").append(totalDeUsos).append("\n");
		return string.toString();
	}
}
