package entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

public class Estacionamento {

    private String nome;
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

    public void addVeiculo(Veiculo veiculo, String idCliente) {
        clientes.get(idCliente).addVeiculo(veiculo);
    }

    public void addCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente); 
    }

    private void gerarVagas() { // error pode estar aqui, pois o primeiro carro deverian parar na primeira vaga
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
    
    public double totalArrecadadoHorista() {
        double totalArrecadadoClienteHorista = 0.0;
        for (Cliente clienteHorista : clientes.values()) {
            if (clienteHorista != null) {
                totalArrecadadoClienteHorista += clienteHorista.arrecadadoTotal();
            }
        }
        return totalArrecadadoClienteHorista;
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

        public double arrecadacaoNoMesClienteHorista(int mes) {
        double arrecadacaoNoMesClienteHorista = 0.0;
        for (Cliente cliente : clientes.values()) {
            if (cliente != null) {
                arrecadacaoNoMesClienteHorista += cliente.arrecadadoNoMes(mes);
            }
        }
        return arrecadacaoNoMesClienteHorista;
    }

    public double valorMedioPorUso() {
        int totalDeUsos = 0;
        for (Cliente cliente : clientes.values()) {
            if (cliente != null) {
                totalDeUsos += 1;
            }
        }
        return totalArrecadado() / totalDeUsos;
    }

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

    public boolean validaEstacionamento(Estacionamento estacionamento) {
        return estacionamento != null;

    }

/**
     
Retorna os cinco principais clientes que geraram a maior receita em um
determinado mês.*
@param mes Número do mês.
@return Nomes dos cinco principais clientes separados por vírgula.
*/
  public String top5Clientes(int mes) {
  List<Cliente> clienteDoMes = new ArrayList<>();

        for (Cliente x : clientes.values()) {
            if (x.arrecadadoNoMes(mes) > 0) {
                clienteDoMes.add(x);
            }
        }
        clienteDoMes.sort(new Comparator<Cliente>() {
            public int compare(Cliente cliente1, Cliente cliente2) {
                double arrecadacao1 = cliente1.arrecadadoNoMes(mes);
                double arrecadacao2 = cliente2.arrecadadoNoMes(mes);

                return Double.compare(arrecadacao2, arrecadacao1);
            }
        });
        int maxClientes = Math.min(5, clienteDoMes.size());

        StringBuilder top5 = new StringBuilder();

        top5.append("Top 5 clientes do mês " + mes + " :\n");

        for (int i = 0; i < maxClientes; i++) {
            Cliente cliente = clienteDoMes.get(i);
            top5.append(cliente + "\n");
        }
        return top5.toString();
    }

}
