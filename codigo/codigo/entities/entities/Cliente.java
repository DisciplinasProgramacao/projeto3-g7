package entities;

import java.util.ArrayList;
import java.util.List;

import entities.Enums.ECliente;
import entities.Enums.ETurnos;
import entities.Enums.Servicos;

/**
 * A classe Cliente representa um cliente do sistema.
 * 
 * Um cliente possui um nome, um ID, uma lista de veículos, a quantidade de veículos,
 * um tipo de cliente e um turno de trabalho.
 * 
 * A classe Cliente implementa a interface IDataToText.
 */
public class Cliente implements IDataToText {

	private String nome;
	private String id;
	private List<Veiculo> veiculos = new ArrayList<>();
	private int qtdVeiculo;
	private ECliente tipo;
	private ETurnos turno;
	private Servicos servicos;

	public Cliente(String nome, String id, ECliente tipo, ETurnos turno) {
		this.nome = nome;
		this.id = id;
		this.qtdVeiculo = 0;
		this.tipo = tipo;
		this.turno = turno;
	}

	public Cliente(String nome, String id) {
		this.nome = nome;
		this.id = id;
		this.tipo = ECliente.HORISTA;
		this.qtdVeiculo = 0;
	}

	public Cliente(String nome, String id, ECliente tipo) {
		this.nome = nome;
		this.id = id;
		this.tipo = tipo;
		this.qtdVeiculo = 0;
	}

	public Cliente(String nome, String id, Servicos servicos) {
		this.nome = nome;
		this.id = id;
		this.tipo = ECliente.HORISTA;
		this.qtdVeiculo = 0;
		this.servicos = servicos;
	}

	public String getId() {
		return this.id;
	}

	/**
	 * Altera o tipo do cliente e, opcionalmente, o turno.
	 * 
	 * @param novoTipo O novo tipo de cliente.
	 * @param turno O novo turno do cliente (opcional).
	 */
	public void mudarTipo(ECliente novoTipo, ETurnos turno) {
		this.tipo = novoTipo;
		if (turno != null) {
			this.turno = turno;
		}
		for (Veiculo veiculo : veiculos) {
			veiculo.setEcliente(novoTipo);
			if (turno != null) {
				this.turno = turno;
				veiculo.setETurno(turno);
			}
		}
	}

	/**
	 * Classe addVeiculo que adicionará um veiculo ao cliente
	 * 
	 * @param veiculo Recebe um veiculo que será adicionado a um dos veiculos do
	 *                cliente
	 */
	public void addVeiculo(Veiculo veiculo) {
		veiculo.setEcliente(tipo);
		if (tipo.equals(ECliente.TURNO)) {
			veiculo.setETurno(turno);
		}
		veiculos.add(veiculo);
	}

	/**
	 * Classe possuiVeiculo verifica se o o cliente possui o veiculo com certa placa
	 * 
	 * @param placa Recebe a placa do usuario
	 * @return Se o cliente possuira o veiculo
	 */
	public Veiculo possuiVeiculo(String placa) {
		Veiculo busca = new Veiculo(placa);
		return veiculos.stream()
				.filter(v -> v.equals(busca))
				.findFirst()
				.orElse(null);
	}

	/**
	 * Classe totalDeUsos que retornará o total de usos de todos os veiculos
	 * 
	 * @return retorna o total de usos
	 */
	public int totalDeUsos() {
		return veiculos.stream()
				.mapToInt(Veiculo::totalDeUsos)
				.sum();
	}

	public int totalDeUsosNoMes(int mes) {
		return veiculos.stream()
				.mapToInt(veiculo -> veiculo.totalDeUsosNoMes(mes))
				.sum();
	}

	/**
	 * Classe arrecadadoPorVeiculo que retornará o valor arrecadado por um veiculo
	 * 
	 * @param placa recebe a placa do veiculo
	 * @return valor arrecadado por um veiculo
	 */
	public double arrecadadoPorVeiculo(String placa) {
		Veiculo busca = new Veiculo(placa);
		return veiculos.stream()
				.filter(v -> v.equals(busca))
				.mapToDouble(Veiculo::totalArrecadado)
				.findFirst()
				.orElse(0.0);
	}

	/**
	 * Classe arrecadadoTotal que retornará o valor arrecadado total juntando todos
	 * os veiculos
	 * 
	 * @return valor arrecadado por todos os veiculos
	 */
	public double arrecadadoTotal() {
		double arrecadadoTotal = tipo.getValor();

		arrecadadoTotal += veiculos.stream()
				.mapToDouble(Veiculo::totalArrecadado)
				.sum();

		return arrecadadoTotal;
	}

	/**
	 * Classe arrecadadoNoMes que retornará o valor arrecadado pelos veiculos no mes
	 * 
	 * @param mes recebe o mes
	 * @return retorna o valor arrecadado no mes
	 */
	public double arrecadadoNoMes(int mes) {
		double arrecadadoNoMes = tipo.getValor(); // Adicionando o valor do tipo de cliente

		arrecadadoNoMes += veiculos.stream()
				.mapToDouble(v -> v.arrecadadoNoMes(mes))
				.sum();

		return arrecadadoNoMes;
	}

	public boolean verificarTipo(String tipo) {
		return tipo.equals(this.tipo.getNome());
	}

	/**
	 * Gera um histórico detalhado para um cliente, incluindo informações sobre
	 * veículos
	 * e valores gastos mensalmente e no total.
	 * 
	 * @return Uma string representando o histórico do cliente.
	 */
	public String historicoCliente() {
		StringBuilder historico = new StringBuilder();
		historico.append("Histórico do Cliente: ").append(nome).append(" (ID: ").append(id).append(")\n");
		historico.append("Veículos do Cliente e Valores Gastos:\n");

		for (int i = 0; i < veiculos.size(); i++) {
			historico.append("Veículo ").append(i + 1).append(": R$ ").append(veiculos.get(i).totalArrecadado())
					.append("\n");
		}

		historico.append("Valor Gasto por Mês:\n");
		for (int mes = 1; mes <= 12; mes++) {
			double valorMes = arrecadadoNoMes(mes);
			historico.append("Mês ").append(mes).append(": R$ ").append(valorMes).append("\n");
		}

		historico.append("Valor Total Arrecadado: R$ ").append(arrecadadoTotal()).append("\n");
		return historico.toString();
	}

	/**
	 * Converte os dados do cliente em formato de texto.
	 * 
	 * @return Os dados do cliente em formato de texto.
	 */
	@Override
	public String dataToText() {
		return this.nome + ";" + this.qtdVeiculo;
	}

	public boolean equals(Cliente c) {
		boolean resp = false;
		if (c.id.equals(this.id)) {
			resp = true;
			return resp;
		}
		return resp;
	}
}
