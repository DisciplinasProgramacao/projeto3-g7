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
    public void gerarVagas() {
        Vaga vagas[] = new Vaga[5];
        for (int i = 0; i < 5; i++) {
            vagas[i] = new Vaga(i,5);
        }
        assertTrue(vagas[0].sair());
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

    
}