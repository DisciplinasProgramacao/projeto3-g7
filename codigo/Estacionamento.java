// Classe Estacionamento
public class Estacionamento {

	// Atributos da classe
	private String nome;
	private Cliente[] clientes;
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira;

	// Construtor da classe
	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
		this.nome = nome;
		this.quantFileiras = fileiras;
		this.vagasPorFileira = vagasPorFila;
		this.clientes = new Cliente[100]; // Supondo um máximo de 100 clientes
		this.vagas = new Vaga[fileiras * vagasPorFila];
		gerarVagas();
	}

	// Método para adicionar um veículo a um cliente
	public void addVeiculo(Veiculo veiculo, String idCli) {
		for (Cliente cliente : clientes) {
			if (cliente != null && cliente.getId().equals(idCli)) {
				cliente.addVeiculo(veiculo);
				break;
			}
		}
	}

	// Método para adicionar um cliente ao estacionamento
	public void addCliente(Cliente cliente) {
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] == null) {
				clientes[i] = cliente;
				break;
			}
		}
	}

	// Método privado para gerar vagas no estacionamento
	private void gerarVagas() {
		int vagaId = 0;
		for (int i = 0; i < this.quantFileiras; i++) {
			for (int j = 0; j < this.vagasPorFileira; j++) {
				this.vagas[vagaId] = new Vaga();
				vagaId++;
			}
		}
	}

	// Método para estacionar um veículo
	public void estacionar(String placa) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.possuiVeiculo(placa) != null) {
                for (Vaga vaga : vagas) {
                    if (vaga != null && vaga.disponivel()) {
                        cliente.possuiVeiculo(placa).estacionar(vaga);
                        break;
                    }
                }
                break;
            }
        }
    }

    // Método para um veículo sair do estacionamento
    public double sair(String placa) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.possuiVeiculo(placa) != null) {
                return cliente.possuiVeiculo(placa).sair();
            }
        }
        return 0.0;
    }

    // Método para calcular o total arrecadado pelo estacionamento
    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                totalArrecadado += cliente.arrecadadoTotal();
            }
        }
        return totalArrecadado;
    }

    // Método para calcular a arrecadação em um determinado mês
    public double arrecadacaoNoMes(int mes) {
        double arrecadacaoNoMes = 0.0;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                arrecadacaoNoMes += cliente.arrecadadoNoMes(mes);
            }
        }
        return arrecadacaoNoMes;
    }

    // Método para calcular o valor médio por uso do estacionamento
    public double valorMedioPorUso() {
        int totalDeUsos = 0;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                totalDeUsos += cliente.totalDeUsos();
            }
        }
        return totalArrecadado() / totalDeUsos;
    }

    // Método para retornar os cinco principais clientes que geraram a maior receita em um determinado mês
    public String top5Clientes(int mes) {
        Map<Cliente, Double> arrecadacaoPorCliente = new HashMap<>();
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                double arrecadacao = cliente.arrecadadoNoMes(mes);
                arrecadacaoPorCliente.put(cliente, arrecadacao);
            }
        }

        List<Map.Entry<Cliente, Double>> list = new ArrayList<>(arrecadacaoPorCliente.entrySet());
        list.sort(Map.Entry.comparingByValue());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5 && i < list.size(); i++) {
            sb.append(list.get(i).getKey().getNome());
            if (i < 4 && i < list.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
