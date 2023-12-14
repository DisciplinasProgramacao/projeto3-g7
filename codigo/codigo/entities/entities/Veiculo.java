package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entities.Enums.ECliente;
import entities.Enums.ETurnos;
import entities.Enums.Servicos;
import entities.excecoes.VeiculoJaEstacionadoException;

/**
 * A classe Veiculo representa um veículo que pode estacionar em vagas de
 * estacionamento.
 * Cada veículo é identificado por sua placa e pode registrar seu uso de vagas.
 */
/**
 * Representa um veículo.
 * Implementa a interface IDataToText para converter os dados do veículo em
 * texto.
 */
public class Veiculo implements IDataToText {

	private String placa; // A placa do veículo
	private List<UsoDeVaga> usos = new ArrayList<>(); // Array de usos de vagas associado ao veículo

	private boolean estacionado = false;

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
	 * Estaciona o veículo em uma vaga, registrando o uso do veículo e os serviços
	 * prestados.
	 * 
	 * @param vaga     A vaga onde o veículo será estacionado.
	 * @param servicos Os serviços prestados ao veículo.
	 * @throws VeiculoJaEstacionadoException Se o veículo já estiver estacionado.
	 * @throws IllegalArgumentException      Se o cliente do veículo ou o nome do
	 *                                       cliente for nulo.
	 */
	public void estacionar(Vaga vaga, Servicos servicos) {
		if (estacionado) {
			throw new VeiculoJaEstacionadoException("O veículo já está estacionado.");
		}

		if (vaga.disponivel()) {
			if (vaga.estacionar()) {
				if (eCliente != null && eCliente.getNome() != null) {
					switch (eCliente.getNome()) {
						case "Horista":
							usos.add(new UsoHorista(vaga, servicos));
							break;
						case "Mensalista":
							usos.add(new UsoMensalista(vaga, servicos));
							break;
						case "Turno":
							usos.add(new UsoTurno(vaga, eTurnos, servicos));

							break;
						default:
							break;
					}
					estacionado = true;
				} else {
					throw new IllegalArgumentException("eCliente or eCliente.getNome() cannot be null");
				}
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

		if (usos.isEmpty()) {
			throw new NullPointerException("Não há uso de vaga registrado para saída");

		} else {
			try {
				return usos.get(usos.size() - 1).sair();
			} catch (IllegalArgumentException e) {
				return 0;
			}
		}
	}

	/**
	 * Calcula o valor total arrecadado pelo veículo até o momento.
	 *
	 * @return O valor total arrecadado pelo veículo.
	 */
	public double totalArrecadado() {
		double total = 0.0;
		for (UsoDeVaga uso : usos) {
			total += uso.valorPago();
		}
		return total;
	}

	/**
	 * Calcula o valor arrecadado pelo veículo no mês especificado.
	 *
	 * @param mes O mês para o qual se deseja calcular o valor arrecadado.
	 * @return O valor arrecadado pelo veículo no mês especificado.
	 */
	public double arrecadadoNoMes(int mes) {
		double arrecadadoNoMes = 0.0;
		for (UsoDeVaga uso : usos) {
			if (uso.ehDoMes(mes)) {
				arrecadadoNoMes += uso.valorPago();
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
		return usos.size();
	}

	/**
	 * Retorna o total de usos do veículo no mês especificado.
	 * 
	 * @param mes O mês para o qual se deseja obter o total de usos.
	 * @return O total de usos do veículo no mês especificado.
	 */
	public int totalDeUsosNoMes(int mes) {
		return (int) usos.stream().filter(u -> u.ehDoMes(mes)).count();
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

	/**
	 * Converte os dados do veículo em formato de texto.
	 * 
	 * @return uma string contendo o relatório do veículo
	 */
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
		for (int i = 0; i < 12; i++) {
			relatorio.append("Arrecadado no mês ").append(i + 1).append(": ").append(arrecadadoNoMes(i + 1))
					.append("\n");
		}
		return relatorio.toString();
	}

}
