package entities;

import java.util.LinkedList;

import entities.Enums.ECliente;
import entities.Enums.ETurnos;
import entities.excecoes.VeiculoJaEstacionadoException;
import entities.Enums.Servicos;

/**
 * A classe Veiculo representa um veículo que pode estacionar em vagas de
 * estacionamento.
 * Cada veículo é identificado por sua placa e pode registrar seu uso de vagas.
 */
public class Veiculo implements IDataToText {

	private String placa; // A placa do veículo
	private UsoDeVaga[] usos = new UsoDeVaga[10]; // Array de usos de vagas associado ao veículo
	private int mes; // Mês atual para cálculos
	private int totalDeUsos = 1; // Total de usos de vagas registrados para o veículo
	private double arrecadadoNoMes = 0; // Valor arrecadado no mês atual
	private double totalArrecadado = 0; // Valor total arrecadado pelo veículo
	private boolean estacionado = false;
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
		if (estacionado) {
			throw new VeiculoJaEstacionadoException("O veículo já está estacionado.");
		}

		if (vaga.disponivel()) {
			if (vaga.estacionar()) {
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
					// Se o veículo foi estacionado com sucesso, atualiza o atributo estacionado
					// para true
					estacionado = true;
				} else {
					throw new IllegalArgumentException("eCliente or eCliente.getNome() cannot be null");
				}
			}
		}
	}

	public void estacionar(Vaga vaga, Servicos servicos) {
		if (vaga.disponivel() == true) {
			if (vaga.estacionar() == true)
				if (eCliente != null && eCliente.getNome() != null) {
					switch (eCliente.getNome()) {
						case "Horista":
							usos[indiceDeVaga] = new UsoHorista(vaga, servicos);
							indiceDeVaga++;
							break;
						case "Mensalista":
							usos[indiceDeVaga] = new UsoMensalista(vaga, servicos);
							indiceDeVaga++;
							break;
						case "Turno":
							usos[indiceDeVaga] = new UsoTurno(vaga, eTurnos, servicos);
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

		estacionado = false;

		if (usos[indiceDeVaga - 1] != null) {
			return usos[indiceDeVaga - 1].sair();
		} else {
			throw new NullPointerException("Uso de vaga é nulo");
		}
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
		double totalArrecadado = totalArrecadado();
		int totalUsos = totalDeUsos();

		StringBuilder relatorio = new StringBuilder();
		relatorio.append("Relatório do Veículo\n");
		relatorio.append("-------------------\n");
		relatorio.append("Placa: ").append(placa).append("\n");
		relatorio.append("Total de Usos: ").append(totalUsos).append("\n");
		relatorio.append("Total Arrecadado: ").append(totalArrecadado).append("\n");
		for(int i = 0; i < 12; i++) {
			relatorio.append("Arrecadado no mês ").append(i+1).append(": ").append(arrecadadoNoMes(i+1)).append("\n");
		}
		return relatorio.toString();
	}

}
