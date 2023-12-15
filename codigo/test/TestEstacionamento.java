package test;

import org.junit.Test;
import static org.junit.Assert.*;
import entities.*;

public class TestEstacionamento {

    Estacionamento estacionamento = new Estacionamento("Test", 5, 5);

    @Test
    public void adicionarVeiculo() {
        Veiculo carro = new Veiculo("555");
        Cliente cliente = new Cliente("Teste", "666");
        estacionamento.addCliente(cliente);
        estacionamento.addVeiculo(carro, "666");
        assertEquals(0, carro.totalDeUsos());
    }

    @Test
    public void adicionarCliente() {
        Cliente cliente = new Cliente("André", "666");
        estacionamento.addCliente(cliente);
        assertEquals(0, cliente.totalDeUsos());
    }



    @Test
    public void testEstacionar() {
        Cliente cliente = new Cliente("Andre", "5556");
        Veiculo veiculo = new Veiculo("555");
        estacionamento.addCliente(cliente);
        estacionamento.addVeiculo(veiculo, "5556");
        estacionamento.estacionar("555", null);
        estacionamento.sair("555");
        assertEquals(1, veiculo.totalDeUsos());

    }

    @Test
    public void sair() {
        Cliente cliente = new Cliente("Andre", "5556");
        Veiculo veiculo = new Veiculo("555");
        estacionamento.addCliente(cliente);
        estacionamento.addVeiculo(veiculo, "5556");
        estacionamento.estacionar("555", null);
        double valor = estacionamento.sair("555");
        assertEquals(4.0, valor, 0.0);
    }

    @Test
    public void totalArrecadado() {
        Cliente cliente = new Cliente("Andre", "5556");
        Veiculo veiculo = new Veiculo("555");
        estacionamento.addCliente(cliente);
        estacionamento.addVeiculo(veiculo, "5556");
        estacionamento.estacionar("555", null);
        estacionamento.sair("555");
        assertEquals(4.0, estacionamento.totalArrecadado(), 0.01);
    }

    @Test
    public void arrecadacaoNoMes() {
        Cliente cliente = new Cliente("Andre", "5556");
        Veiculo veiculo = new Veiculo("555");
        estacionamento.addCliente(cliente);
        estacionamento.addVeiculo(veiculo, "5556");
        estacionamento.estacionar("555", null);
        estacionamento.sair("555");
        assertEquals(4.0, estacionamento.arrecadacaoNoMes(12), 0.01);
    }

    @Test
    public void valorMedioPorUso() {
        Cliente cliente = new Cliente("Andre", "5556");
        Veiculo veiculo = new Veiculo("555");
        estacionamento.addCliente(cliente);
        estacionamento.addVeiculo(veiculo, "5556");
        estacionamento.estacionar("555", null);
        estacionamento.sair("555");
        assertEquals(4.0, estacionamento.valorMedioPorUso(), 0.01);
    }
}
