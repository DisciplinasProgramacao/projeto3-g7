package test;

import org.junit.Test;
import static org.junit.Assert.*;
import entities.Veiculo;
import entities.Vaga;

public class TestVeiculo {
    @Test
    public void testEstacionar() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga = new Vaga(4, 7);
        veiculo.estacionar(vaga);
        assertEquals(0, veiculo.getIndiceDeVaga());
    }

    @Test
    public void testSair() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga = new Vaga(4, 7);
        veiculo.estacionar(vaga);
        double valorPago = veiculo.sair();
        assertEquals(10.0, valorPago, 0.01);
    }

    @Test
    public void testTotalArrecadado() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga1 = new Vaga(4, 7);
        Vaga vaga2 = new Vaga(5, 8);
        veiculo.estacionar(vaga1);
        veiculo.estacionar(vaga2);
        double totalArrecadado = veiculo.totalArrecadado();
        assertEquals(20.0, totalArrecadado, 0.01);
    }

    @Test
    public void testArrecadadoNoMes() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga1 = new Vaga(4, 7);
        Vaga vaga2 = new Vaga(5, 8);
        veiculo.estacionar(vaga1);
        veiculo.estacionar(vaga2);
        double arrecadadoNoMes = veiculo.arrecadadoNoMes(4);
        assertEquals(10.0, arrecadadoNoMes, 0.01);
    }

    @Test
    public void testTotalDeUsos() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Vaga vaga1 = new Vaga(4, 7);
        Vaga vaga2 = new Vaga(5, 8);
        veiculo.estacionar(vaga1);
        veiculo.estacionar(vaga2);
        int totalDeUsos = veiculo.totalDeUsos();
        assertEquals(2, totalDeUsos);
    }
}