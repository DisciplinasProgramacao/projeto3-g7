
public class Estacionamento {

	private String nome;
	private Cliente[] id;
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira;

	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
		this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.vagas = new Vaga[fileiras * vagasPorFila];	
	}

	public void addVeiculo(Veiculo veiculo, String idCli) {
		
	}

	public void addCliente(Cliente cliente) {
		
	}

	private void gerarVagas() {
		
	}

	public void estacionar(String placa) {

		Cliente cliente = encontrarClientePorPlaca(placa);
        if (cliente != null) {
            Vaga vaga = encontrarVagaLivre();
            if (vaga != null) {
                vaga.ocuparVaga(cliente, placa);
                double valorCobranca = calcularValorCobranca();
                cliente.registrarUsoVaga(placa, vaga, valorCobranca);
                valorTotalArrecadado += valorCobranca;

                // Atualizar a arrecadação por mês
                int mesAtual = obterMesAtual();
                arrecadacaoPorMes.putIfAbsent(mesAtual, 0.0);
                arrecadacaoPorMes.put(mesAtual, arrecadacaoPorMes.get(mesAtual) + valorCobranca);
            }
        }
		
	}

	public double sair(String placa) {
		
	}

	public double totalArrecadado() {
		
	}

	public double arrecadacaoNoMes(int mes) {
		
	}

	public double valorMedioPorUso() {
		
	}

	public String top5Clientes(int mes) {
		
	}

}
