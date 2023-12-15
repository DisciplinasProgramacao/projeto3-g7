package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import entities.Enums.Servicos;

import entities.Enums.ECliente;
import entities.Enums.ETurnos;
import entities.excecoes.ClienteDuplicadoException;
import entities.excecoes.ClienteNaoEncontradoException;
import entities.excecoes.VeiculoNaoEncontradoException;

/**
 * Representa um estacionamento.
 * 
 * Um estacionamento possui um nome, uma lista de clientes, um conjunto de vagas
 * e informações sobre a quantidade de fileiras e vagas por fileira.
 * 
 * Os clientes podem adicionar veículos ao estacionamento, estacionar seus
 * veículos, sair do estacionamento, consultar o histórico de uso e mudar o tipo
 * de cliente.
 * O estacionamento também permite calcular a arrecadação total, a arrecadação
 * por mês, a porcentagem de usos de clientes mensalistas, a arrecadação por mês
 * para clientes horistas, o valor médio por uso e os cinco principais clientes
 * que geraram a maior receita em um determinado mês.
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
     * Adiciona um veículo a um cliente existente com base no ID do cliente.
     *
     * @param veiculo   Veículo a ser adicionado.
     * @param idCliente Identificação do cliente ao qual o veículo será associado.
     * @throws ClienteNaoEncontradoException Se o cliente não for encontrado para o
     *                                       ID especificado.
     */
    public void addVeiculo(Veiculo veiculo, String idCliente) {
        if (clientes.containsKey(idCliente)) {
            Cliente cliente = clientes.get(idCliente);
            cliente.addVeiculo(veiculo);
        } else {
            throw new ClienteNaoEncontradoException("Cliente não encontrado. Não é possível adicionar o veículo.");
        }
    }

    /**
     * Adiciona um novo cliente ao sistema, verificando se já existe um cliente com
     * base na igualdade entre objetos.
     *
     * @param cliente Cliente a ser adicionado ao sistema.
     * @throws ClienteDuplicadoException Se um cliente duplicado for encontrado no
     *                                   sistema.
     */
    public void addCliente(Cliente cliente) {
        for (Cliente c : clientes.values()) {
            if (c.equals(cliente)) {
                throw new ClienteDuplicadoException("Cliente duplicado encontrado.");
            }
        }
        clientes.put(cliente.getId(), cliente);
    }

    /**
     * Gera e inicializa as vagas no estacionamento de acordo com as fileiras e
     * vagas por fileira.
     * A alocação das vagas é sequencial, onde o primeiro carro deveria ocupar a
     * primeira vaga.
     * A numeração das vagas é feita de maneira sequencial, começando da primeira
     * fileira até a última.
     */
    private void gerarVagas() {
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
     * @param placa    a placa do veículo a ser estacionado
     * @param servicos os serviços a serem realizados no veículo
     * @throws VeiculoNaoEncontradoException se o veículo com a placa especificada
     *                                       não for encontrado
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
     * Remove um veículo estacionado com base na placa do veículo.
     *
     * @param placa Placa do veículo a ser removido.
     * @return O valor arrecadado pelo veículo ao sair.
     * @throws VeiculoNaoEncontradoException Se o veículo com a placa especificada
     *                                       não for encontrado.
     */
    public double sair(String placa) throws VeiculoNaoEncontradoException {
        for (Cliente cliente : clientes.values()) {
            if (cliente.possuiVeiculo(placa) != null) {
                return cliente.possuiVeiculo(placa).sair();
            }
        }
        throw new VeiculoNaoEncontradoException("Veículo com a placa " + placa + " não encontrado.");
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
     * Retorna uma string contendo o total de usos dos clientes mensalistas no mês
     * especificado
     * e a porcentagem em relação ao total de usos no mês.
     * 
     * @param mes O mês para o qual se deseja obter o total de usos dos clientes
     *            mensalistas.
     * @return Uma string contendo o total de usos dos clientes mensalistas no mês e
     *         a porcentagem em relação ao total de usos no mês.
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
     * Gera um histórico detalhado para um cliente específico com base na
     * identificação do cliente.
     *
     * @param id Identificação do cliente para o qual o histórico será gerado.
     * @return Histórico detalhado do cliente em formato de texto.
     * @throws ClienteNaoEncontradoException Se o cliente não for encontrado para o
     *                                       ID especificado.
     */
    public String historicoCliente(String id) {
        Cliente busca = new Cliente(id, id);
        String texto = "";

        for (Cliente cliente : clientes.values()) {
            if (busca.equals(cliente)) {
                texto = cliente.historicoCliente();
                return texto;
            }
        }

        throw new ClienteNaoEncontradoException("Cliente não encontrado para o ID: " + id);
    }

    /**
     * Retorna o histórico de um veículo com base na placa do veículo.
     * 
     * @param placa Placa do veículo para o qual o histórico será obtido.
     * @return O histórico do veículo em formato de texto ou "Error" se não for
     *         encontrado.
     * @throws VeiculoNaoEncontradoException Se o veículo não for encontrado no
     *                                       histórico.
     */
    public String historicoVeiculo(String placa) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.possuiVeiculo(placa) != null) {
                return cliente.possuiVeiculo(placa).dataToText();
            }
        }
        throw new VeiculoNaoEncontradoException("Veículo não encontrado no histórico.");
    }

    /**
     * Altera o tipo e, se aplicável, o turno de um cliente com base no CPF
     * fornecido.
     *
     * @param cpf   CPF do cliente a ser modificado.
     * @param tipo  Novo tipo de cliente a ser definido.
     * @param turno Novo turno, aplicável apenas se o tipo for "Turno".
     * @throws ClienteNaoEncontradoException Se o cliente não for encontrado para o
     *                                       CPF especificado.
     */
    public void mudarTipoCliente(String cpf, ECliente tipo, ETurnos turno) throws ClienteNaoEncontradoException {
        Cliente busca = new Cliente(cpf, cpf);
        boolean clienteEncontrado = false;

        for (Cliente cliente : clientes.values()) {
            if (cliente.equals(busca)) {
                cliente.mudarTipo(tipo, turno);
                clienteEncontrado = true;
                break;
            }
        }

        if (!clienteEncontrado) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado para o CPF: " + cpf);
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
                .collect(Collectors.toMap(Cliente::getNome, Cliente::arrecadadoTotal));

        List<Map.Entry<String, Double>> listaOrdenada = valorGastoPorCliente.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toList());

        List<Map.Entry<String, Double>> top5Clientes = listaOrdenada.stream()
                .limit(5)
                .collect(Collectors.toList());

        StringBuilder resultado = new StringBuilder();
        resultado.append("Top 5 Clientes que mais gastaram no estacionamento:\n");
        top5Clientes.forEach(entry -> resultado.append("Cliente Nome: ").append(entry.getKey())
                .append(", Valor Gasto: R$ ").append(entry.getValue()).append("\n"));

        return resultado.toString();
    }
}
