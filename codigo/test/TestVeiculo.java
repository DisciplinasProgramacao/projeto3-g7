package test;

import org.junit.Test;

import entities.*;

import static org.junit.Assert.*;

public class TestVeiculo {

    @Test
    public void testEstacionar() {
        // Cria uma instância de Veiculo e uma instância de Vaga
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga = new Vaga(1, 1); // Certifique-se de que a classe Vaga tenha um construtor que aceite fila e
                                    // número

        // Estaciona o veículo na vaga
        veiculo.estacionar(vaga);

        // Verifica se a vaga foi ocupada corretamente
        assertTrue(vaga.sair());
    }

    @Test
    public void testSair() {
        // Cria uma instância de Veiculo e uma instância de Vaga
        Veiculo veiculo = new Veiculo("XYZ789");
        Vaga vaga = new Vaga(2, 2);

        // Estaciona o veículo na vaga
        veiculo.estacionar(vaga);

        // Registra a saída do veículo e verifica se o valor pago é maior que zero
        assertTrue(veiculo.sair() > 0);
    }

    @Test
    public void testTotalArrecadado() {
        // Cria uma instância de Veiculo e uma instância de Vaga
        Veiculo veiculo = new Veiculo("DEF456");
        Vaga vaga = new Vaga(3, 3);

        // Estaciona o veículo na vaga
        veiculo.estacionar(vaga);

        // Registra a saída do veículo e verifica se o total arrecadado é maior que zero
        assertTrue(veiculo.totalArrecadado() > 0);
    }

    @Test
    public void testArrecadadoNoMes() {
        // Cria uma instância de Veiculo e uma instância de Vaga
        Veiculo veiculo = new Veiculo("GHI789");
        Vaga vaga = new Vaga(4, 4);

        // Estaciona o veículo na vaga
        veiculo.estacionar(vaga);

        // Registra a saída do veículo no mês especificado e verifica se o valor
        // arrecadado é maior que zero
        assertTrue(veiculo.arrecadadoNoMes(11) > 0);
    }

    @Test
    public void testTotalDeUsos() {
        // Cria uma instância de Veiculo e uma instância de Vaga
        Veiculo veiculo = new Veiculo("JKL012");
        Vaga vaga = new Vaga(5, 20);

        // Estaciona o veículo na vaga
        veiculo.estacionar(vaga);

        // Verifica se o total de usos é igual a 1
        assertEquals(1, veiculo.totalDeUsos());
    }

}

