package entities;

public class Cliente implements IDataToText{

	private String nome;
	private String id;
	private Veiculo[] veiculos = new Veiculo[100];
	private int qtdVeiculo;

	public Cliente(String nome, String id) {
		this.nome = nome;
		this.id = id;
		this.qtdVeiculo = 0;
	}

	/**
	 * Classe addVeiculo que adicionará um veiculo ao cliente
	 * 
	 * @param veiculo Recebe um veiculo que será adicionado a um dos veiculos do
	 *                cliente
	 */
	public void addVeiculo(Veiculo veiculo) {
		if (qtdVeiculo < veiculos.length) {
			veiculos[qtdVeiculo] = veiculo;
			qtdVeiculo++;
		}
	}

	/**
	 * Classe possuiVeiculo verifica se o o cliente possui o veiculo com certa placa
	 * 
	 * @param placa Recebe a placa do usuario
	 * @return Se o cliente possuira o veiculo
	 */
	public Veiculo possuiVeiculo(String placa) {
		Veiculo busca = new Veiculo(placa);
		for (int i = 0; i < qtdVeiculo; i++) {
			if (busca.equals(veiculos[i]))
				return veiculos[i];
		}
		return busca;
	}

	/**
	 * Classe totalDeUsos que retornará o total de usos de todos os veiculos
	 * 
	 * @return retorna o total de usos
	 */
	public int totalDeUsos() {
		int totalDeUsos = 0;
		for (int i = 0; i < qtdVeiculo; i++) {
			totalDeUsos += veiculos[i].totalDeUsos();
		}
		return totalDeUsos;
	}

	/**
	 * Classe arrecadadoPorVeiculo que retornará o valor arrecadado por um veiculo
	 * 
	 * @param placa recebe a placa do veiculo
	 * @return valor arrecadado por um veiculo
	 */
	public double arrecadadoPorVeiculo(String placa) {
		double valorArrecadado = 0;
		Veiculo busca = new Veiculo(placa);
		for (int i = 0; i < qtdVeiculo; i++) {
			if (busca.equals(veiculos[i])) {
				valorArrecadado = busca.totalArrecadado();
			}
		}
		return valorArrecadado;

	}

	/**
	 * Classe arrecadadoTotal que retornará o valor arrecadado total juntando todos
	 * os veiculos
	 * 
	 * @return valor arrecadado por todos os veiculos
	 */
	public double arrecadadoTotal() {
		int arrecadadoTotal = 0;
		for (int i = 0; i < qtdVeiculo; i++) {
			arrecadadoTotal += veiculos[i].totalArrecadado();
		}
		return arrecadadoTotal;
	}

	/**
	 * Classe arrecadadoNoMes que retornará o valor arrecadado pelos veiculos no mes
	 * 
	 * @param mes recebe o mes
	 * @return retorna o valor arrecadado no mes
	 */
	public double arrecadadoNoMes(int mes) {
		int arrecadadoNoMes = 0;
		for (int i = 0; i < qtdVeiculo; i++) {
			arrecadadoNoMes += veiculos[i].arrecadadoNoMes(mes);
		}
		return arrecadadoNoMes;
	}

	public String historicoCliente() {
		StringBuilder historico = new StringBuilder();
		historico.append("Histórico do Cliente: ").append(nome).append(" (ID: ").append(id).append(")\n");
		historico.append("Veículos do Cliente e Valores Gastos:\n");
		for (int i = 0; i < qtdVeiculo; i++) {
			historico.append("Veículo ").append(i + 1).append(": R$ ").append(veiculos[i].totalArrecadado())
					.append("\n");
		}
		historico.append("Valor Gasto por Mês:\n");
		for (int mes = 1; mes <= 12; mes++) {
			double valorMes = arrecadadoNoMes(mes);
			historico.append("Mês ").append(mes).append(": R$ ").append(valorMes).append("\n");
		}
		double valorTotal = arrecadadoTotal();
		historico.append("Valor Total Arrecadado: R$ ").append(valorTotal).append("\n");
		return historico.toString();
	}

	public Object getNome() {
		return nome;
	}

	public Object getId() {
		return id;
	}

	public Object[] getVeiculos() {
		return veiculos;
	}

	@Override
	public String dataToText() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'dataToText'");
	}

}
