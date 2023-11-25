package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estacionamento {

    public String nome;
    private Map<String, Cliente> clientes; 
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private int nFileira;
    private int nVaga;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new HashMap<>(); 
        this.vagas = new Vaga[fileiras * vagasPorFila];
        gerarVagas();
    }
       /**
     * Adiciona um veículo ao cliente.
     *
     * @param veiculo    Veículo a ser adicionado.
     * @param idCliente  Identificação do cliente.
     */

    public void addVeiculo(Veiculo veiculo, String idCliente) {
        clientes.get(idCliente).addVeiculo(veiculo);
    }
       /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente Cliente a ser adicionado.
     */

    public void addCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente); 
    }
      /**
     * Gera as vagas do estacionamento.
     * Error pode estar aqui, pois o primeiro carro deveria parar na primeira vaga.
     */

    private void gerarVagas() { // error pode estar aqui, pois o primeiro carro deverian parar na primeira vaga
        int vagaId = 0;
        for (int i = 0; i < this.quantFileiras; i++) {
            for (int j = 0; j < this.vagasPorFileira; j++) {
                this.vagas[vagaId] = new Vaga(nFileira, nVaga);
                vagaId++;
            }
        }
    }
 /**
     * Estaciona um veículo.
     *
     * @param placa Placa do veículo a ser estacionado.
     */
    public void estacionar(String placa) {
        for (Cliente cliente : clientes.values()) {
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
      /**
     * Remove um veículo estacionado.
     *
     * @param placa Placa do veículo a ser removido.
     * @return Valor arrecadado pelo veículo ao sair.
     */

    public double sair(String placa) {
        for (Cliente cliente : clientes.values()) {
            if (cliente != null && cliente.possuiVeiculo(placa) != null) {
                return cliente.possuiVeiculo(placa).sair();
            }
        }
        return 0.0;
    }
     /**
     * Calcula o total arrecadado no estacionamento.
     *
     * @return Valor total arrecadado.
     */

    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (Cliente cliente : clientes.values()) {
            if (cliente != null) {
                totalArrecadado += cliente.arrecadadoTotal();
            }
        }
        return totalArrecadado;
    }
    /**
     * Calcula a arrecadação no mês para todos os clientes.
     *
     * @param mes Número do mês.
     * @return Arrecadação total no mês.
     */

    public double arrecadacaoNoMes(int mes) {
        double arrecadacaoNoMes = 0.0;
        for (Cliente cliente : clientes.values()) {
            if (cliente != null) {
                arrecadacaoNoMes += cliente.arrecadadoNoMes(mes);
            }
        }
        return arrecadacaoNoMes;
    }
    /**
     * Calcula a arrecadação no mês para clientes do tipo horista.
     *
     * @param mes Número do mês.
     * @return Arrecadação total no mês para clientes do tipo horista.
     */

    public double arrecadacaoNoMesClienteHorista(int mes) {
        double arrecadacaoNoMesClienteHorista = 0.0;
        for (Cliente cliente : clientes.values()) {
            if (cliente.getTipo() == "Horista") {
                arrecadacaoNoMesClienteHorista += cliente.arrecadadoNoMes(mes);
            }
        }
        return arrecadacaoNoMesClienteHorista;
    }
/**
     * Calcula o valor médio por uso no estacionamento.
     *
     * @return Valor médio por uso.
     */
    public double valorMedioPorUso() {
        int totalDeUsos = 0;
        for (Cliente cliente : clientes.values()) {
            if (cliente != null) {
                totalDeUsos += 1;
            }
        }
        return totalArrecadado() / totalDeUsos;
    }
     /**
     * Gera um histórico detalhado para um cliente específico.
     *
     * @param id Identificação do cliente.
     * @return Histórico detalhado do cliente.
     */

    public String historicoCliente(String id) {
        Cliente busca = new Cliente(id,id);
        String texto = "";

        for (Cliente cliente : clientes.values()) {
            if (busca.equals(cliente)) {
                texto = cliente.historicoCliente();
            }

    }
        return texto;
    }
/**
     
Retorna os cinco principais clientes que geraram a maior receita em um
determinado mês.*
@param mes Número do mês.
@return Nomes dos cinco principais clientes separados por vírgula.
*/
public String top5Clientes() {

    Map<String, Double> valorGastoPorCliente = clientes.values().stream()
            .collect(Collectors.toMap(Cliente::getId, Cliente::arrecadadoTotal));


    List<Map.Entry<String, Double>> listaOrdenada = valorGastoPorCliente.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .collect(Collectors.toList());


    List<Map.Entry<String, Double>> top5Clientes = listaOrdenada.stream()
            .limit(5)
            .collect(Collectors.toList());

    StringBuilder resultado = new StringBuilder();
    resultado.append("Top 5 Clientes que mais gastaram no estacionamento:\n");
    top5Clientes.forEach(entry ->
            resultado.append("Cliente ID: ").append(entry.getKey()).append(", Valor Gasto: R$ ").append(entry.getValue()).append("\n"));

    return resultado.toString();
}


}
