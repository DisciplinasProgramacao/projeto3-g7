package test;

import org.junit.Test;

import entities.*;

import static org.junit.Assert.*;

public class TestVeiculo {

    @Test
    public void testVeiculo() {
        Veiculo veiculo = new Veiculo("ABC1234");
        assertNotNull(null, veiculo.getPlaca());
        assertEquals("ABC1234", veiculo.getPlaca());
    }

    @Test
    public void testEstacionar() {
        Vaga vaga = new Vaga(1, 1);
        Veiculo veiculo = new Veiculo("ABC1244");
        veiculo.estacionar(vaga);
        assertEquals(false, vaga.disponivel());
    }

    @Test
    public void testSairTotalArrecadado() {
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga = new Vaga(1, 1);

        veiculo.estacionar(vaga);
        double valorPago = veiculo.sair();
        double totalArrecadado = veiculo.totalArrecadado();

        assertEquals(0.0, valorPago, 0.01);
        assertEquals(0.0, totalArrecadado, 0.01);

    }

    @Test
    public void testTotalArrecadadoNoMes() {

        Veiculo veiculo = new Veiculo("LOL1189");
        Vaga vaga1 = new Vaga(1, 2);
        Vaga vaga3 = new Vaga(1, 3);

        veiculo.estacionar(vaga1);

        double arrecadadoMes1 = veiculo.arrecadadoNoMes(1);
        assertEquals(0.0, arrecadadoMes1, 0.01);

        veiculo.estacionar(vaga3);

        double arrecadadoMes2 = veiculo.arrecadadoNoMes(2);
        assertEquals(0.0, arrecadadoMes2, 0.01);

    }

    @Test
    public void testTotalDeUsos() {
        Veiculo veiculo = new Veiculo("BBA1189");
        Vaga vaga1 = new Vaga(3,1); 
        Vaga vaga5 = new Vaga(1,3); 

        veiculo.estacionar(vaga1);
        veiculo.estacionar(vaga5);

        int totalUsos = veiculo.totalDeUsos();

        assertEquals(2, totalUsos); 
    }
}



   