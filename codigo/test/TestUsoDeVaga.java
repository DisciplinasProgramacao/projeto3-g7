package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import entities.*;

public class TestUsoDeVaga {

    private UsoDeVaga usoDeVaga;
    private Vaga vaga;
    private int n1 = 3;
    private int n2 = 4;

    @Before
    public void setup() {

        vaga = new Vaga(n1, n2);
        usoDeVaga = new UsoDeVaga(vaga);
    }

    @Test
    public void testGetValorPagoShouldReturnZeroIfVagaIsEmpty() {
        double result = usoDeVaga.valorPago();
        assertEquals(0.0, result, 0.01);
    }

    @Test
    public void testGetValorPagoShouldReturnValorPago() {
        vaga.disponivel();

        double valorPago = usoDeVaga.sair();
        double result = usoDeVaga.valorPago();
        assertEquals(valorPago, result, 0.01);
    }

}
