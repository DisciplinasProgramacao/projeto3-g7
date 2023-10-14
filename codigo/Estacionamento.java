public class Estacionamento {

    private String nome;
    private Cliente[] id;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private Cliente[] clientes;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.vagas = new Vaga[fileiras * vagasPorFila];
    }

    public boolean addVeiculo(Veiculo veiculo, String ecID) {
        Cliente cliente = encontrarClienteID(ecID);
        if (cliente != null) {
            cliente.addVeiculo(veiculo);
            return true;
        } else {
            return false;
        }
    }

    private Cliente encontrarClienteID(String ecID) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getId().equals(ecID)) {
                return cliente;
            }
        }
        return null;
    }

    public boolean adicionarCliente(Cliente cliente) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] == null) {
                clientes[i] = cliente;
                return true;
            }
        }
        return false;
    }

	private void gerarVagas() {
		int numVagas = quantFileiras * vagasPorFileira;
        for (int i = 0; i < numVagas; i++) {
            Vaga vaga = new Vaga(i + 1); 
            vagas[i] = vaga;
        }
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
        Cliente cliente = encontrarClientePorPlaca(placa);
        if (cliente != null) {
            Vaga vaga = cliente.sairDoEstacionamento(placa);
            if (vaga != null) {
                double valorCobranca = calcularValorCobranca(vaga.getHoraEntrada());
                valorTotalArrecadado += valorCobranca;

                // Atualizar a arrecadação por mês
                int mesAtual = obterMesAtual();
                arrecadacaoPorMes.putIfAbsent(mesAtual, 0.0);
                arrecadacaoPorMes.put(mesAtual, arrecadacaoPorMes.get(mesAtual) + valorCobranca);

                return valorCobranca;
            }
        }
        return 0.0; // Se o veículo não estiver estacionado ou não for encontrado, retorne 0.
    }

	public double totalArrecadado() {
		return valorTotalArrecadado;
	}

	public double arrecadacaoNoMes(int mes) {
        return arrecadacaoPorMes.getOrDefault(mes, 0.0);
    }

	public double valorMedioPorUso() {
		
	}

	public String top5Clientes(int mes) {
		
	}

}