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
        cliente.addVeiculo(veiculo);
        estacionamento.estacionar("555", null);
        assertEquals(0, veiculo.totalDeUsos());
    }

    @Test
    public void sair() {
        Cliente cliente = new Cliente("Teste", "666");
        Veiculo veiculo = new Veiculo("555");
        estacionamento.addCliente(cliente);
        cliente.addVeiculo(veiculo);
        double valor = estacionamento.sair("555");
        assertTrue(valor > 0.0);
    }

    @Test
    public void totalArrecadado() {
        Cliente cliente = new Cliente("Teste", "666");
        Veiculo veiculos = new Veiculo("555");
        double arrecadado = estacionamento.totalArrecadado();
        assertEquals(arrecadado, estacionamento.totalArrecadado(), 0.01);
    }

    @Test
    public void arrecadacaoNoMes() {
        Cliente cliente = new Cliente("Teste", "666");
        Veiculo veiculos = new Veiculo("555");
        assertEquals(0, estacionamento.arrecadacaoNoMes(1), 0.01);
    }

    @Test
    public void valorMedioPorUso() {
        Cliente cliente = new Cliente("Teste", "666");
        Veiculo veiculos = new Veiculo("555");
        assertEquals(0, estacionamento.valorMedioPorUso(), 0.01);
    }

    @Test
    public void testTop5Clientes() {
        Cliente cliente1 = new Cliente("Andre", "555");
        estacionamento.addCliente(cliente1);
        estacionamento.estacionar("555", null);
        estacionamento.sair("555");
        Cliente cliente2 = new Cliente("Miguel", "554");
        estacionamento.addCliente(cliente2);
        estacionamento.estacionar("554", null);
        estacionamento.sair("554");
        Cliente cliente3 = new Cliente("Segiao", "553");
        estacionamento.addCliente(cliente3);
        estacionamento.estacionar("553", null);
        estacionamento.sair("553");
        Cliente cliente4 = new Cliente("Dudao", "552");
        estacionamento.addCliente(cliente4);
        estacionamento.estacionar("552", null);
        estacionamento.sair("552");
        Cliente cliente5 = new Cliente("Fael", "551");
        estacionamento.addCliente(cliente5);
        estacionamento.estacionar("551", null);
        estacionamento.sair("551");

        assertEquals("Andre, Miguel, Segiao, Dudao, Fael", estacionamento.top5Clientes());

    }
}