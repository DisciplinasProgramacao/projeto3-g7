package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import entities.Enums.Servicos;

import entities.Enums.ECliente;
import entities.Enums.ETurnos;
import entities.excecoes.VeiculoNaoEncontradoException;

/**
 * Representa um estacionamento.
 * 
 * Um estacionamento possui um nome, uma lista de clientes, um conjunto de vagas e informações sobre a quantidade de fileiras e vagas por fileira.
 * 
 * Os clientes podem adicionar veículos ao estacionamento, estacionar seus veículos, sair do estacionamento, consultar o histórico de uso e mudar o tipo de cliente.
 * O estacionamento também permite calcular a arrecadação total, a arrecadação por mês, a porcentagem de usos de clientes mensalistas, a arrecadação por mês para clientes horistas, o valor médio por uso e os cinco principais clientes que geraram a maior receita em um determinado mês.
 */
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
     * @param veiculo   Veículo a ser adicionado.
     * @param idCliente Identificação do cliente.
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
     * Estaciona um veículo no estacionamento.
     * 
     * @param placa a placa do veículo a ser estacionado
     * @param servicos os serviços a serem realizados no veículo
     * @throws VeiculoNaoEncontradoException se o veículo com a placa especificada não for encontrado
     */
    public void estacionar(String placa, Servicos servicos) {
        boolean veiculoEstacionado = false;
        for (Cliente cliente : clientes.values()) {
            if (cliente != null && cliente.possuiVeiculo(placa) != null) {
                for (Vaga vaga : vagas) {
                    if (vaga != null && vaga.disponivel()) {
                        cliente.possuiVeiculo(placa).estacionar(vaga, servicos);
                        veiculoEstacionado = true;
                        break;
                    }
                }
                break;
            }
        }
        if (!veiculoEstacionado) {
            throw new VeiculoNaoEncontradoException("O veículo com placa " + placa + " não foi encontrado.");
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
            if (cliente.possuiVeiculo(placa) != null) {
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
        return clientes.values().stream()
                .filter(Objects::nonNull)
                .mapToDouble(Cliente::arrecadadoTotal)
                .sum();
    }

    /**
     * Calcula a arrecadação no mês para todos os clientes.
     *
     * @param mes Número do mês.
     * @return Arrecadação total no mês.
     */

    public double arrecadacaoNoMes(int mes) {
        return clientes.values().stream()
                .filter(Objects::nonNull)
                .mapToDouble(cliente -> cliente.arrecadadoNoMes(mes))
                .sum();
    }

    /**
     * Retorna uma string contendo o total de usos dos clientes mensalistas no mês especificado
     * e a porcentagem em relação ao total de usos no mês.
     * 
     * @param mes O mês para o qual se deseja obter o total de usos dos clientes mensalistas.
     * @return Uma string contendo o total de usos dos clientes mensalistas no mês e a porcentagem em relação ao total de usos no mês.
     */
    public String mesClienteMensalista(int mes) {
        int totalDeUsosMensalistas = clientes.values().stream()
                .filter(cliente -> cliente.verificarTipo("Mensalista"))
                .mapToInt(cliente -> cliente.totalDeUsosNoMes(mes))
                .sum();
    
        int totalDeUsos = clientes.values().stream()
                .mapToInt(cliente -> cliente.totalDeUsosNoMes(mes))
                .sum();
    
        double porcentagem = ((double) totalDeUsosMensalistas / totalDeUsos) * 100;
    
        return "Total de usos dos clientes mensalistas no mês " + mes + ": " + totalDeUsosMensalistas
                + "\nPorcentagem em relação ao total de usos no mês: " + porcentagem + "%";
    }

    /**
     * Calcula a arrecadação no mês para clientes do tipo horista.
     *
     * @param mes Número do mês.
     * @return Arrecadação total no mês para clientes do tipo horista.
     */

    public double arrecadacaoNoMesClienteHorista(int mes) {
        return clientes.values().stream()
                .filter(cliente -> cliente.verificarTipo("Horista"))
                .mapToDouble(cliente -> cliente.arrecadadoNoMes(mes))
                .sum();
    }

    /**
     * Calcula o valor médio por uso no estacionamento.
     *
     * @return Valor médio por uso.
     */
    public double valorMedioPorUso() {
        long totalClientes = clientes.values().stream()
                .filter(Objects::nonNull)
                .count();

        if (totalClientes == 0) {
            return 0.0;
        }

        double totalArrecadado = totalArrecadado();
        return totalArrecadado / totalClientes;
    }

    /**
     * Gera um histórico detalhado para um cliente específico.
     *
     * @param id Identificação do cliente.
     * @return Histórico detalhado do cliente.
     */

    public String historicoCliente(String id) {
        Cliente busca = new Cliente(id, id);
        String texto = "";

        for (Cliente cliente : clientes.values()) {
            if (busca.equals(cliente)) {
                texto = cliente.historicoCliente();
            }

        }
        return texto;
    }

    /**
     * Retorna o histórico de um veículo com base na placa.
     * 
     * @param placa a placa do veículo
     * @return o histórico do veículo em formato de texto ou "Error" se não for encontrado
     */
    public String historicoVeiculo(String placa) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.possuiVeiculo(placa) != null) {
                return cliente.possuiVeiculo(placa).dataToText();
            }
        }
        return "Error";

    }

    /**
     * Altera o tipo de cliente e turno de um cliente específico.
     * 
     * @param cpf O CPF do cliente a ser modificado.
     * @param tipo O novo tipo de cliente.
     * @param turno O novo turno do cliente.
     */
    public void mudarTipoCliente(String cpf, ECliente tipo, ETurnos turno) {
        Cliente busca = new Cliente(cpf, cpf);
        for (Cliente cliente : clientes.values()) {
            if (cliente.equals(busca)) {
                cliente.mudarTipo(tipo, turno);
                ;
            }
        }
    }

    /**
     * 
     * Retorna os cinco principais clientes que geraram a maior receita em um
     * determinado mês.*
     * 
     * @param mes Número do mês.
     * @return Nomes dos cinco principais clientes separados por vírgula.
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
        top5Clientes.forEach(entry -> resultado.append("Cliente ID: ").append(entry.getKey())
                .append(", Valor Gasto: R$ ").append(entry.getValue()).append("\n"));

        return resultado.toString();
    }

}
