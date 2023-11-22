package entities;

import java.util.HashMap;
import java.util.Map;

public class Estacionamento {

    private String nome;
    private Map<String, Cliente> clientes; 
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private int qtdcliente = 0;

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

    public void addVeiculo(Veiculo veiculo, String idCliente) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null) {
            cliente.addVeiculo(veiculo);
        }
    }

    public void addCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente); 
    }

    private void gerarVagas() {
        int vagaId = 0;
        for (int i = 0; i < this.quantFileiras; i++) {
            for (int j = 0; j < this.vagasPorFileira; j++) {
                this.vagas[vagaId] = new Vaga(nFileira, nVaga);
                vagaId++;
            }
        }
    }

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

    public double sair(String placa) {
        for (Cliente cliente : clientes.values()) {
            if (cliente != null && cliente.possuiVeiculo(placa) != null) {
                return cliente.possuiVeiculo(placa).sair();
            }
        }
        return 0.0;
    }

    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (Cliente cliente : clientes.values()) {
            if (cliente != null) {
                totalArrecadado += cliente.arrecadadoTotal();
            }
        }
        return totalArrecadado;
    }

    public double arrecadacaoNoMes(int mes) {
        double arrecadacaoNoMes = 0.0;
        for (Cliente cliente : clientes.values()) {
            if (cliente != null) {
                arrecadacaoNoMes += cliente.arrecadadoNoMes(mes);
            }
        }
        return arrecadacaoNoMes;
    }

    public double valorMedioPorUso() {
        int totalDeUsos = 1;
        for (Cliente cliente : clientes.values()) {
            if (cliente != null) {
                totalDeUsos += cliente.totalDeUsos();
            }
        }
        return totalArrecadado() / totalDeUsos;
    }

    /**
     * Retorna os cinco principais clientes que geraram a maior receita em um
     * determinado mês.
     *
     * @param mes Número do mês.
     * @return Nomes dos cinco principais clientes separados por vírgula.
     */
  //   public String top5Clientes(int mes) {
  //       Map<Cliente, Double> arrecadacaoPorCliente = new HashMap<>();
  //
  //       Collections.sort(clientes.values(), 
  //                               (c1, c2)-> 
  //                                   c1.arrecadacaoNoMes(mes)>c2.arrecadadoNoMes(mes)?1:-1
  //                       );
//
//
  //      for (Cliente cliente : clientes) {
    //        if (cliente != null) {
      //          double arrecadacao = cliente.arrecadadoNoMes(mes);
        //         arrecadacaoPorCliente.put(cliente, arrecadacao);
          //   }
         //}

        //}

    }
