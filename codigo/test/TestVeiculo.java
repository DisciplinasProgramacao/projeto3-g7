package test;

import entities.*;
import org.junit.Test;
import static org.junit.Assert.*;
import entities.Veiculo;
import entities.Vaga;

public class TestVeiculo {

    @Test
    public void testEstacionar() {
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga = new Vaga(1, 1);
        veiculo.estacionar(vaga, null);
        assertEquals(4.0, veiculo.sair(), 0.0);
    }

    @Test
    public void testSair() {
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga = new Vaga(1, 1);
        veiculo.estacionar(vaga, null);
        veiculo.sair();
        assertNull(veiculo.sair());
    }

    @Test
    public void testTotalDeUsos() {
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga1 = new Vaga(1, 1);
        Vaga vaga2 = new Vaga(2, 2);
        veiculo.estacionar(vaga1, null);
        veiculo.estacionar(vaga2, null);
        assertEquals(2, veiculo.totalDeUsos());
    } 

    @Test
    public void testTotalArrecadado() {
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga1 = new Vaga(1, 1);
        Vaga vaga2 = new Vaga(2, 2);
        veiculo.estacionar(vaga1, null);
        veiculo.estacionar(vaga2, null);
        assertEquals(20.0, veiculo.totalArrecadado(), 0.0);
    }

   
}

    
