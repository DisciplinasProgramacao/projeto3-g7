package test;

import org.junit.Before;
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
        estacionamento.addVeiculo(carro, cliente);
        assertEquals(0, carro.totalDeUsos());
    }

    @Test
    public void adicionarCliente() {
        Cliente cliente = new Cliente("André", "666");
        estacionamento.addCliente(cliente);
        assertEquals(0, cliente.totalDeUsos());
    }

    @Test
    public void gerarVagas() {
        Vaga vagas[] = new Vaga[5];
        for (int i = 0; i < 5; i++) {
            vagas[i] = new Vaga();
        }
        estacionamento.gerarVagas();
        assertTrue(vagas[0].disponivel());
    }

    @Test
    public void estacionar() {
        Veiculo veiculo = new Veiculo("555");
        Cliente cliente = new Cliente("Teste", "666");
        Vaga vaga = new Vaga();
        estacionamento.gerarVagas();
        estacionamento.addCliente(cliente);
        estacionamento.addVeiculo(veiculo, cliente);
        estacionamento.estacionar("555");
    }

    @Test
    public void sair() {
        Cliente cliente = new Cliente("Teste", "666");
        Veiculo veiculo = new Veiculo("555");
        cliente.addVeiculo(veiculo);
        estacionamento.sair("555");
        assertEquals(veiculo, cliente.possuiVeiculo("555"));
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
    public void top5Clientes() {
        Cliente cliente = new Cliente("Vitor", "666");
        Cliente cliente1 = new Cliente("Vitor", "665");
        Cliente cliente2 = new Cliente("Vitor", "664");
        Cliente cliente3 = new Cliente("Vitor", "663");
        Cliente cliente4 = new Cliente("Vitor", "662");
        estacionamento.addCliente(cliente);
        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente3);
        estacionamento.addCliente(cliente4);
        Veiculo veiculo = new Veiculo("555");
        Veiculo veiculo1 = new Veiculo("554");
        Veiculo veiculo2 = new Veiculo("553");
        Veiculo veiculo3 = new Veiculo("552");
        Veiculo veiculo4 = new Veiculo("551");
        estacionamento.addVeiculo(veiculo1, cliente);
        estacionamento.addVeiculo(veiculo2, cliente1);
        estacionamento.addVeiculo(veiculo3, cliente2);
        estacionamento.addVeiculo(veiculo4, cliente3);
        estacionamento.addVeiculo(veiculo, cliente4);

        assertEquals("Vitor", estacionamento.top5Clientes(1));
    }
}