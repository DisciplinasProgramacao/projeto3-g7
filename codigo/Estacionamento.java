import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa um estacionamento com várias funcionalidades, como adição de clientes, veículos,
 * estacionamento e cálculos relacionados a arrecadação.
 */
public class Estacionamento {

  
    private String nome;
    private Cliente[] clientes;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;

    /**
     * Construtor da classe Estacionamento.
     *
     * @param nome Nome do estacionamento.
     * @param fileiras Quantidade de fileiras de vagas.
     * @param vagasPorFila Quantidade de vagas por fileira.
     */
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new Cliente[100]; // Supondo um máximo de 100 clientes
        this.vagas = new Vaga[fileiras * vagasPorFila];
        gerarVagas(); // Método privado para gerar as vagas no estacionamento
    }

    /**
     * Adiciona um veículo a um cliente com base no ID do cliente.
     *
     * @param veiculo Veículo a ser adicionado.
     * @param idCli ID do cliente.
     */
    public boolean addVeiculo(Veiculo veiculo, Cliente cliente) {
        boolean teste = false;
        for (Cliente c : clientes) {
            if (c != null && c.equals(cliente)) {
                c.addVeiculo(veiculo);
                teste = true;
                break;
            }
        }
        return teste;
    }

    /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente Cliente a ser adicionado.
     */
    public boolean addCliente(Cliente cliente) {
        boolean teste = false;
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] == null) {
                clientes[i] = cliente;
                teste = true;
                break;
            }
        }
        return teste;
    }

    /**
     * Método privado para gerar as vagas disponíveis no estacionamento.
     */
    public boolean gerarVagas() {
        boolean teste = false;
        int vagaId = 0;
        for (int i = 0; i < this.quantFileiras; i++) {
            for (int j = 0; j < this.vagasPorFileira; j++) {
                this.vagas[vagaId] = new Vaga();
                vagaId++;
                teste = true;
            }
        }
        return teste;
    }

    /**
     * Estaciona um veículo no estacionamento com base na placa do veículo.
     *
     * @param placa Placa do veículo a ser estacionado.
     */
    public boolean estacionar(String placa) {
        boolean teste = false;
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.possuiVeiculo(placa) != null) {
                for (Vaga vaga : vagas) {
                    if (vaga != null && vaga.disponivel()) {
                        cliente.possuiVeiculo(placa).estacionar(vaga);
                        teste = true;
                        break;
                    }
                }
                break;
            }
        }
        return teste;
    }

    /**
     * Remove um veículo do estacionamento com base na placa e calcula o valor a ser pago.
     *
     * @param placa Placa do veículo a ser removido.
     * @return Valor a ser pago pelo uso da vaga.
     */
    public double sair(String placa) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.possuiVeiculo(placa) != null) {
                return cliente.possuiVeiculo(placa).sair();
            }
        }
        return 0.0;
    }

    /**
     * Calcula o total arrecadado pelo estacionamento com base nas transações dos clientes.
     *
     * @return Total arrecadado pelo estacionamento.
     */
    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                totalArrecadado += cliente.arrecadadoTotal();
            }
        }
        return totalArrecadado;
    }

    /**
     * Calcula a arrecadação em um determinado mês com base nas transações dos clientes.
     *
     * @param mes Número do mês.
     * @return Arrecadação no mês especificado.
     */
    public double arrecadacaoNoMes(int mes) {
        double arrecadacaoNoMes = 0.0;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                arrecadacaoNoMes += cliente.arrecadadoNoMes(mes);
            }
        }
        return arrecadacaoNoMes;
    }

    /**
     * Calcula o valor médio por uso do estacionamento com base nas transações dos clientes.
     *
     * @return Valor médio por uso do estacionamento.
     */
    public double valorMedioPorUso() {
        int totalDeUsos = 0;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                totalDeUsos += cliente.totalDeUsos();
            }
        }
        return totalArrecadado() / 1;
    }

    /**
     * Retorna os cinco principais clientes que geraram a maior receita em um determinado mês.
     *
     * @param mes Número do mês.
     * @return Nomes dos cinco principais clientes separados por vírgula.
     */
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