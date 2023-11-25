package entities;

import java.util.LinkedList;

import entities.Enums.ECliente;
import entities.Enums.ETurnos;

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
	private int indiceDeVaga = 0;
	private ECliente eCliente;
	private ETurnos eTurnos;

	/**
	 * Construtor da classe Veiculo.
	 *
	 * @param placa A placa do veículo.
	 */
	public Veiculo(String placa) {
		this.placa = placa;

	}

	public void setEcliente(ECliente eCliente) {
		this.eCliente = eCliente;
	}

	public void setETurno(ETurnos turno) {
		this.eTurnos = turno;
	}

	/**
	 * Estaciona o veículo em uma vaga especificada.
	 *
	 * @param vaga A vaga em que o veículo será estacionado.
	 */
	public void estacionar(Vaga vaga) {

		if (vaga.disponivel() == true) {
			if (vaga.estacionar() == true)
				if (eCliente != null && eCliente.getNome() != null) {
					switch (eCliente.getNome()) {
						case "Horista":
							usos[indiceDeVaga] = new UsoHorista(vaga);
							indiceDeVaga++;
							break;
						case "Mensalista":
							usos[indiceDeVaga] = new UsoMensalista(vaga);
							indiceDeVaga++;
							break;
						case "Turno":
							usos[indiceDeVaga] = new UsoTurno(vaga, eTurnos);
							indiceDeVaga++;
							break;
						default:
							break;
					}
				} else {
					throw new IllegalArgumentException("eCliente or eCliente.getNome() cannot be null");
				}
		}
	}

	/**
	 * Registra a saída do veículo da vaga e calcula o valor pago.
	 *
	 * @return O valor pago pelo uso da vaga.
	 */
	public double sair() {
		return usos[indiceDeVaga - 1].sair();
	}

	/**
	 * Calcula o valor total arrecadado pelo veículo até o momento.
	 *
	 * @return O valor total arrecadado pelo veículo.
	 */
	public double totalArrecadado() {
		totalArrecadado = 0.0;
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
		arrecadadoNoMes = 0.0;
		this.mes = mes;

		for (int i = 0; i < indiceDeVaga; i++) {
			if (usos[indiceDeVaga - 1].ehDoMes(mes)) {
				arrecadadoNoMes += usos[indiceDeVaga - 1].valorPago();
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

	public boolean equals(Veiculo v) {
		boolean resp = false;
		if (v.placa.equals(this.placa)) {
			resp = true;
			return resp;
		}
		return resp;
	}

	LinkedList<Veiculo> veiculos = new LinkedList<>();

	@Override
	public String dataToText() {
		double totalArrecadado = veiculos.stream()
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
